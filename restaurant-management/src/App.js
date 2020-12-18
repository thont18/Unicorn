import "./App.css";
import React from "react";
import DefaultLayout from "./containers/DefaultLayout";
import { Route, Switch } from "react-router-dom";

function App() {
  return (
    <Switch>
      <Route path="/" name="Home" component={DefaultLayout} />
    </Switch>
  );
}

export default App;
