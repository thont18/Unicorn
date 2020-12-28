import Welcome from "./containers/Welcome";
import ProductTypeList from "./pages/product-types/ProductTypeList";
import ProductList from "./pages/products/ProductList";
import BangTinh from "./pages/bangtinh";
import Menu from "./pages/menu";
import Tabs from "./pages/tabs";
import RoomTables from "./pages/roomtables";
import ControlledTabs from "./pages/hoadon";

const routes = [
  { path: "/", exact: true, name: "Home", component: Welcome },
  { path: "/home", exact: true, name: "Home", component: Welcome },
  {
    path: "/productTypes",
    exact: true,
    name: "Product Types",
    component: ProductTypeList,
  },
  {
    path: "/products",
    exact: true,
    name: "Product",
    component: ProductList,
  },
  { path: "/bangtinh", exact: true, name: "BangTinh", component: BangTinh },
  { path: "/menu", exact: true, name: "Menu", component: Menu },
  { path: "/tabs", exact: true, name: "Tabs", component: Tabs },
  { path: "/tab", exact: true, name: "Tab", component: ControlledTabs },
  { path: "/roomtables", exact: true, name: "RoomTables", component: RoomTables }
];

export default routes;
