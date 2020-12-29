import api from '../services/api';

const getAll = () => api.get(`${api.url.positions}/getAllPositions`).then((res) => res.data);
const get = (id) =>
    api.get(`${api.url.positions}/${id}`).then((res) => res.data);
const add = (data) =>
    api.post(api.url.positions, data).then((res) => res.data);
const update = (id , data) =>
    api.put(`${api.url.positions}/${id}`, data).then((res) => res.data);
const remove = (id) =>
    api.delete(`${api.url.positions}/${id}`).then((res) => res.data);


const positionService =  {
    getAll,
    get,
    add,
    update,
    remove,
};
 
export default positionService;