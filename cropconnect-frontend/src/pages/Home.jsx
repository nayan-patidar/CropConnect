import { Link } from 'react-router-dom'
import { FaUsers, FaCrop, FaLeaf, FaWarehouse, FaShoppingCart, FaChartBar } from 'react-icons/fa'
import './Home.css'

function Home() {
  const features = [
    {
      icon: <FaUsers />,
      title: 'Farmer Management',
      description: 'Manage farmer profiles, contact information, and land ownership details.',
      link: '/farmers',
    },
    {
      icon: <FaCrop />,
      title: 'Crop Management',
      description: 'Track crops, varieties, seasons, and growing duration.',
      link: '/crops',
    },
    {
      icon: <FaLeaf />,
      title: 'Farm Plots',
      description: 'Manage individual farm plots and their growing operations.',
      link: '/farm-plots',
    },
    {
      icon: <FaChartBar />,
      title: 'Harvest Tracking',
      description: 'Record and monitor harvest data and yields.',
      link: '/harvest',
    },
    {
      icon: <FaWarehouse />,
      title: 'Inventory & Warehouse',
      description: 'Manage storage facilities and product inventory.',
      link: '/marketplace',
    },
    {
      icon: <FaShoppingCart />,
      title: 'Marketplace',
      description: 'Connect with markets and manage product sales.',
      link: '/marketplace',
    },
  ]

  return (
    <div className="home">
      <section className="hero">
        <div className="hero-content">
          <h1>Welcome to CropConnect</h1>
          <p>Your comprehensive platform for crop farming management</p>
          <Link to="/farmers" className="btn btn-primary btn-large">
            Get Started
          </Link>
        </div>
      </section>

      <section className="features">
        <h2>Our Features</h2>
        <div className="features-grid">
          {features.map((feature, index) => (
            <Link key={index} to={feature.link} className="feature-card">
              <div className="feature-icon">{feature.icon}</div>
              <h3>{feature.title}</h3>
              <p>{feature.description}</p>
            </Link>
          ))}
        </div>
      </section>

      <section className="about">
        <h2>About CropConnect</h2>
        <div className="about-content">
          <p>
            CropConnect is an innovative platform designed to streamline agricultural operations
            and connect farmers with markets. Our system helps manage every aspect of crop farming,
            from planning and cultivation to harvest and marketplace engagement.
          </p>
          <p>
            With our comprehensive tools, farmers can optimize their yields, reduce costs,
            and maximize profits while maintaining sustainable farming practices.
          </p>
        </div>
      </section>
    </div>
  )
}

export default Home
