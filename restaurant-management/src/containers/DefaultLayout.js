import React, { Component, Fragment } from "react";
import Header from "./header";
// import Home from "../pages/home";
import routes from "../routes";
import { Switch, Route } from "react-router-dom";

class DefaultLayout extends Component {
  state = {};
  render() {
    return (
      <Fragment>
        <Header />
        <Switch>
          {routes.map((route, idx) => {
            return route.component ? (
              <Route
                key={idx}
                path={route.path}
                exact={route.exact}
                name={route.name}
                component={route.component}
              />
            ) : null;
          })}
        </Switch>
        {/* <Home /> */}
      </Fragment>
    );
  }
}

export default DefaultLayout;
