import { useState, useEffect } from 'react'
import './Form.css'

function FarmPlotForm({ plot, farmers, onSubmit, onCancel }) {
  const [formData, setFormData] = useState({
    plotId: '',
    farmerId: '',
    area: '',
    soilType: '',
    irrigationType: '',
    status: 'Active',
    fertilizerUsed: false,
  })
  const [errors, setErrors] = useState({})

  useEffect(() => {
    if (plot) {
      setFormData(plot)
    }
  }, [plot])

  const handleChange = (e) => {
    const { name, value, type, checked } = e.target
    setFormData((prev) => ({
      ...prev,
      [name]: type === 'checkbox' ? checked : value,
    }))
  }

  const validate = () => {
    const newErrors = {}
    if (!formData.plotId) newErrors.plotId = 'Plot ID is required'
    if (!formData.farmerId) newErrors.farmerId = 'Farmer is required'
    if (!formData.area) newErrors.area = 'Area is required'
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
      <h2>{plot ? 'Edit Farm Plot' : 'Add New Farm Plot'}</h2>

      <div className="form-group">
        <label htmlFor="plotId">Plot ID *</label>
        <input
          type="number"
          id="plotId"
          name="plotId"
          value={formData.plotId}
          onChange={handleChange}
          disabled={!!plot}
          required
        />
        {errors.plotId && <span className="error">{errors.plotId}</span>}
      </div>

      <div className="form-group">
        <label htmlFor="farmerId">Farmer *</label>
        <select
          id="farmerId"
          name="farmerId"
          value={formData.farmerId}
          onChange={handleChange}
          required
        >
          <option value="">Select Farmer</option>
          {farmers.map((farmer) => (
            <option key={farmer.farmerId} value={farmer.farmerId}>
              {farmer.name} (ID: {farmer.farmerId})
            </option>
          ))}
        </select>
        {errors.farmerId && <span className="error">{errors.farmerId}</span>}
      </div>

      <div className="form-group">
        <label htmlFor="area">Area (acres) *</label>
        <input
          type="number"
          id="area"
          name="area"
          value={formData.area}
          onChange={handleChange}
          step="0.01"
          required
        />
        {errors.area && <span className="error">{errors.area}</span>}
      </div>

      <div className="form-group">
        <label htmlFor="soilType">Soil Type</label>
        <select id="soilType" name="soilType" value={formData.soilType} onChange={handleChange}>
          <option value="">Select Soil Type</option>
          <option value="Clay">Clay</option>
          <option value="Sandy">Sandy</option>
          <option value="Loamy">Loamy</option>
          <option value="Silty">Silty</option>
          <option value="Peaty">Peaty</option>
        </select>
      </div>

      <div className="form-group">
        <label htmlFor="irrigationType">Irrigation Type</label>
        <select
          id="irrigationType"
          name="irrigationType"
          value={formData.irrigationType}
          onChange={handleChange}
        >
          <option value="">Select Irrigation Type</option>
          <option value="Drip">Drip Irrigation</option>
          <option value="Sprinkler">Sprinkler</option>
          <option value="Flood">Flood</option>
          <option value="Rainfed">Rain-fed</option>
        </select>
      </div>

      <div className="form-group">
        <label htmlFor="status">Status</label>
        <select id="status" name="status" value={formData.status} onChange={handleChange}>
          <option value="Active">Active</option>
          <option value="Inactive">Inactive</option>
          <option value="Fallow">Fallow</option>
        </select>
      </div>

      <div className="form-group checkbox">
        <input
          type="checkbox"
          id="fertilizerUsed"
          name="fertilizerUsed"
          checked={formData.fertilizerUsed}
          onChange={handleChange}
        />
        <label htmlFor="fertilizerUsed">Fertilizer Used</label>
      </div>

      <div className="form-actions">
        <button type="submit" className="btn btn-primary">
          {plot ? 'Update Plot' : 'Add Plot'}
        </button>
        <button type="button" className="btn btn-secondary" onClick={onCancel}>
          Cancel
        </button>
      </div>
    </form>
  )
}

export default FarmPlotForm
