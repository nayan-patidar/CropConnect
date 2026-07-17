import MainLayout from "../../layouts/MainLayout";

export default function Dashboard() {
  return (
    <MainLayout>

      <h1 className="text-3xl font-bold mb-6">
        Dashboard
      </h1>

      <div className="grid grid-cols-4 gap-6">

        <div className="bg-white rounded-xl shadow p-6">
          Total Farmers
        </div>

        <div className="bg-white rounded-xl shadow p-6">
          Total Crops
        </div>

        <div className="bg-white rounded-xl shadow p-6">
          Warehouses
        </div>

        <div className="bg-white rounded-xl shadow p-6">
          Products
        </div>

      </div>

    </MainLayout>
  );
}