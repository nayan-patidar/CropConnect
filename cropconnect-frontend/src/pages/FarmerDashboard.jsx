import { useState, useEffect } from 'react'
import { FaPlus, FaEdit, FaTrash } from 'react-icons/fa'
import { farmerAPI } from '../services/api'
import FarmerForm from '../components/FarmerForm'
import './Dashboard.css'

function FarmerDashboard() {
  const [farmers, setFarmers] = useState([])
  const [loading, setLoading] = useState(false)
  const [error, setError] = useState(null)
  const [showForm, setShowForm] = useState(false)
  const [editingFarmer, setEditingFarmer] = useState(null)
  const [message, setMessage] = useState(null)

  useEffect(() => {
    fetchFarmers()
  }, [])

  const fetchFarmers = async () => {
    setLoading(true)
    setError(null)
    try {
      const response = await farmerAPI.getAll()
      setFarmers(response.data)
    } catch (err) {
      setError('Failed to load farmers. ' + (err.response?.data?.message || err.message))
    } finally {
      setLoading(false)
    }
  }

  const handleSubmit = async (data) => {
    try {
      if (editingFarmer) {
        await farmerAPI.update(editingFarmer.farmerId, data)
        setMessage('Farmer updated successfully!')
      } else {
        await farmerAPI.create(data)
        setMessage('Farmer added successfully!')
      }
      setShowForm(false)
      setEditingFarmer(null)
      fetchFarmers()
    } catch (err) {
      setError('Failed to save farmer. ' + (err.response?.data?.message || err.message))
    }
  }

  const handleEdit = (farmer) => {
    setEditingFarmer(farmer)
    setShowForm(true)
  }

  const handleDelete = async (id) => {
    if (window.confirm('Are you sure you want to delete this farmer?')) {
      try {
        await farmerAPI.delete(id)
        setMessage('Farmer deleted successfully!')
        fetchFarmers()
      } catch (err) {
        setError('Failed to delete farmer. ' + (err.response?.data?.message || err.message))
      }
    }
  }

  const handleCloseForm = () => {
    setShowForm(false)
    setEditingFarmer(null)
  }

  return (
    <div className="dashboard">
      <div className="dashboard-header">
        <h1>Farmer Management</h1>
        <button
          className="btn btn-primary"
          onClick={() => setShowForm(!showForm)}
        >
          <FaPlus /> {showForm ? 'Cancel' : 'Add Farmer'}
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
        <FarmerForm
          farmer={editingFarmer}
          onSubmit={handleSubmit}
          onCancel={handleCloseForm}
        />
      )}

      {loading ? (
        <div className="loading">Loading farmers...</div>
      ) : farmers.length === 0 ? (
        <div className="empty-state">
          <p>No farmers found. Add one to get started!</p>
        </div>
      ) : (
        <div className="table-container">
          <table className="table">
            <thead>
              <tr>
                <th>Farmer ID</th>
                <th>Name</th>
                <th>Contact</th>
                <th>Registration No.</th>
                <th>Land Size (acres)</th>
                <th>Actions</th>
              </tr>
            </thead>
            <tbody>
              {farmers.map((farmer) => (
                <tr key={farmer.farmerId}>
                  <td>{farmer.farmerId}</td>
                  <td>{farmer.name}</td>
                  <td>{farmer.contact || '-'}</td>
                  <td>{farmer.regNo}</td>
                  <td>{farmer.sizeOwned || '-'}</td>
                  <td>
                    <button
                      className="btn btn-secondary"
                      onClick={() => handleEdit(farmer)}
                      title="Edit"
                    >
                      <FaEdit />
                    </button>
                    <button
                      className="btn btn-danger"
                      onClick={() => handleDelete(farmer.farmerId)}
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

export default FarmerDashboard
