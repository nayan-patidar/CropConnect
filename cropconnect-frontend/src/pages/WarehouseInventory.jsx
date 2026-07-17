import { useState, useEffect } from 'react'
import { FaWarehouse, FaBoxes, FaFilter, FaPlus } from 'react-icons/fa'
import { warehouseAPI, productAPI, maintainsInventoryAPI } from '../services/api'
import './Dashboard.css'
import './Warehouse.css'

function WarehouseInventory() {
  const [warehouses, setWarehouses] = useState([])
  const [products, setProducts] = useState([])
  const [inventory, setInventory] = useState([])
  const [loading, setLoading] = useState(false)
  const [error, setError] = useState(null)
  const [selectedWarehouse, setSelectedWarehouse] = useState('')
  const [stats, setStats] = useState({
    totalWarehouses: 0,
    totalItems: 0,
    capacityUsed: 0,
  })

  useEffect(() => {
    fetchData()
  }, [])

  const fetchData = async () => {
    setLoading(true)
    setError(null)
    try {
      const [whRes, prodRes, invRes] = await Promise.all([
        warehouseAPI.getAll(),
        productAPI.getAll(),
        maintainsInventoryAPI.getAll(),
      ])
      setWarehouses(whRes.data)
      setProducts(prodRes.data)
      setInventory(invRes.data)

      const totalItems = invRes.data.reduce((sum, inv) => sum + (inv.quantityStored || 0), 0)
      const capacityUsed = whRes.data.reduce((sum, wh) => sum + (wh.capacity || 0), 0)

      setStats({
        totalWarehouses: whRes.data.length,
        totalItems,
        capacityUsed,
      })
    } catch (err) {
      setError('Failed to load data. ' + (err.response?.data?.message || err.message))
    } finally {
      setLoading(false)
    }
  }

  const getWarehouseName = (id) => {
    const wh = warehouses.find((w) => w.warehouseId === id)
    return wh ? wh.name : 'Unknown'
  }

  const getProductName = (id) => {
    const prod = products.find((p) => p.productId === id)
    return prod ? prod.name : 'Unknown'
  }

  const filteredInventory = selectedWarehouse
    ? inventory.filter((inv) => inv.warehouseId.toString() === selectedWarehouse)
    : inventory

  return (
    <div className="dashboard">
      <div className="dashboard-header">
        <h1><FaWarehouse /> Warehouse Inventory Management</h1>
      </div>

      {error && (
        <div className="alert alert-error">
          {error}
          <button onClick={() => setError(null)} style={{ float: 'right' }}>×</button>
        </div>
      )}

      <div className="stats-grid">
        <div className="stat-card">
          <h3><FaWarehouse /> Total Warehouses</h3>
          <p className="stat-value">{stats.totalWarehouses}</p>
        </div>
        <div className="stat-card">
          <h3><FaBoxes /> Total Items Stored</h3>
          <p className="stat-value">{stats.totalItems.toLocaleString()}</p>
        </div>
        <div className="stat-card">
          <h3>Total Capacity</h3>
          <p className="stat-value">{stats.capacityUsed.toLocaleString()}</p>
        </div>
      </div>

      <div className="filters card">
        <div className="filter-group">
          <label><FaFilter /> Filter by Warehouse</label>
          <select value={selectedWarehouse} onChange={(e) => setSelectedWarehouse(e.target.value)}>
            <option value="">All Warehouses</option>
            {warehouses.map((wh) => (
              <option key={wh.warehouseId} value={wh.warehouseId}>
                {wh.name} - {wh.location}
              </option>
            ))}
          </select>
        </div>
      </div>

      {loading ? (
        <div className="loading">Loading inventory data...</div>
      ) : filteredInventory.length === 0 ? (
        <div className="empty-state">
          <p>No inventory records found.</p>
        </div>
      ) : (
        <div className="table-container">
          <table className="table">
            <thead>
              <tr>
                <th>Warehouse</th>
                <th>Product</th>
                <th>Quantity Stored</th>
                <th>Storage Date</th>
                <th>Expiry Date</th>
                <th>Storage Location</th>
                <th>Status</th>
              </tr>
            </thead>
            <tbody>
              {filteredInventory.map((inv, idx) => (
                <tr key={idx}>
                  <td>{getWarehouseName(inv.warehouseId)}</td>
                  <td>{getProductName(inv.productId)}</td>
                  <td>{inv.quantityStored?.toLocaleString() || '-'}</td>
                  <td>{new Date(inv.storageDate).toLocaleDateString()}</td>
                  <td>{inv.expiryDate ? new Date(inv.expiryDate).toLocaleDateString() : '-'}</td>
                  <td>{inv.storageLocation || '-'}</td>
                  <td>
                    <span className={`status-badge status-${inv.status?.toLowerCase() || 'active'}`}>
                      {inv.status || 'Active'}
                    </span>
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

export default WarehouseInventory
