import React, { Component } from "react";
import { Col, Container, Navbar } from "react-bootstrap";

export class Footer extends Component {
  render() {
    let fullYear = new Date().getFullYear();
    return (
      <Navbar bg="primary" variant="dark" className="py-4 mt-3">
        <Container>
          <Col lg={12} className="text-center text-white">
            <div>All rights reserved by &copy;Unicorn - {fullYear}</div>
          </Col>
        </Container>
      </Navbar>
    );
  }
}

export default Footer;
