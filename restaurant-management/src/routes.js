import Welcome from "./containers/Welcome";
import ProductTypeList from "./pages/ProductTypeList";
import PositionList from "./pages/PositionList";
import EmployeeList from "./pages/EmployeeList";

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
    path: "/positions",
    exact: true,
    name: "Positons",
    component: PositionList,
  },

  {
    path: "/employees",
    exact: true,
    name: "Employees",
    component: EmployeeList,
  },
];

export default routes;
