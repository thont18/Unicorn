import api from '../services/api';
const getAll = () => {
	api.get(api.url.bills).then((res) => res.data);
};
const get = (id) => api.get(`${api.url.bills}/${id}`).then((res) => res.data);
const getTotalPrice = (id) => api.get(`${api.url.bills}/getToTalPrice/${id}`).then((res) => res.data);
const getBillByTableId = (tableId) => api.get(`${api.url.bills}/getBill/${tableId}`).then((res) => res.data);
const add = (/*paymentDate, paymentMethod,*/ tableId /*, employeeId, promotionId*/) => {
	let formData = new FormData();
	//formData.append('paymentDate', paymentDate);
	//	formData.append('paymentMethod', paymentMethod);
	formData.append('tableId', tableId);
	//formData.append('employeeId', employeeId);
	//formData.append('promotionId', promotionId);
	return api
		.post(api.url.bills, formData, {
			headers: {
				'Content-Type': 'multipart/form-data',
			},
		})
		.then((res) => res.data);
};
const update = (id, paymentDate, paymentMethod, tableId, employeeId, promotionId, totalPrice) => {
	let formData = new FormData();
	formData.append('paymentDate', paymentDate);
	formData.append('paymentMethod', paymentMethod);
	if (tableId !== '') {
		formData.append('tableId', tableId);
	}
	if (employeeId !== '') {
		formData.append('employeeId', employeeId);
	}
	if (promotionId !== '') {
		formData.append('promotionId', promotionId);
	}
	formData.append('totalPrice', totalPrice);
	return api
		.put(`${api.url.bills}/${id}`, formData, {
			headers: {
				'Content-Type': 'multipart/form-data',
			},
		})
		.then((res) => res.data);
};

const remove = (id) => api.delete(`${api.url.bills}/${id}`).then((res) => res.data);

const billService = {
	getAll,
	getBillByTableId,
	get,
	add,
	update,
	getTotalPrice,
	remove,
};
export default billService;
