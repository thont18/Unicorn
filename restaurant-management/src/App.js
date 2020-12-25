import "./App.css";
import React from "react";
import DefaultLayout from "./containers/DefaultLayout";
import { Route, Switch } from "react-router-dom";
// import UploadFile from "./pages/Upload";

function App() {
  return (
    <Switch>
      <Route path="/" name="Home" component={DefaultLayout} />
    </Switch>
    // <UploadFile />
  );
}

export default App;
