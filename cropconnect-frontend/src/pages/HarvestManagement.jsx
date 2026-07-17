import { useState, useEffect } from 'react'
import { FaPlus, FaEdit, FaTrash } from 'react-icons/fa'
import { harvestAPI } from '../services/api'
import './Dashboard.css'

function HarvestManagement() {
  const [harvests, setHarvests] = useState([])
  const [loading, setLoading] = useState(false)
  const [error, setError] = useState(null)
  const [message, setMessage] = useState(null)

  useEffect(() => {
    fetchHarvests()
  }, [])

  const fetchHarvests = async () => {
    setLoading(true)
    setError(null)
    try {
      const response = await harvestAPI.getAll()
      setHarvests(response.data)
    } catch (err) {
      setError('Failed to load harvests. ' + (err.response?.data?.message || err.message))
    } finally {
      setLoading(false)
    }
  }

  const handleDelete = async (id) => {
    if (window.confirm('Are you sure you want to delete this harvest record?')) {
      try {
        await harvestAPI.delete(id)
        setMessage('Harvest record deleted successfully!')
        fetchHarvests()
      } catch (err) {
        setError('Failed to delete harvest. ' + (err.response?.data?.message || err.message))
      }
    }
  }

  return (
    <div className="dashboard">
      <div className="dashboard-header">
        <h1>Harvest Management</h1>
        <button className="btn btn-primary" onClick={() => fetchHarvests()}>
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
        <div className="loading">Loading harvest records...</div>
      ) : harvests.length === 0 ? (
        <div className="empty-state">
          <p>No harvest records found.</p>
        </div>
      ) : (
        <div className="table-container">
          <table className="table">
            <thead>
              <tr>
                <th>Harvest ID</th>
                <th>Plot ID</th>
                <th>Crop ID</th>
                <th>Harvest Date</th>
                <th>Quantity (kg)</th>
                <th>Quality Rating</th>
                <th>Actions</th>
              </tr>
            </thead>
            <tbody>
              {harvests.map((harvest) => (
                <tr key={harvest.harvestId}>
                  <td>{harvest.harvestId}</td>
                  <td>{harvest.plotId}</td>
                  <td>{harvest.cropId}</td>
                  <td>{new Date(harvest.harvestDate).toLocaleDateString()}</td>
                  <td>{harvest.quantity || '-'}</td>
                  <td>{harvest.qualityRating || '-'}</td>
                  <td>
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

export default HarvestManagement
