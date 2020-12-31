import api from '../services/api';

const getAll = () => api.get(`${api.url.products}/getAllProducts`).then((res) => res.data);
const Find = (name) => api.get(`${api.url.products}/find?searchText=${name}`).then((res) => res.data);
const get = (id) => api.get(`${api.url.products}/${id}`).then((res) => res.data);
const add = (formData) => {
	return api
		.post(api.url.products, formData, {
			headers: {
				'Content-Type': 'multipart/form-data',
			},
		})
		.then((res) => res.data);
};
const update = (id, code, name, proTypeId, unit, price, status, description) => {
	const formData = new FormData();
	formData.append('code', code);
	formData.append('name', name);
	formData.append('proTypeId', proTypeId);
	formData.append('unit', unit);
	formData.append('price', Number(price));
	formData.append('status', status);
	formData.append('description', description);
	return api
		.put(`${api.url.products}/updateProductWTI/${id}`, formData, {
			headers: {
				'Content-Type': 'multipart/form-data',
			},
		})
		.then((res) => res.data);
};

const updateWithPhoto = (id, code, name, proTypeId, unit, price, status, description, photo) => {
	const formData = new FormData();
	formData.append('code', code);
	formData.append('name', name);
	formData.append('proTypeId', proTypeId);
	formData.append('unit', unit);
	formData.append('price', Number(price));
	formData.append('status', status);
	formData.append('description', description);
	formData.append('photo', photo);
	return api
		.put(`${api.url.products}/updateProduct/${id}`, formData, {
			headers: {
				'Content-Type': 'multipart/form-data',
			},
		})
		.then((res) => res.data);
};
const remove = (id) => api.delete(`${api.url.products}/${id}`).then((res) => res.data);

const productService = {
	getAll,
	get,
	add,
	Find,
	update,
	updateWithPhoto,
	remove,
};

export default productService;
