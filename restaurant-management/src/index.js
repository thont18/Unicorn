import React from "react";
import ReactDOM from "react-dom";
import "./index.css";
import App from "./App";
import reportWebVitals from "./reportWebVitals";

import "../node_modules/bootstrap/dist/css/bootstrap.min.css";
import "../node_modules/@fortawesome/fontawesome-free/css/all.min.css";
import $ from "jquery";
import Popper from "popper.js";
import "../node_modules/bootstrap/dist/js/bootstrap.bundle.min";
import { BrowserRouter } from "react-router-dom";

<<<<<<< HEAD
// add react toastify 
import { toast } from "react-toastify";
import "react-toastify/dist/ReactToastify.css";

=======
import { toast } from "react-toastify";
import "react-toastify/dist/ReactToastify.css";

// REDUX
import { Provider } from "react-redux";
//import store from "./store";
import store from "./config/store";

>>>>>>> 8f4859ad739749e36073266c8243523d11bc0a09
toast.configure({
  autoClose: 3000,
  draggable: false,
  position: "top-right",
  hideProgressBar: false,
  newestOnTop: true,
  closeOnClick: true,
  rtl: false,
  pauseOnFocusLoss: true,
  pauseOnHover: true,
});

ReactDOM.render(
  <Provider store={store}>
    <BrowserRouter>
      <App />
    </BrowserRouter>
  </Provider>,
  document.getElementById("root")
);

// If you want to start measuring performance in your app, pass a function
// to log results (for example: reportWebVitals(console.log))
// or send to an analytics endpoint. Learn more: https://bit.ly/CRA-vitals
reportWebVitals();
