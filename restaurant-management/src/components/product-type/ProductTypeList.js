/* eslint-disable jsx-a11y/anchor-is-valid */
import React, { Component, Fragment } from "react";
import ProductTypeService from "../../services/ProductTypeService";

class ProductTypeList extends Component {
  constructor(props) {
    super(props);

    this.state = {
      productTypes: [],
    };
  }

  componentDidMount() {
    ProductTypeService.getProductTypes().then((res) => {
      this.setState({
        productTypes: res.data,
      });
    });
  }

  viewProductType(id) {
    this.props.history.push(`/productType/${id}`);
  }

  addProductType() {
    window.localStorage.removeItem("productTypeId");
    this.props.history.push("/addProductType");
  }

  editProductType(id) {
    window.localStorage.setItem("productTypeId", id);
    this.props.history.push(`/editProductType/${id}`);
  }

  deleteProductType(id) {
    ProductTypeService.deleteProductType(id).then((res) => {
      this.setState({
        productTypes: this.state.productTypes.filter((type) => type.id !== id),
      });
    });
  }

  render() {
    return (
      <Fragment>
        <div className="container">
          <div className="container pt-4">
            <div
              className="card border-primary"
              style={{ borderTopWidth: "5px", color: "#007bff" }}
            >
              <div className="card-header">
                <div className="row">
                  <div className="col">
                    <h2 className="card-title">Product Type List</h2>
                  </div>
                  <div className="col-auto">
                    <button
                      onClick={() => this.addProductType()}
                      className="btn btn-primary"
                      data-toggle="modal"
                      data-target="#addModal"
                    >
                      <i className="fas fa-plus-circle mr-1"></i>
                      Add
                    </button>
                  </div>
                </div>
              </div>
              <div className="card-body">
                <table className="table table-hover table-striped table-bordered text-center">
                  <thead>
                    <tr className="table-primary">
                      <th scope="col">No</th>
                      <th scope="col">Name</th>
                      <th scope="col">Description</th>
                      <th scope="col">Actions</th>
                    </tr>
                  </thead>
                  <tbody>
                    {this.state.productTypes.map((type, idx) => (
                      <tr key={type.id}>
                        <th scope="row">{idx + 1}</th>
                        <td>{type.name} </td>
                        <td>{type.description}</td>
                        <td>
                          <button
                            onClick={() => this.editProductType(type.id)}
                            className="btn btn-success"
                          >
                            <i className="fas fa-edit text-light"></i>
                          </button>
                          <button
                            style={{ marginLeft: "10px" }}
                            onClick={() => this.deleteProductType(type.id)}
                            className="btn btn-danger"
                          >
                            <i className="fas fa-trash-alt text-light"></i>
                          </button>
                        </td>
                      </tr>
                    ))}
                  </tbody>
                </table>
              </div>
            </div>
          </div>
        </div>
      </Fragment>
    );
  }
}

export default ProductTypeList;
