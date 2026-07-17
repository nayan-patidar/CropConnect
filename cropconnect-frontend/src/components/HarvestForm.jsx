import { useState, useEffect } from 'react'
import './Form.css'

function HarvestForm({ harvest, farmPlots, onSubmit, onCancel }) {
  const [formData, setFormData] = useState({
    harvestId: '',
    plotId: '',
    cropId: '',
    harvestDate: '',
    quantity: '',
    qualityRating: '',
    weatherImpact: 'Normal',
    status: 'Completed',
  })
  const [errors, setErrors] = useState({})

  useEffect(() => {
    if (harvest) {
      setFormData(harvest)
    }
  }, [harvest])

  const handleChange = (e) => {
    const { name, value } = e.target
    setFormData((prev) => ({
      ...prev,
      [name]: value,
    }))
  }

  const validate = () => {
    const newErrors = {}
    if (!formData.harvestId) newErrors.harvestId = 'Harvest ID is required'
    if (!formData.plotId) newErrors.plotId = 'Plot is required'
    if (!formData.cropId) newErrors.cropId = 'Crop ID is required'
    if (!formData.harvestDate) newErrors.harvestDate = 'Harvest date is required'
    if (!formData.quantity) newErrors.quantity = 'Quantity is required'
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
      <h2>{harvest ? 'Edit Harvest Record' : 'Record New Harvest'}</h2>

      <div className="form-group">
        <label htmlFor="harvestId">Harvest ID *</label>
        <input
          type="number"
          id="harvestId"
          name="harvestId"
          value={formData.harvestId}
          onChange={handleChange}
          disabled={!!harvest}
          required
        />
        {errors.harvestId && <span className="error">{errors.harvestId}</span>}
      </div>

      <div className="form-group">
        <label htmlFor="plotId">Farm Plot *</label>
        <select
          id="plotId"
          name="plotId"
          value={formData.plotId}
          onChange={handleChange}
          required
        >
          <option value="">Select Farm Plot</option>
          {farmPlots.map((plot) => (
            <option key={plot.plotId} value={plot.plotId}>
              Plot {plot.plotId}
            </option>
          ))}
        </select>
        {errors.plotId && <span className="error">{errors.plotId}</span>}
      </div>

      <div className="form-group">
        <label htmlFor="cropId">Crop ID *</label>
        <input
          type="number"
          id="cropId"
          name="cropId"
          value={formData.cropId}
          onChange={handleChange}
          required
        />
        {errors.cropId && <span className="error">{errors.cropId}</span>}
      </div>

      <div className="form-group">
        <label htmlFor="harvestDate">Harvest Date *</label>
        <input
          type="date"
          id="harvestDate"
          name="harvestDate"
          value={formData.harvestDate}
          onChange={handleChange}
          required
        />
        {errors.harvestDate && <span className="error">{errors.harvestDate}</span>}
      </div>

      <div className="form-group">
        <label htmlFor="quantity">Quantity (kg) *</label>
        <input
          type="number"
          id="quantity"
          name="quantity"
          value={formData.quantity}
          onChange={handleChange}
          step="0.01"
          required
        />
        {errors.quantity && <span className="error">{errors.quantity}</span>}
      </div>

      <div className="form-group">
        <label htmlFor="qualityRating">Quality Rating (1-10)</label>
        <input
          type="number"
          id="qualityRating"
          name="qualityRating"
          value={formData.qualityRating}
          onChange={handleChange}
          min="1"
          max="10"
        />
      </div>

      <div className="form-group">
        <label htmlFor="weatherImpact">Weather Impact</label>
        <select
          id="weatherImpact"
          name="weatherImpact"
          value={formData.weatherImpact}
          onChange={handleChange}
        >
          <option value="Normal">Normal</option>
          <option value="Positive">Positive</option>
          <option value="Negative">Negative</option>
          <option value="Severe">Severe</option>
        </select>
      </div>

      <div className="form-group">
        <label htmlFor="status">Status</label>
        <select id="status" name="status" value={formData.status} onChange={handleChange}>
          <option value="Completed">Completed</option>
          <option value="Pending">Pending</option>
          <option value="Failed">Failed</option>
        </select>
      </div>

      <div className="form-actions">
        <button type="submit" className="btn btn-primary">
          {harvest ? 'Update Harvest' : 'Record Harvest'}
        </button>
        <button type="button" className="btn btn-secondary" onClick={onCancel}>
          Cancel
        </button>
      </div>
    </form>
  )
}

export default HarvestForm
