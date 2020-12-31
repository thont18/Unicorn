import Welcome from './containers/Welcome';

import ProductTypeList from './pages/ProductTypeList';
import PositionList from './pages/PositionList';
import EmployeeList from './pages/EmployeeList';

import ProductList from './pages/products/ProductList';
import Order from './pages/order';
import Bill from './pages/Bill';
import BillDetail from './pages/BillDetail';

const routes = [
	{ path: '/', exact: true, name: 'Home', component: Welcome },
	{ path: '/order', exact: true, name: 'Order', component: Order },
	{
		path: '/productTypes',
		exact: true,
		name: 'Product Types',
		component: ProductTypeList,
	},

	{
		path: '/positions',
		exact: true,
		name: 'Positons',
		component: PositionList,
	},

	{
		path: '/employees',
		exact: true,
		name: 'Employees',
		component: EmployeeList,
	},
	{
		path: '/products',
		exact: true,
		name: 'Product',
		component: ProductList,
	},
	{
		path: '/bills',
		exact: true,
		name: 'Bill',
		component: Bill,
	},
	{
		path: '/billDetail:billId',
		exact: true,
		name: 'billDetail',
		component: BillDetail,
	},
];

export default routes;
