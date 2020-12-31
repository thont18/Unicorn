import axios from 'axios';

const url = {
	baseUrl: 'http://localhost:8080',
	productTypes: '/productTypes',
	positions: '/positions',
	employees: '/employees',
	bills: '/bills',
	billDetail: '/billDetail',
	products: '/products',
	tables: '/tables',
	promotion: '/promotiontypes',
};

const instance = axios.create({
	baseURL: url.baseUrl,
	headers: {
		'Content-Type': 'application/json',
		Accept: 'application/json',
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
