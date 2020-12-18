import api from "../services/api";

const getAll = () => api.get(api.url.productTypes).then((res) => res.data);
const get = (id) =>
  api.get(`${api.url.productTypes}/${id}`).then((res) => res.data);
const add = (data) =>
  api.post(api.url.productTypes, data).then((res) => res.data);
const update = (id, data) =>
  api.put(`${api.url.productTypes}/${id}`, data).then((res) => res.data);
const remove = (id) =>
  api.delete(`${api.url.productTypes}/${id}`).then((res) => res.data);

const productTypeService = {
  getAll,
  get,
  add,
  update,
  remove,
};

export default productTypeService;
