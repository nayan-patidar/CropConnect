import axios from "axios";

const api = axios.create({
  baseURL: "http://localhost:8080/api", // Change if your backend uses another base path
  headers: {
    "Content-Type": "application/json",
  },
});

export default api;