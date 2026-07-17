import api from "./api";

export const getFarmers = () => api.get("/farmers");

export const getFarmerById = (id) =>
  api.get(`/farmers/${id}`);

export const addFarmer = (farmer) =>
  api.post("/farmers", farmer);

export const updateFarmer = (id, farmer) =>
  api.put(`/farmers/${id}`, farmer);

export const deleteFarmer = (id) =>
  api.delete(`/farmers/${id}`);