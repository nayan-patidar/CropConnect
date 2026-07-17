import { useState, useEffect } from 'react'
import './Form.css'

function FarmerForm({ farmer, onSubmit, onCancel }) {
  const [formData, setFormData] = useState({
    farmerId: '',
    name: '',
    contact: '',
    regNo: '',
    sizeOwned: '',
  })
  const [errors, setErrors] = useState({})

  useEffect(() => {
    if (farmer) {
      setFormData(farmer)
    }
  }, [farmer])

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
    if (!formData.farmerId) newErrors.farmerId = 'Farmer ID is required'
    if (!formData.name) newErrors.name = 'Name is required'
    if (!formData.regNo) newErrors.regNo = 'Registration number is required'
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
      <h2>{farmer ? 'Edit Farmer' : 'Add New Farmer'}</h2>

      <div className="form-group">
        <label htmlFor="farmerId">Farmer ID *</label>
        <input
          type="number"
          id="farmerId"
          name="farmerId"
          value={formData.farmerId}
          onChange={handleChange}
          disabled={!!farmer}
          required
        />
        {errors.farmerId && <span className="error">{errors.farmerId}</span>}
      </div>

      <div className="form-group">
        <label htmlFor="name">Name *</label>
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
        <label htmlFor="contact">Contact</label>
        <input
          type="text"
          id="contact"
          name="contact"
          value={formData.contact}
          onChange={handleChange}
        />
      </div>

      <div className="form-group">
        <label htmlFor="regNo">Registration No. *</label>
        <input
          type="number"
          id="regNo"
          name="regNo"
          value={formData.regNo}
          onChange={handleChange}
          required
        />
        {errors.regNo && <span className="error">{errors.regNo}</span>}
      </div>

      <div className="form-group">
        <label htmlFor="sizeOwned">Land Size (acres)</label>
        <input
          type="number"
          id="sizeOwned"
          name="sizeOwned"
          value={formData.sizeOwned}
          onChange={handleChange}
          step="0.01"
        />
      </div>

      <div className="form-actions">
        <button type="submit" className="btn btn-primary">
          {farmer ? 'Update Farmer' : 'Add Farmer'}
        </button>
        <button type="button" className="btn btn-secondary" onClick={onCancel}>
          Cancel
        </button>
      </div>
    </form>
  )
}

export default FarmerForm
