<<<<<<< HEAD
import Welcome from './containers/Welcome';

import ProductTypeList from './pages/ProductTypeList';
import PositionList from './pages/PositionList';
import EmployeeList from './pages/EmployeeList';

import ProductList from './pages/products/ProductList';
import Order from './pages/order';
import Bill from './pages/Bill';
import BillDetail from './pages/BillDetail';
=======
import Welcome from "./containers/Welcome";
<<<<<<< HEAD
import ProductTypeList from "./pages/ProductTypeList";
import PositionList from "./pages/PositionList";
import EmployeeList from "./pages/EmployeeList";
=======
import ProductTypeList from "./pages/product-types/ProductTypeList";
import ProductList from "./pages/products/ProductList";
<<<<<<< HEAD
import WorkingSiteList from "./pages/working-sites/workingSiteList";
=======
import BangTinh from "./pages/bangtinh";
import Menu from "./pages/menu";
import Tabs from "./pages/tabs";
import RoomTables from "./pages/roomtables";
import ControlledTabs from "./pages/hoadon";
>>>>>>> 8f4859ad739749e36073266c8243523d11bc0a09
>>>>>>> 717a06e9612174598764008560ed6cefee22f001
>>>>>>> 4d18ed23e216c987e5935301608c363267c7ed93

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

<<<<<<< HEAD
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
=======
  {
    path: "/employees",
    exact: true,
    name: "Employees",
    component: EmployeeList,
  },
=======
  {
    path: "/products",
    exact: true,
    name: "Product",
    component: ProductList,
  },{
    path: "/workingSites",
    exact: true,
    name: "Working Sites",
    component: WorkingSiteList,
  },
  { path: "/bangtinh", exact: true, name: "BangTinh", component: BangTinh },
  { path: "/menu", exact: true, name: "Menu", component: Menu },
  { path: "/tabs", exact: true, name: "Tabs", component: Tabs },
  { path: "/tab", exact: true, name: "Tab", component: ControlledTabs },
  { path: "/roomtables", exact: true, name: "RoomTables", component: RoomTables }
>>>>>>> 8f4859ad739749e36073266c8243523d11bc0a09
>>>>>>> 4d18ed23e216c987e5935301608c363267c7ed93
];

export default routes;
