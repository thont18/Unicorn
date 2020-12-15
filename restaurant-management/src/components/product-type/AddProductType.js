import React, { Component } from "react";
import ProductTypeService from "../../services/ProductTypeService";

class AddProductType extends Component {
  constructor(props) {
    super(props);

    this.state = {
      name: "",
      description: "",
      message: null,
    };
    this.saveProductType = this.saveProductType.bind(this);
  }

  saveProductType = (e) => {
    e.preventDefault();
    let type = {
      name: this.state.name,
      description: this.state.description,
    };
    ProductTypeService.createProductType(type).then((res) => {
      this.setState({ message: "Add product type successfully" });
      this.props.history.push("/productTypes");
    });
  };

  onChange = (e) => this.setState({ [e.target.name]: e.target.value });

  render() {
    return (
      <div className="container pt-4">
        <div className="row justify-content-center">
          <div className="col-lg-7">
            <div
              className="card border-primary"
              style={{ borderTopWidth: "5px", borderColor: "blue" }}
            >
              <form>
                <div className="card-header">
                  <h2 className="card-title font-weight-bolder">
                    Add Product Type
                  </h2>
                </div>
                <div className="card-body">
                  <div className="form-group row">
                    <label
                      htmlFor="name"
                      className="h5 col-sm-4 col-form-label"
                    >
                      Student Code
                    </label>
                    <div className="col-sm-8">
                      <input
                        id="name"
                        name="name"
                        type="text"
                        className="form-control"
                        placeholder="Enter name"
                        required="required"
                        value={this.state.name}
                        onChange={this.onChange}
                      />
                    </div>
                  </div>
                  <div className="form-group row">
                    <label
                      htmlFor="description"
                      className="h5 col-sm-4 col-form-label"
                    >
                      Description
                    </label>
                    <div className="col-sm-8">
                      <input
                        id="description"
                        name="description"
                        type="text"
                        className="form-control"
                        placeholder="Enter description"
                        value={this.state.description}
                        onChange={this.onChange}
                      />
                    </div>
                  </div>
                </div>
                <div className="card-footer">
                  <div className="row">
                    <div className="col-sm-4"></div>
                    <div className="col-sm-8">
                      <button
                        type="submit"
                        className="btn btn-success"
                        onClick={this.saveProductType}
                      >
                        Save
                      </button>
                    </div>
                  </div>
                </div>
              </form>
            </div>
          </div>
        </div>
      </div>
    );
  }
}

export default AddProductType;
