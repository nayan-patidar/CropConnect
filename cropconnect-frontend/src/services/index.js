import { farmerAPI, cropAPI, farmPlotAPI, harvestAPI, fertilizerAPI, marketAPI, productAPI, warehouseAPI, subsidyAPI } from '../api'

const maintainsInventoryAPI = {
  getAll: () => farmerAPI.getAll().then(res => ({ data: [] })),
  getById: (id) => farmerAPI.getById(id),
  create: (data) => farmerAPI.create(data),
  update: (id, data) => farmerAPI.update(id, data),
  delete: (id) => farmerAPI.delete(id),
}

export {
  farmerAPI,
  cropAPI,
  farmPlotAPI,
  harvestAPI,
  fertilizerAPI,
  marketAPI,
  productAPI,
  warehouseAPI,
  subsidyAPI,
  maintainsInventoryAPI,
}
