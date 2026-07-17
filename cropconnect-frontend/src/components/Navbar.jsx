import { useState } from 'react'
import { Link } from 'react-router-dom'
import { FaBars, FaTimes, FaLeaf } from 'react-icons/fa'
import './Navbar.css'

function Navbar() {
  const [isMenuOpen, setIsMenuOpen] = useState(false)

  const toggleMenu = () => {
    setIsMenuOpen(!isMenuOpen)
  }

  return (
    <nav className="navbar">
      <div className="navbar-container">
        <Link to="/" className="navbar-logo">
          <FaLeaf className="logo-icon" />
          CropConnect
        </Link>
        <button className="menu-toggle" onClick={toggleMenu}>
          {isMenuOpen ? <FaTimes /> : <FaBars />}
        </button>
        <ul className={`navbar-menu ${isMenuOpen ? 'active' : ''}`}>
          <li>
            <Link to="/" onClick={() => setIsMenuOpen(false)}>Home</Link>
          </li>
          <li>
            <Link to="/farmers" onClick={() => setIsMenuOpen(false)}>Farmers</Link>
          </li>
          <li>
            <Link to="/crops" onClick={() => setIsMenuOpen(false)}>Crops</Link>
          </li>
          <li>
            <Link to="/farm-plots" onClick={() => setIsMenuOpen(false)}>Farm Plots</Link>
          </li>
          <li>
            <Link to="/harvest" onClick={() => setIsMenuOpen(false)}>Harvest</Link>
          </li>
          <li>
            <Link to="/marketplace" onClick={() => setIsMenuOpen(false)}>Marketplace</Link>
          </li>
        </ul>
      </div>
    </nav>
  )
}

export default Navbar
