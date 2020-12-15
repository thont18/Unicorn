import "./App.css";
import { Route, Router, Switch } from "react-router-dom";
import Header from "./containers/Header";
import ProductTypeList from "./components/product-type/ProductTypeList";
import Footer from "./containers/Footer";
import AddProductType from "./components/product-type/AddProductType";
import EditProductType from "./components/product-type/EditProductType";

function App() {
  return (
    <div>
      <div className="container">
        <Header />
        <Switch>
          <Route path="/" exact component={ProductTypeList}></Route>
          <Route path="/productTypes" component={ProductTypeList}></Route>
          <Route path="/addProductType" component={AddProductType}></Route>
          {/* <Route path = "/view-employee/:id" component = {ViewEmployeeComponent}></Route> */}
          <Route
            path="/editProductType/:id"
            component={EditProductType}
          ></Route>
        </Switch>
        <Footer />
      </div>
    </div>
  );
}

export default App;
