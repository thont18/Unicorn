import api from '../services/api';

const getAll = () => api.get(`${api.url.tables}`).then((res) => res.data);
const get = (id) => api.get(`${api.url.tables}/${id}`).then((res) => res.data);
const add = (data) => api.post(api.url.tables, data).then((res) => res.data);
const updateStatus = (id, data) => api.put(`${api.url.tables}/put/${id}`, data).then((res) => res.data);
const update = (id, data) => api.put(`${api.url.tables}/${id}`, data).then((res) => res.data);
const remove = (id) => api.delete(`${api.url.tables}/${id}`).then((res) => res.data);

const TableService = {
	getAll,
	updateStatus,
	get,
	add,
	update,
	remove,
};

export default TableService;
