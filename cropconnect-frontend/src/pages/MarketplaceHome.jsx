import { useState, useEffect } from 'react'
import { FaShoppingCart, FaBoxes, FaWarehouse } from 'react-icons/fa'
import { productAPI, marketAPI, warehouseAPI } from '../services/api'
import './Marketplace.css'

function MarketplaceHome() {
  const [products, setProducts] = useState([])
  const [markets, setMarkets] = useState([])
  const [warehouses, setWarehouses] = useState([])
  const [loading, setLoading] = useState(false)
  const [activeTab, setActiveTab] = useState('products')

  useEffect(() => {
    fetchData()
  }, [])

  const fetchData = async () => {
    setLoading(true)
    try {
      const [productsRes, marketsRes, warehousesRes] = await Promise.all([
        productAPI.getAll(),
        marketAPI.getAll(),
        warehouseAPI.getAll(),
      ])
      setProducts(productsRes.data)
      setMarkets(marketsRes.data)
      setWarehouses(warehousesRes.data)
    } catch (err) {
      console.error('Error fetching data:', err)
    } finally {
      setLoading(false)
    }
  }

  const renderProducts = () => (
    <div className="marketplace-section">
      <h2>
        <FaBoxes /> Products
      </h2>
      {products.length === 0 ? (
        <p>No products available</p>
      ) : (
        <div className="products-grid">
          {products.map((product) => (
            <div key={product.productId} className="product-card">
              <h3>{product.name}</h3>
              <p>Category: {product.category || '-'}</p>
              <p>Price: ₹{product.price || '-'}/unit</p>
            </div>
          ))}
        </div>
      )}
    </div>
  )

  const renderMarkets = () => (
    <div className="marketplace-section">
      <h2>
        <FaShoppingCart /> Markets
      </h2>
      {markets.length === 0 ? (
        <p>No markets available</p>
      ) : (
        <div className="markets-grid">
          {markets.map((market) => (
            <div key={market.marketId} className="market-card">
              <h3>{market.name}</h3>
              <p>Location: {market.location || '-'}</p>
              <p>Type: {market.marketType || '-'}</p>
            </div>
          ))}
        </div>
      )}
    </div>
  )

  const renderWarehouses = () => (
    <div className="marketplace-section">
      <h2>
        <FaWarehouse /> Warehouses
      </h2>
      {warehouses.length === 0 ? (
        <p>No warehouses available</p>
      ) : (
        <div className="warehouses-grid">
          {warehouses.map((warehouse) => (
            <div key={warehouse.warehouseId} className="warehouse-card">
              <h3>{warehouse.name}</h3>
              <p>Location: {warehouse.location || '-'}</p>
              <p>Capacity: {warehouse.capacity || '-'} units</p>
            </div>
          ))}
        </div>
      )}
    </div>
  )

  return (
    <div className="marketplace">
      <h1>Marketplace & Supply Chain</h1>

      <div className="tabs">
        <button
          className={`tab ${activeTab === 'products' ? 'active' : ''}`}
          onClick={() => setActiveTab('products')}
        >
          Products
        </button>
        <button
          className={`tab ${activeTab === 'markets' ? 'active' : ''}`}
          onClick={() => setActiveTab('markets')}
        >
          Markets
        </button>
        <button
          className={`tab ${activeTab === 'warehouses' ? 'active' : ''}`}
          onClick={() => setActiveTab('warehouses')}
        >
          Warehouses
        </button>
      </div>

      {loading ? (
        <div className="loading">Loading marketplace data...</div>
      ) : (
        <div className="tab-content">
          {activeTab === 'products' && renderProducts()}
          {activeTab === 'markets' && renderMarkets()}
          {activeTab === 'warehouses' && renderWarehouses()}
        </div>
      )}
    </div>
  )
}

export default MarketplaceHome
