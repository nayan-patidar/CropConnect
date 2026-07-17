import { useEffect, useState } from "react";
import { getFarmers } from "../../services/farmerService";
import MainLayout from "../../layouts/MainLayout";

export default function FarmerList() {
  const [farmers, setFarmers] = useState([]);

  useEffect(() => {
    loadFarmers();
  }, []);

  const loadFarmers = async () => {
    try {
      const response = await getFarmers();
      setFarmers(response.data);
    } catch (error) {
      console.error(error);
    }
  };

  return (
    <MainLayout>
      <h1 className="text-3xl font-bold mb-6">Farmers</h1>

      <table className="w-full bg-white shadow rounded-lg">
        <thead>
          <tr className="bg-green-600 text-white">
            <th className="p-3">ID</th>
            <th className="p-3">Name</th>
          </tr>
        </thead>

        <tbody>
          {farmers.map((farmer) => (
            <tr key={farmer.id} className="border-b">
              <td className="p-3">{farmer.id}</td>
              <td className="p-3">{farmer.name}</td>
            </tr>
          ))}
        </tbody>
      </table>
    </MainLayout>
  );
}