import api from '../services/api';

const getAll = () => api.get(`${api.url.promotion}`).then((res) => res.data);
const get = (id) => api.get(`${api.url.promotion}/${id}`).then((res) => res.data);
const add = (data) => api.post(api.url.promotion, data).then((res) => res.data);
const update = (id, data) => api.put(`${api.url.promotion}/${id}`, data).then((res) => res.data);
const remove = (id) => api.delete(`${api.url.promotion}/${id}`).then((res) => res.data);

const PromotionTypeService = {
	getAll,
	get,
	add,
	update,
	remove,
};

export default PromotionTypeService;
