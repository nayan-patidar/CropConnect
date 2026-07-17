import { useState, useEffect } from 'react'
import { FaPlus, FaEdit, FaTrash, FaSearch, FaChartBar } from 'react-icons/fa'
import { harvestAPI, farmPlotAPI } from '../services/api'
import HarvestForm from '../components/HarvestForm'
import './Dashboard.css'
import './Analytics.css'

function HarvestAnalytics() {
  const [harvests, setHarvests] = useState([])
  const [farmPlots, setFarmPlots] = useState([])
  const [loading, setLoading] = useState(false)
  const [error, setError] = useState(null)
  const [showForm, setShowForm] = useState(false)
  const [editingHarvest, setEditingHarvest] = useState(null)
  const [message, setMessage] = useState(null)
  const [searchTerm, setSearchTerm] = useState('')
  const [filterStatus, setFilterStatus] = useState('')
  const [stats, setStats] = useState({
    totalHarvests: 0,
    totalQuantity: 0,
    averageQuality: 0,
  })

  useEffect(() => {
    fetchData()
  }, [])

  const fetchData = async () => {
    setLoading(true)
    setError(null)
    try {
      const [harvestsRes, plotsRes] = await Promise.all([
        harvestAPI.getAll(),
        farmPlotAPI.getAll(),
      ])
      setHarvests(harvestsRes.data)
      setFarmPlots(plotsRes.data)
      calculateStats(harvestsRes.data)
    } catch (err) {
      setError('Failed to load data. ' + (err.response?.data?.message || err.message))
    } finally {
      setLoading(false)
    }
  }

  const calculateStats = (data) => {
    const totalQuantity = data.reduce((sum, h) => sum + (h.quantity || 0), 0)
    const avgQuality = data.length > 0
      ? (data.reduce((sum, h) => sum + (h.qualityRating || 0), 0) / data.length).toFixed(2)
      : 0

    setStats({
      totalHarvests: data.length,
      totalQuantity,
      averageQuality: avgQuality,
    })
  }

  const handleSubmit = async (data) => {
    try {
      if (editingHarvest) {
        await harvestAPI.update(editingHarvest.harvestId, data)
        setMessage('Harvest updated successfully!')
      } else {
        await harvestAPI.create(data)
        setMessage('Harvest recorded successfully!')
      }
      setShowForm(false)
      setEditingHarvest(null)
      fetchData()
    } catch (err) {
      setError('Failed to save harvest. ' + (err.response?.data?.message || err.message))
    }
  }

  const handleEdit = (harvest) => {
    setEditingHarvest(harvest)
    setShowForm(true)
  }

  const handleDelete = async (id) => {
    if (window.confirm('Are you sure you want to delete this harvest record?')) {
      try {
        await harvestAPI.delete(id)
        setMessage('Harvest deleted successfully!')
        fetchData()
      } catch (err) {
        setError('Failed to delete harvest. ' + (err.response?.data?.message || err.message))
      }
    }
  }

  const handleCloseForm = () => {
    setShowForm(false)
    setEditingHarvest(null)
  }

  const filteredHarvests = harvests.filter((harvest) => {
    const matchesSearch = harvest.harvestId.toString().includes(searchTerm)
    const matchesStatus = !filterStatus || harvest.status === filterStatus
    return matchesSearch && matchesStatus
  })

  const getPlotInfo = (plotId) => {
    const plot = farmPlots.find((p) => p.plotId === plotId)
    return plot ? `Plot ${plot.plotId}` : 'Unknown'
  }

  return (
    <div className="dashboard">
      <div className="dashboard-header">
        <h1><FaChartBar /> Harvest Analytics & Management</h1>
        <button
          className="btn btn-primary"
          onClick={() => setShowForm(!showForm)}
        >
          <FaPlus /> {showForm ? 'Cancel' : 'Record Harvest'}
        </button>
      </div>

      {message && (
        <div className="alert alert-success">
          {message}
          <button onClick={() => setMessage(null)} style={{ float: 'right' }}>×</button>
        </div>
      )}

      {error && (
        <div className="alert alert-error">
          {error}
          <button onClick={() => setError(null)} style={{ float: 'right' }}>×</button>
        </div>
      )}

      <div className="stats-grid">
        <div className="stat-card">
          <h3>Total Harvests</h3>
          <p className="stat-value">{stats.totalHarvests}</p>
        </div>
        <div className="stat-card">
          <h3>Total Quantity</h3>
          <p className="stat-value">{stats.totalQuantity.toLocaleString()} kg</p>
        </div>
        <div className="stat-card">
          <h3>Average Quality</h3>
          <p className="stat-value">{stats.averageQuality}/10</p>
        </div>
      </div>

      {showForm && (
        <HarvestForm
          harvest={editingHarvest}
          farmPlots={farmPlots}
          onSubmit={handleSubmit}
          onCancel={handleCloseForm}
        />
      )}

      <div className="filters card">
        <div className="filter-group">
          <label><FaSearch /> Search Harvest ID</label>
          <input
            type="text"
            placeholder="Enter harvest ID..."
            value={searchTerm}
            onChange={(e) => setSearchTerm(e.target.value)}
          />
        </div>
        <div className="filter-group">
          <label>Filter by Status</label>
          <select value={filterStatus} onChange={(e) => setFilterStatus(e.target.value)}>
            <option value="">All Status</option>
            <option value="Completed">Completed</option>
            <option value="Pending">Pending</option>
            <option value="Failed">Failed</option>
          </select>
        </div>
      </div>

      {loading ? (
        <div className="loading">Loading harvest data...</div>
      ) : filteredHarvests.length === 0 ? (
        <div className="empty-state">
          <p>No harvest records found.</p>
        </div>
      ) : (
        <div className="table-container">
          <table className="table">
            <thead>
              <tr>
                <th>Harvest ID</th>
                <th>Farm Plot</th>
                <th>Crop ID</th>
                <th>Harvest Date</th>
                <th>Quantity (kg)</th>
                <th>Quality Rating</th>
                <th>Weather Impact</th>
                <th>Actions</th>
              </tr>
            </thead>
            <tbody>
              {filteredHarvests.map((harvest) => (
                <tr key={harvest.harvestId}>
                  <td>#{harvest.harvestId}</td>
                  <td>{getPlotInfo(harvest.plotId)}</td>
                  <td>{harvest.cropId}</td>
                  <td>{new Date(harvest.harvestDate).toLocaleDateString()}</td>
                  <td>{harvest.quantity?.toLocaleString() || '-'}</td>
                  <td>
                    <span className={`quality-badge quality-${Math.round(harvest.qualityRating / 2)}`}>
                      {harvest.qualityRating || '-'}/10
                    </span>
                  </td>
                  <td>{harvest.weatherImpact || 'Normal'}</td>
                  <td>
                    <button
                      className="btn btn-secondary"
                      onClick={() => handleEdit(harvest)}
                      title="Edit"
                    >
                      <FaEdit />
                    </button>
                    <button
                      className="btn btn-danger"
                      onClick={() => handleDelete(harvest.harvestId)}
                      title="Delete"
                    >
                      <FaTrash />
                    </button>
                  </td>
                </tr>
              ))}
            </tbody>
          </table>
        </div>
      )}
    </div>
  )
}

export default HarvestAnalytics
