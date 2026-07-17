import { useState, useEffect } from 'react'
import { FaPlus, FaEdit, FaTrash, FaLeaf, FaFilter } from 'react-icons/fa'
import { fertilizerAPI, cropAPI } from '../services/api'
import FertilizerForm from '../components/FertilizerForm'
import './Dashboard.css'

function FertilizerManagement() {
  const [fertilizers, setFertilizers] = useState([])
  const [crops, setCrops] = useState([])
  const [loading, setLoading] = useState(false)
  const [error, setError] = useState(null)
  const [showForm, setShowForm] = useState(false)
  const [editingFertilizer, setEditingFertilizer] = useState(null)
  const [message, setMessage] = useState(null)
  const [filterType, setFilterType] = useState('')

  useEffect(() => {
    fetchData()
  }, [])

  const fetchData = async () => {
    setLoading(true)
    setError(null)
    try {
      const [fertRes, cropsRes] = await Promise.all([
        fertilizerAPI.getAll(),
        cropAPI.getAll(),
      ])
      setFertilizers(fertRes.data)
      setCrops(cropsRes.data.data || cropsRes.data)
    } catch (err) {
      setError('Failed to load data. ' + (err.response?.data?.message || err.message))
    } finally {
      setLoading(false)
    }
  }

  const handleSubmit = async (data) => {
    try {
      if (editingFertilizer) {
        await fertilizerAPI.update(editingFertilizer.fertilizerId, data)
        setMessage('Fertilizer updated successfully!')
      } else {
        await fertilizerAPI.create(data)
        setMessage('Fertilizer added successfully!')
      }
      setShowForm(false)
      setEditingFertilizer(null)
      fetchData()
    } catch (err) {
      setError('Failed to save fertilizer. ' + (err.response?.data?.message || err.message))
    }
  }

  const handleEdit = (fertilizer) => {
    setEditingFertilizer(fertilizer)
    setShowForm(true)
  }

  const handleDelete = async (id) => {
    if (window.confirm('Are you sure you want to delete this fertilizer?')) {
      try {
        await fertilizerAPI.delete(id)
        setMessage('Fertilizer deleted successfully!')
        fetchData()
      } catch (err) {
        setError('Failed to delete fertilizer. ' + (err.response?.data?.message || err.message))
      }
    }
  }

  const handleCloseForm = () => {
    setShowForm(false)
    setEditingFertilizer(null)
  }

  const filteredFertilizers = fertilizers.filter((fert) =>
    !filterType || fert.type === filterType
  )

  const fertilizerTypes = [...new Set(fertilizers.map((f) => f.type))].filter(Boolean)

  return (
    <div className="dashboard">
      <div className="dashboard-header">
        <h1><FaLeaf /> Fertilizer Management</h1>
        <button
          className="btn btn-primary"
          onClick={() => setShowForm(!showForm)}
        >
          <FaPlus /> {showForm ? 'Cancel' : 'Add Fertilizer'}
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
        <FertilizerForm
          fertilizer={editingFertilizer}
          onSubmit={handleSubmit}
          onCancel={handleCloseForm}
        />
      )}

      <div className="filters card">
        <div className="filter-group">
          <label><FaFilter /> Filter by Type</label>
          <select value={filterType} onChange={(e) => setFilterType(e.target.value)}>
            <option value="">All Types</option>
            {fertilizerTypes.map((type) => (
              <option key={type} value={type}>
                {type}
              </option>
            ))}
          </select>
        </div>
      </div>

      {loading ? (
        <div className="loading">Loading fertilizers...</div>
      ) : filteredFertilizers.length === 0 ? (
        <div className="empty-state">
          <p>No fertilizers found.</p>
        </div>
      ) : (
        <div className="table-container">
          <table className="table">
            <thead>
              <tr>
                <th>Fertilizer ID</th>
                <th>Name</th>
                <th>Type</th>
                <th>NPK Ratio</th>
                <th>Cost per Unit</th>
                <th>Stock</th>
                <th>Actions</th>
              </tr>
            </thead>
            <tbody>
              {filteredFertilizers.map((fert) => (
                <tr key={fert.fertilizerId}>
                  <td>#{fert.fertilizerId}</td>
                  <td>{fert.name}</td>
                  <td>
                    <span className="type-badge">{fert.type || '-'}</span>
                  </td>
                  <td>{fert.npkRatio || '-'}</td>
                  <td>₹{fert.costPerUnit || '-'}</td>
                  <td>
                    <span className={`stock-indicator ${fert.stock < 10 ? 'low' : 'good'}`}>
                      {fert.stock || 0} units
                    </span>
                  </td>
                  <td>
                    <button
                      className="btn btn-secondary"
                      onClick={() => handleEdit(fert)}
                      title="Edit"
                    >
                      <FaEdit />
                    </button>
                    <button
                      className="btn btn-danger"
                      onClick={() => handleDelete(fert.fertilizerId)}
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

export default FertilizerManagement
