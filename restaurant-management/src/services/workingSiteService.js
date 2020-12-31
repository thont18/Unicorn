import api from "./api";

const getAll = () =>
  api.get(`${api.url.workingSites}`).then((res) => res.data);
const get = (id) =>
  api.get(`${api.url.workingSites}/${id}`).then((res) => res.data);
const add = (data) =>
  api.post(api.url.workingSites, data).then((res) => res.data);
const update = (id, data) =>
  api.put(`${api.url.workingSites}/${id}`, data).then((res) => res.data);
const remove = (id) =>
  api.delete(`${api.url.workingSites}/${id}`).then((res) => res.data);

const workingSiteService = {
  getAll,
  get,
  add,
  update,
  remove,
};

export default workingSiteService;
