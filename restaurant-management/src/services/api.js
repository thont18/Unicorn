import axios from "axios";

const url = {
  baseUrl: "http://localhost:8080",
  productTypes: "/productTypes",
<<<<<<< HEAD
  positions: "/positions",
  employees: "/employees",
=======
  products: "/products",
<<<<<<< HEAD
  workingSites: "/sites"
=======
  tables: "/tables",
>>>>>>> 8f4859ad739749e36073266c8243523d11bc0a09
>>>>>>> 717a06e9612174598764008560ed6cefee22f001
};

const instance = axios.create({
  baseURL: url.baseUrl,
  headers: {
    "Content-Type": "application/json",
    Accept: "application/json",
  },
});

const api = {
  url,
  instance,
  get: instance.get,
  post: instance.post,
  put: instance.put,
  delete: instance.delete,
};

export default api;
