import { BrowserRouter as Router, Routes, Route } from 'react-router-dom'
import Navbar from './components/Navbar'
import Home from './pages/Home'
import FarmerDashboard from './pages/FarmerDashboard'
import CropManagement from './pages/CropManagement'
import FarmPlotManagement from './pages/FarmPlotManagement'
import HarvestManagement from './pages/HarvestManagement'
import MarketplaceHome from './pages/MarketplaceHome'
import './App.css'

function App() {
  return (
    <Router>
      <div className="app">
        <Navbar />
        <main className="main-content">
          <Routes>
            <Route path="/" element={<Home />} />
            <Route path="/farmers" element={<FarmerDashboard />} />
            <Route path="/crops" element={<CropManagement />} />
            <Route path="/farm-plots" element={<FarmPlotManagement />} />
            <Route path="/harvest" element={<HarvestManagement />} />
            <Route path="/marketplace" element={<MarketplaceHome />} />
          </Routes>
        </main>
      </div>
    </Router>
  )
}

export default App
