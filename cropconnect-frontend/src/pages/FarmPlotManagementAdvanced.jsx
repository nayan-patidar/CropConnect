import { useState, useEffect } from 'react'
import { FaPlus, FaEdit, FaTrash, FaSearch } from 'react-icons/fa'
import { farmPlotAPI, farmerAPI } from '../services/api'
import FarmPlotForm from '../components/FarmPlotForm'
import './Dashboard.css'

function FarmPlotManagementAdvanced() {
  const [farmPlots, setFarmPlots] = useState([])
  const [farmers, setFarmers] = useState([])
  const [loading, setLoading] = useState(false)
  const [error, setError] = useState(null)
  const [showForm, setShowForm] = useState(false)
  const [editingPlot, setEditingPlot] = useState(null)
  const [message, setMessage] = useState(null)
  const [searchTerm, setSearchTerm] = useState('')
  const [filterFarmer, setFilterFarmer] = useState('')

  useEffect(() => {
    fetchData()
  }, [])

  const fetchData = async () => {
    setLoading(true)
    setError(null)
    try {
      const [plotsRes, farmersRes] = await Promise.all([
        farmPlotAPI.getAll(),
        farmerAPI.getAll(),
      ])
      setFarmPlots(plotsRes.data)
      setFarmers(farmersRes.data)
    } catch (err) {
      setError('Failed to load data. ' + (err.response?.data?.message || err.message))
    } finally {
      setLoading(false)
    }
  }

  const handleSubmit = async (data) => {
    try {
      if (editingPlot) {
        await farmPlotAPI.update(editingPlot.plotId, data)
        setMessage('Farm plot updated successfully!')
      } else {
        await farmPlotAPI.create(data)
        setMessage('Farm plot added successfully!')
      }
      setShowForm(false)
      setEditingPlot(null)
      fetchData()
    } catch (err) {
      setError('Failed to save farm plot. ' + (err.response?.data?.message || err.message))
    }
  }

  const handleEdit = (plot) => {
    setEditingPlot(plot)
    setShowForm(true)
  }

  const handleDelete = async (id) => {
    if (window.confirm('Are you sure you want to delete this farm plot?')) {
      try {
        await farmPlotAPI.delete(id)
        setMessage('Farm plot deleted successfully!')
        fetchData()
      } catch (err) {
        setError('Failed to delete farm plot. ' + (err.response?.data?.message || err.message))
      }
    }
  }

  const handleCloseForm = () => {
    setShowForm(false)
    setEditingPlot(null)
  }

  const filteredPlots = farmPlots.filter((plot) => {
    const matchesSearch = plot.plotId.toString().includes(searchTerm)
    const matchesFarmer = !filterFarmer || plot.farmerId.toString() === filterFarmer
    return matchesSearch && matchesFarmer
  })

  const getFarmerName = (farmerId) => {
    const farmer = farmers.find((f) => f.farmerId === farmerId)
    return farmer ? farmer.name : 'Unknown'
  }

  return (
    <div className="dashboard">
      <div className="dashboard-header">
        <h1>Advanced Farm Plot Management</h1>
        <button
          className="btn btn-primary"
          onClick={() => setShowForm(!showForm)}
        >
          <FaPlus /> {showForm ? 'Cancel' : 'Add Farm Plot'}
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

      {showForm && (
        <FarmPlotForm
          plot={editingPlot}
          farmers={farmers}
          onSubmit={handleSubmit}
          onCancel={handleCloseForm}
        />
      )}

      <div className="filters card">
        <div className="filter-group">
          <label><FaSearch /> Search Plot ID</label>
          <input
            type="text"
            placeholder="Enter plot ID..."
            value={searchTerm}
            onChange={(e) => setSearchTerm(e.target.value)}
          />
        </div>
        <div className="filter-group">
          <label>Filter by Farmer</label>
          <select value={filterFarmer} onChange={(e) => setFilterFarmer(e.target.value)}>
            <option value="">All Farmers</option>
            {farmers.map((farmer) => (
              <option key={farmer.farmerId} value={farmer.farmerId}>
                {farmer.name}
              </option>
            ))}
          </select>
        </div>
      </div>

      {loading ? (
        <div className="loading">Loading farm plots...</div>
      ) : filteredPlots.length === 0 ? (
        <div className="empty-state">
          <p>No farm plots found.</p>
        </div>
      ) : (
        <div className="table-container">
          <table className="table">
            <thead>
              <tr>
                <th>Plot ID</th>
                <th>Farmer Name</th>
                <th>Area (acres)</th>
                <th>Soil Type</th>
                <th>Irrigation Type</th>
                <th>Fertilizer</th>
                <th>Status</th>
                <th>Actions</th>
              </tr>
            </thead>
            <tbody>
              {filteredPlots.map((plot) => (
                <tr key={plot.plotId}>
                  <td>#{plot.plotId}</td>
                  <td>{getFarmerName(plot.farmerId)}</td>
                  <td>{plot.area || '-'}</td>
                  <td>{plot.soilType || '-'}</td>
                  <td>{plot.irrigationType || '-'}</td>
                  <td>{plot.fertilizerUsed ? '✓' : '✗'}</td>
                  <td>
                    <span className={`status-badge status-${plot.status?.toLowerCase() || 'active'}`}>
                      {plot.status || 'Active'}
                    </span>
                  </td>
                  <td>
                    <button
                      className="btn btn-secondary"
                      onClick={() => handleEdit(plot)}
                      title="Edit"
                    >
                      <FaEdit />
                    </button>
                    <button
                      className="btn btn-danger"
                      onClick={() => handleDelete(plot.plotId)}
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

export default FarmPlotManagementAdvanced
