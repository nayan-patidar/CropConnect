import { useState, useEffect } from 'react'
import { FaPlus, FaEdit, FaTrash } from 'react-icons/fa'
import { farmPlotAPI } from '../services/api'
import './Dashboard.css'

function FarmPlotManagement() {
  const [farmPlots, setFarmPlots] = useState([])
  const [loading, setLoading] = useState(false)
  const [error, setError] = useState(null)
  const [message, setMessage] = useState(null)

  useEffect(() => {
    fetchFarmPlots()
  }, [])

  const fetchFarmPlots = async () => {
    setLoading(true)
    setError(null)
    try {
      const response = await farmPlotAPI.getAll()
      setFarmPlots(response.data)
    } catch (err) {
      setError('Failed to load farm plots. ' + (err.response?.data?.message || err.message))
    } finally {
      setLoading(false)
    }
  }

  const handleDelete = async (id) => {
    if (window.confirm('Are you sure you want to delete this farm plot?')) {
      try {
        await farmPlotAPI.delete(id)
        setMessage('Farm plot deleted successfully!')
        fetchFarmPlots()
      } catch (err) {
        setError('Failed to delete farm plot. ' + (err.response?.data?.message || err.message))
      }
    }
  }

  return (
    <div className="dashboard">
      <div className="dashboard-header">
        <h1>Farm Plot Management</h1>
        <button className="btn btn-primary" onClick={() => fetchFarmPlots()}>
          <FaPlus /> Refresh
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

      {loading ? (
        <div className="loading">Loading farm plots...</div>
      ) : farmPlots.length === 0 ? (
        <div className="empty-state">
          <p>No farm plots found.</p>
        </div>
      ) : (
        <div className="table-container">
          <table className="table">
            <thead>
              <tr>
                <th>Plot ID</th>
                <th>Farmer ID</th>
                <th>Area (acres)</th>
                <th>Soil Type</th>
                <th>Irrigation</th>
                <th>Actions</th>
              </tr>
            </thead>
            <tbody>
              {farmPlots.map((plot) => (
                <tr key={plot.plotId}>
                  <td>{plot.plotId}</td>
                  <td>{plot.farmerId}</td>
                  <td>{plot.area || '-'}</td>
                  <td>{plot.soilType || '-'}</td>
                  <td>{plot.irrigationType || '-'}</td>
                  <td>
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

export default FarmPlotManagement
