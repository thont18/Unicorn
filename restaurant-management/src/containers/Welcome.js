import React, { Component } from "react";
import { Container, Jumbotron } from "react-bootstrap";

class Welcome extends Component {
  state = {};
  render() {
    return (
      <Container className="mt-3">
        <Jumbotron className="bg-aqua text-dark text-center">
          <h1>Hello, welcome my Unicorn !!!</h1>
          <p>
            This is a simple hero unit, a simple jumbotron-style component for
            calling extra attention to featured content or information.
          </p>
        </Jumbotron>
      </Container>
    );
  }
}

export default Welcome;
