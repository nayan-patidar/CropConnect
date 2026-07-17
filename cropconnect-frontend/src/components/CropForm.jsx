import { useState, useEffect } from 'react'
import './Form.css'

function CropForm({ crop, onSubmit, onCancel }) {
  const [formData, setFormData] = useState({
    cropId: '',
    name: '',
    type: '',
    season: '',
    duration: '',
  })
  const [errors, setErrors] = useState({})

  useEffect(() => {
    if (crop) {
      setFormData(crop)
    }
  }, [crop])

  const handleChange = (e) => {
    const { name, value } = e.target
    setFormData((prev) => ({
      ...prev,
      [name]: value,
    }))
    if (errors[name]) {
      setErrors((prev) => ({
        ...prev,
        [name]: '',
      }))
    }
  }

  const validate = () => {
    const newErrors = {}
    if (!formData.cropId) newErrors.cropId = 'Crop ID is required'
    if (!formData.name) newErrors.name = 'Name is required'
    return newErrors
  }

  const handleSubmit = (e) => {
    e.preventDefault()
    const newErrors = validate()
    if (Object.keys(newErrors).length === 0) {
      onSubmit(formData)
    } else {
      setErrors(newErrors)
    }
  }

  return (
    <form className="form card" onSubmit={handleSubmit}>
      <h2>{crop ? 'Edit Crop' : 'Add New Crop'}</h2>

      <div className="form-group">
        <label htmlFor="cropId">Crop ID *</label>
        <input
          type="number"
          id="cropId"
          name="cropId"
          value={formData.cropId}
          onChange={handleChange}
          disabled={!!crop}
          required
        />
        {errors.cropId && <span className="error">{errors.cropId}</span>}
      </div>

      <div className="form-group">
        <label htmlFor="name">Crop Name *</label>
        <input
          type="text"
          id="name"
          name="name"
          value={formData.name}
          onChange={handleChange}
          required
        />
        {errors.name && <span className="error">{errors.name}</span>}
      </div>

      <div className="form-group">
        <label htmlFor="type">Type</label>
        <input
          type="text"
          id="type"
          name="type"
          value={formData.type}
          onChange={handleChange}
          placeholder="e.g., Grain, Vegetable, Fruit"
        />
      </div>

      <div className="form-group">
        <label htmlFor="season">Season</label>
        <select id="season" name="season" value={formData.season} onChange={handleChange}>
          <option value="">Select Season</option>
          <option value="Kharif">Kharif (Monsoon)</option>
          <option value="Rabi">Rabi (Winter)</option>
          <option value="Summer">Summer</option>
          <option value="Perennial">Perennial</option>
        </select>
      </div>

      <div className="form-group">
        <label htmlFor="duration">Duration (days)</label>
        <input
          type="number"
          id="duration"
          name="duration"
          value={formData.duration}
          onChange={handleChange}
        />
      </div>

      <div className="form-actions">
        <button type="submit" className="btn btn-primary">
          {crop ? 'Update Crop' : 'Add Crop'}
        </button>
        <button type="button" className="btn btn-secondary" onClick={onCancel}>
          Cancel
        </button>
      </div>
    </form>
  )
}

export default CropForm
