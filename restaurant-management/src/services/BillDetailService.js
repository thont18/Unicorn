import api from '../services/api';

const getAll = () => api.get(api.url.billDetail).then((res) => res.data);
const get = (id) => api.get(`${api.url.billDetail}/${id}`).then((res) => res.data);
const getById = (billId, productId) =>
	api.get(`${api.url.billDetail}/?billId=${billId}&productId=${productId}`).then((res) => res.data);
const add = (data, billId, productId) =>
	api.post(`${api.url.billDetail}/${billId}_${productId}`, data).then((res) => res.data);

const update = (billId, productId, data) =>
	api.put(`${api.url.billDetail}/${billId}_${productId}`, data).then((res) => res.data);
const remove = (billId, productId) =>
	api.delete(`${api.url.billDetail}/${billId}_${productId}`).then((res) => res.data);

const billDetailService = {
	getAll,
	getById,

	get,
	add,
	update,
	remove,
};
export default billDetailService;
