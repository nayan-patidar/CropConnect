import { useState, useEffect } from 'react'
import { FaPlus, FaEdit, FaTrash } from 'react-icons/fa'
import { cropAPI } from '../services/api'
import CropForm from '../components/CropForm'
import './Dashboard.css'

function CropManagement() {
  const [crops, setCrops] = useState([])
  const [loading, setLoading] = useState(false)
  const [error, setError] = useState(null)
  const [showForm, setShowForm] = useState(false)
  const [editingCrop, setEditingCrop] = useState(null)
  const [message, setMessage] = useState(null)

  useEffect(() => {
    fetchCrops()
  }, [])

  const fetchCrops = async () => {
    setLoading(true)
    setError(null)
    try {
      const response = await cropAPI.getAll()
      setCrops(response.data.data || response.data)
    } catch (err) {
      setError('Failed to load crops. ' + (err.response?.data?.message || err.message))
    } finally {
      setLoading(false)
    }
  }

  const handleSubmit = async (data) => {
    try {
      if (editingCrop) {
        await cropAPI.update(editingCrop.cropId, data)
        setMessage('Crop updated successfully!')
      } else {
        await cropAPI.create(data)
        setMessage('Crop added successfully!')
      }
      setShowForm(false)
      setEditingCrop(null)
      fetchCrops()
    } catch (err) {
      setError('Failed to save crop. ' + (err.response?.data?.message || err.message))
    }
  }

  const handleEdit = (crop) => {
    setEditingCrop(crop)
    setShowForm(true)
  }

  const handleDelete = async (id) => {
    if (window.confirm('Are you sure you want to delete this crop?')) {
      try {
        await cropAPI.delete(id)
        setMessage('Crop deleted successfully!')
        fetchCrops()
      } catch (err) {
        setError('Failed to delete crop. ' + (err.response?.data?.message || err.message))
      }
    }
  }

  const handleCloseForm = () => {
    setShowForm(false)
    setEditingCrop(null)
  }

  return (
    <div className="dashboard">
      <div className="dashboard-header">
        <h1>Crop Management</h1>
        <button
          className="btn btn-primary"
          onClick={() => setShowForm(!showForm)}
        >
          <FaPlus /> {showForm ? 'Cancel' : 'Add Crop'}
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
        <CropForm
          crop={editingCrop}
          onSubmit={handleSubmit}
          onCancel={handleCloseForm}
        />
      )}

      {loading ? (
        <div className="loading">Loading crops...</div>
      ) : crops.length === 0 ? (
        <div className="empty-state">
          <p>No crops found. Add one to get started!</p>
        </div>
      ) : (
        <div className="table-container">
          <table className="table">
            <thead>
              <tr>
                <th>Crop ID</th>
                <th>Name</th>
                <th>Type</th>
                <th>Season</th>
                <th>Duration (days)</th>
                <th>Actions</th>
              </tr>
            </thead>
            <tbody>
              {crops.map((crop) => (
                <tr key={crop.cropId}>
                  <td>{crop.cropId}</td>
                  <td>{crop.name}</td>
                  <td>{crop.type || '-'}</td>
                  <td>{crop.season || '-'}</td>
                  <td>{crop.duration || '-'}</td>
                  <td>
                    <button
                      className="btn btn-secondary"
                      onClick={() => handleEdit(crop)}
                      title="Edit"
                    >
                      <FaEdit />
                    </button>
                    <button
                      className="btn btn-danger"
                      onClick={() => handleDelete(crop.cropId)}
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

export default CropManagement
