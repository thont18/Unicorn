import React, { Component } from "react";

class Footer extends Component {
  constructor(props) {
    super(props);

    this.state = {};
  }
  render() {
    return (
      <footer className="bg-primary mt-3 text-light">
        <div className="text-center py-3">
          {" "}
          &copy; 2020 Copyright by Unicorn - All right reserved
        </div>
      </footer>
    );
  }
}

export default Footer;
