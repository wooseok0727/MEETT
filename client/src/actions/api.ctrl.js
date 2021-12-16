import axios from "axios";

const api = axios.create();

api.defaults.baseURL = "http://localhost:8080/meett";

api.interceptors.request.use(
  (config) => {
    const token = localStorage.getItem("token");
    if (token) {
      config.headers["Authorization"] = "Bearer " + token;
    }
    return config;
  },
  (error) => {
    console.log(error);
    Promise.reject(error);
  }
);

export default api;
