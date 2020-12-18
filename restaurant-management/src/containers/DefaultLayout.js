import React, { Component, Fragment } from "react";
import Header from "./Header";
// import Home from "../pages/home";
import routes from "../routes";
import { Switch, Route } from "react-router-dom";
import Footer from "./Footer";

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
        <Footer />
      </Fragment>
    );
  }
}

export default DefaultLayout;
