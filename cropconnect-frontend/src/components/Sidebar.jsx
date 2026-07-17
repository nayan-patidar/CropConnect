import { Link } from "react-router-dom";
import {
  FaHome,
  FaUsers,
  FaLeaf,
  FaWarehouse,
  FaStore,
  FaBox,
  FaSeedling,
  FaMoneyBill
} from "react-icons/fa";

export default function Sidebar() {

  const menus = [
    { name: "Dashboard", path: "/", icon: <FaHome /> },
    { name: "Farmers", path: "/farmers", icon: <FaUsers /> },
    { name: "Crops", path: "/crops", icon: <FaLeaf /> },
    { name: "Farm Plots", path: "/farmplots", icon: <FaSeedling /> },
    { name: "Products", path: "/products", icon: <FaBox /> },
    { name: "Warehouses", path: "/warehouses", icon: <FaWarehouse /> },
    { name: "Markets", path: "/markets", icon: <FaStore /> },
    { name: "Harvest", path: "/harvests", icon: <FaLeaf /> },
    { name: "Subsidies", path: "/subsidies", icon: <FaMoneyBill /> },
  ];

  return (
    <div className="w-64 bg-green-700 text-white">

      <h1 className="text-2xl font-bold text-center py-6">
        CropConnect
      </h1>

      {menus.map((item) => (

        <Link
          key={item.name}
          to={item.path}
          className="flex items-center gap-3 px-6 py-4 hover:bg-green-800 transition"
        >
          {item.icon}
          {item.name}
        </Link>

      ))}

    </div>
  );
}