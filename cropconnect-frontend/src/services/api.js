import axios from 'axios'

const API_BASE_URL = 'http://localhost:8080'

const api = axios.create({
  baseURL: API_BASE_URL,
  headers: {
    'Content-Type': 'application/json',
  },
})

// Farmer API calls
export const farmerAPI = {
  getAll: () => api.get('/farmers'),
  getById: (id) => api.get(`/farmers/${id}`),
  create: (data) => api.post('/farmers', data),
  update: (id, data) => api.put(`/farmers/${id}`, data),
  delete: (id) => api.delete(`/farmers/${id}`),
}

// Crop API calls
export const cropAPI = {
  getAll: () => api.get('/api/crops'),
  getById: (id) => api.get(`/api/crops/${id}`),
  create: (data) => api.post('/api/crops', data),
  update: (id, data) => api.put(`/api/crops/${id}`, data),
  delete: (id) => api.delete(`/api/crops/${id}`),
}

// FarmPlot API calls
export const farmPlotAPI = {
  getAll: () => api.get('/farm-plots'),
  getById: (id) => api.get(`/farm-plots/${id}`),
  create: (data) => api.post('/farm-plots', data),
  update: (id, data) => api.put(`/farm-plots/${id}`, data),
  delete: (id) => api.delete(`/farm-plots/${id}`),
}

// Harvest API calls
export const harvestAPI = {
  getAll: () => api.get('/harvest'),
  getById: (id) => api.get(`/harvest/${id}`),
  create: (data) => api.post('/harvest', data),
  update: (id, data) => api.put(`/harvest/${id}`, data),
  delete: (id) => api.delete(`/harvest/${id}`),
}

// Fertilizer API calls
export const fertilizerAPI = {
  getAll: () => api.get('/fertilizers'),
  getById: (id) => api.get(`/fertilizers/${id}`),
  create: (data) => api.post('/fertilizers', data),
  update: (id, data) => api.put(`/fertilizers/${id}`, data),
  delete: (id) => api.delete(`/fertilizers/${id}`),
}

// Market API calls
export const marketAPI = {
  getAll: () => api.get('/markets'),
  getById: (id) => api.get(`/markets/${id}`),
  create: (data) => api.post('/markets', data),
  update: (id, data) => api.put(`/markets/${id}`, data),
  delete: (id) => api.delete(`/markets/${id}`),
}

// Product API calls
export const productAPI = {
  getAll: () => api.get('/products'),
  getById: (id) => api.get(`/products/${id}`),
  create: (data) => api.post('/products', data),
  update: (id, data) => api.put(`/products/${id}`, data),
  delete: (id) => api.delete(`/products/${id}`),
}

// Warehouse API calls
export const warehouseAPI = {
  getAll: () => api.get('/warehouses'),
  getById: (id) => api.get(`/warehouses/${id}`),
  create: (data) => api.post('/warehouses', data),
  update: (id, data) => api.put(`/warehouses/${id}`, data),
  delete: (id) => api.delete(`/warehouses/${id}`),
}

// Subsidy API calls
export const subsidyAPI = {
  getAll: () => api.get('/subsidies'),
  getById: (id) => api.get(`/subsidies/${id}`),
  create: (data) => api.post('/subsidies', data),
  update: (id, data) => api.put(`/subsidies/${id}`, data),
  delete: (id) => api.delete(`/subsidies/${id}`),
}

export default api
