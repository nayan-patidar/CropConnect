import { useState, useEffect } from 'react'
import { FaTractor, FaChartLine, FaFilter } from 'react-icons/fa'
import { farmerAPI, farmPlotAPI, harvestAPI, cropAPI } from '../services/api'
import './Dashboard.css'
import './FarmerProfile.css'

function FarmerProfile({ farmerId }) {
  const [farmer, setFarmer] = useState(null)
  const [farmPlots, setFarmPlots] = useState([])
  const [harvests, setHarvests] = useState([])
  const [loading, setLoading] = useState(false)
  const [error, setError] = useState(null)
  const [stats, setStats] = useState({})

  useEffect(() => {
    fetchFarmerData()
  }, [farmerId])

  const fetchFarmerData = async () => {
    setLoading(true)
    setError(null)
    try {
      const [farmerRes, plotsRes, harvestRes] = await Promise.all([
        farmerAPI.getById(farmerId),
        farmPlotAPI.getAll(),
        harvestAPI.getAll(),
      ])

      setFarmer(farmerRes.data)

      const farmerPlots = plotsRes.data.filter((p) => p.farmerId === parseInt(farmerId))
      setFarmPlots(farmerPlots)

      const farmerHarvests = harvestRes.data.filter((h) =>
        farmerPlots.some((p) => p.plotId === h.plotId)
      )
      setHarvests(farmerHarvests)

      calculateStats(farmerPlots, farmerHarvests)
    } catch (err) {
      setError('Failed to load farmer data. ' + (err.response?.data?.message || err.message))
    } finally {
      setLoading(false)
    }
  }

  const calculateStats = (plots, harvests) => {
    const totalArea = plots.reduce((sum, p) => sum + (p.area || 0), 0)
    const totalYield = harvests.reduce((sum, h) => sum + (h.quantity || 0), 0)
    const avgQuality = harvests.length > 0
      ? (harvests.reduce((sum, h) => sum + (h.qualityRating || 0), 0) / harvests.length).toFixed(2)
      : 0

    setStats({
      totalPlots: plots.length,
      totalArea,
      totalYield,
      avgQuality,
      harvestCount: harvests.length,
    })
  }

  if (loading) return <div className="loading">Loading farmer profile...</div>
  if (error) return <div className="alert alert-error">{error}</div>
  if (!farmer) return <div className="empty-state">Farmer not found</div>

  return (
    <div className="farmer-profile">
      <div className="profile-header card">
        <div className="profile-info">
          <h1>{farmer.name}</h1>
          <p className="farmer-id">Farmer ID: {farmer.farmerId}</p>
          <p className="reg-no">Registration No: {farmer.regNo}</p>
          <p className="contact">Contact: {farmer.contact || 'N/A'}</p>
        </div>
      </div>

      <div className="stats-grid">
        <div className="stat-card">
          <h3><FaTractor /> Total Plots</h3>
          <p className="stat-value">{stats.totalPlots || 0}</p>
        </div>
        <div className="stat-card">
          <h3>Total Land Area</h3>
          <p className="stat-value">{stats.totalArea || 0} acres</p>
        </div>
        <div className="stat-card">
          <h3><FaChartLine /> Total Yield</h3>
          <p className="stat-value">{(stats.totalYield || 0).toLocaleString()} kg</p>
        </div>
        <div className="stat-card">
          <h3>Avg Quality</h3>
          <p className="stat-value">{stats.avgQuality || 0}/10</p>
        </div>
      </div>

      <div className="profile-section card">
        <h2>Farm Plots ({stats.totalPlots})</h2>
        {farmPlots.length === 0 ? (
          <p>No farm plots assigned</p>
        ) : (
          <div className="table-container">
            <table className="table">
              <thead>
                <tr>
                  <th>Plot ID</th>
                  <th>Area (acres)</th>
                  <th>Soil Type</th>
                  <th>Irrigation</th>
                  <th>Status</th>
                </tr>
              </thead>
              <tbody>
                {farmPlots.map((plot) => (
                  <tr key={plot.plotId}>
                    <td>#{plot.plotId}</td>
                    <td>{plot.area || '-'}</td>
                    <td>{plot.soilType || '-'}</td>
                    <td>{plot.irrigationType || '-'}</td>
                    <td>
                      <span className={`status-badge status-${plot.status?.toLowerCase() || 'active'}`}>
                        {plot.status || 'Active'}
                      </span>
                    </td>
                  </tr>
                ))}
              </tbody>
            </table>
          </div>
        )}
      </div>

      <div className="profile-section card">
        <h2>Recent Harvests ({stats.harvestCount})</h2>
        {harvests.length === 0 ? (
          <p>No harvest records</p>
        ) : (
          <div className="table-container">
            <table className="table">
              <thead>
                <tr>
                  <th>Date</th>
                  <th>Crop ID</th>
                  <th>Quantity (kg)</th>
                  <th>Quality</th>
                </tr>
              </thead>
              <tbody>
                {harvests.slice(0, 5).map((harvest) => (
                  <tr key={harvest.harvestId}>
                    <td>{new Date(harvest.harvestDate).toLocaleDateString()}</td>
                    <td>{harvest.cropId}</td>
                    <td>{harvest.quantity?.toLocaleString() || '-'}</td>
                    <td>
                      <span className={`quality-badge quality-${Math.round(harvest.qualityRating / 2)}`}>
                        {harvest.qualityRating || '-'}/10
                      </span>
                    </td>
                  </tr>
                ))}
              </tbody>
            </table>
          </div>
        )}
      </div>
    </div>
  )
}

export default FarmerProfile
