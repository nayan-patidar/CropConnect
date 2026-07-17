import { useState, useEffect } from 'react'
import './Form.css'

function FertilizerForm({ fertilizer, onSubmit, onCancel }) {
  const [formData, setFormData] = useState({
    fertilizerId: '',
    name: '',
    type: '',
    npkRatio: '',
    costPerUnit: '',
    stock: '',
    supplier: '',
    expiryDate: '',
  })
  const [errors, setErrors] = useState({})

  useEffect(() => {
    if (fertilizer) {
      setFormData(fertilizer)
    }
  }, [fertilizer])

  const handleChange = (e) => {
    const { name, value } = e.target
    setFormData((prev) => ({
      ...prev,
      [name]: value,
    }))
  }

  const validate = () => {
    const newErrors = {}
    if (!formData.fertilizerId) newErrors.fertilizerId = 'Fertilizer ID is required'
    if (!formData.name) newErrors.name = 'Name is required'
    if (!formData.type) newErrors.type = 'Type is required'
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
      <h2>{fertilizer ? 'Edit Fertilizer' : 'Add New Fertilizer'}</h2>

      <div className="form-group">
        <label htmlFor="fertilizerId">Fertilizer ID *</label>
        <input
          type="number"
          id="fertilizerId"
          name="fertilizerId"
          value={formData.fertilizerId}
          onChange={handleChange}
          disabled={!!fertilizer}
          required
        />
        {errors.fertilizerId && <span className="error">{errors.fertilizerId}</span>}
      </div>

      <div className="form-group">
        <label htmlFor="name">Fertilizer Name *</label>
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
        <label htmlFor="type">Type *</label>
        <select id="type" name="type" value={formData.type} onChange={handleChange} required>
          <option value="">Select Type</option>
          <option value="Organic">Organic</option>
          <option value="Inorganic">Inorganic</option>
          <option value="Bio">Bio-fertilizer</option>
          <option value="Mixed">Mixed</option>
        </select>
        {errors.type && <span className="error">{errors.type}</span>}
      </div>

      <div className="form-group">
        <label htmlFor="npkRatio">NPK Ratio</label>
        <input
          type="text"
          id="npkRatio"
          name="npkRatio"
          value={formData.npkRatio}
          onChange={handleChange}
          placeholder="e.g., 10:26:26"
        />
      </div>

      <div className="form-group">
        <label htmlFor="costPerUnit">Cost per Unit (₹)</label>
        <input
          type="number"
          id="costPerUnit"
          name="costPerUnit"
          value={formData.costPerUnit}
          onChange={handleChange}
          step="0.01"
        />
      </div>

      <div className="form-group">
        <label htmlFor="stock">Stock (units)</label>
        <input
          type="number"
          id="stock"
          name="stock"
          value={formData.stock}
          onChange={handleChange}
        />
      </div>

      <div className="form-group">
        <label htmlFor="supplier">Supplier</label>
        <input
          type="text"
          id="supplier"
          name="supplier"
          value={formData.supplier}
          onChange={handleChange}
        />
      </div>

      <div className="form-group">
        <label htmlFor="expiryDate">Expiry Date</label>
        <input
          type="date"
          id="expiryDate"
          name="expiryDate"
          value={formData.expiryDate}
          onChange={handleChange}
        />
      </div>

      <div className="form-actions">
        <button type="submit" className="btn btn-primary">
          {fertilizer ? 'Update Fertilizer' : 'Add Fertilizer'}
        </button>
        <button type="button" className="btn btn-secondary" onClick={onCancel}>
          Cancel
        </button>
      </div>
    </form>
  )
}

export default FertilizerForm
