import api from '../services/api';

const getAll = () => api.get(`${api.url.employees}/getAllEmployees`).then((res) => res.data);
const get = (id) =>
    api.get(`${api.url.employees}/${id}`).then((res) => res.data);
const add = (data) =>
    api.post(api.url.employees, data).then((res) => res.data);
const update = (id , data) =>
    api.put(`${api.url.employees}/${id}`, data).then((res) => res.data);
const remove = (id) =>
    api.delete(`${api.url.employees}/${id}`).then((res) => res.data);


const employeeservice =  {
    getAll,
    get,
    add,
    update,
    remove,
};
 
export default employeeservice;