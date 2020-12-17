import Welcome from "./containers/Welcome";
import ProductTypeList from "./pages/product-types/ProductTypeList";

const routes = [
  { path: "/", exact: true, name: "Home", component: Welcome },
  { path: "/home", exact: true, name: "Home", component: Welcome },
  {
    path: "/productTypes",
    exact: true,
    name: "Product Types",
    component: ProductTypeList,
  },
];

export default routes;
