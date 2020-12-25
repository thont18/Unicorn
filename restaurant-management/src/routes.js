import Welcome from "./containers/Welcome";
import ProductTypeList from "./pages/product-types/ProductTypeList";
import ProductList from "./pages/products/ProductList";

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
];

export default routes;
