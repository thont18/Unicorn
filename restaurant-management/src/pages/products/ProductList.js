import React, { useState, Fragment, useEffect } from "react";
import Input from "../../controls/Input";
import { Button, FormControl, InputGroup, Modal } from "react-bootstrap";
import productService from "../../services/productService";
import { useFormik } from "formik";
import * as Yup from "yup";
import axios from "axios";
import { toast } from "react-toastify";
import productTypeService from "../../services/productTypeService";

const ProductList = (props) => {
  const [productTypes, setProductTypes] = useState([]);
  const [products, setProducts] = useState([]);
  const [currentPage, setCurrentPage] = useState(1);
  const [totalPages, setTotalPages] = useState(0);
  const [totalElements, setTotalElements] = useState(0);
  const [sortDir, setSortDir] = useState("asc");
  const [search, setSearch] = useState("");

  const [modalShow, setModalShow] = useState(false);

  // radio button
  // product status
  const handleRadioChange = (value) => {
    formik.setFieldValue("status", value);
  };

  // get product ID
  const [detail, setDetail] = useState(false);
  const handleDetailClose = () => setDetail(false);
  const [productId, setProductId] = useState(0);

  const getProduct = (e, dataId) => {
    if (e) {
      e.preventDefault();
    }
    setProductId(dataId);
    if (dataId > 0) {
      // edit
      productService.get(dataId).then((res) => {
        formik.setValues(res);
        // setDetail(true);
        console.log(res);
      });
    }
  };

  const handleModalClose = () => setModalShow(false);
  const handleModalShow = (e, dataId) => {
    if (e) {
      e.preventDefault();
    }
    // const id = parseInt(dataId);
    setProductId(dataId);
    if (dataId > 0) {
      // edit
      productService.get(dataId).then((res) => {
        console.log(res.image);
        console.log(res);
        formik.setValues({
          code: res.code,
          name: res.name,
          unit: res.unit,
          price: res.price,
          status: res.status,
          image: res.image,
          description: res.description,
          proTypeId: res.productType.id,
        });
        setImageReview(res.image);
        setModalShow(true);
      });
    } else {
      // add
      formik.resetForm();
      setModalShow(true);
    }
  };

  const loadData = () => {
    productTypeService.getAll().then((res) => {
      setProductTypes(res);
    });
    productService.getAll().then((res) => {
      setProducts(res);
    });
  };

  useEffect(() => {
    // if (id === "" || id === null) return;
    // setStateValues(statusArr);
    loadData();
  }, []);

  // Validation
  const formik = useFormik({
    initialValues: {
      code: "",
      name: "",
      unit: "",
      price: "",
      status: "AVAILABLE",
      image: "",
      description: "",
      proTypeId: "",
    },
    validationSchema: Yup.object({
      code: Yup.string().required("Required"),
      name: Yup.string().required("Required"),
      unit: Yup.string().required("Required"),
      price: Yup.number().required("Required"),
    }),
    onSubmit: (values) => {
      console.log(values);
      handleFormSubmit(values);
    },
  });

  // image handle
  // const fileRef = React.createRef();
  const [selectedFile, setSelectFile] = useState(null);
  const [imageReview, setImageReview] = useState("");

  // const handleImageReview = (e) => {
  //   // e.preventDefault();
  //   setSelectFile(e.target.files[0]);
  //   setImageReview(URL.createObjectURL(e.target.files[0]));
  // };

  // selection
  // const onChangeSelected = (e) => {
  //   console.log(e);
  // };

  const handleFormSubmit = (data) => {
    const formData = new FormData();
    formData.append("code", data.code);
    formData.append("name", data.name);
    formData.append("proTypeId", data.proTypeId);
    formData.append("unit", data.unit);
    formData.append("price", Number(data.price));
    formData.append("status", data.status);
    formData.append("description", data.description);
    formData.append("photo", data.image);
    if (productId === null) {
      console.log(data);
      // add

      // console.log(fileRef.current.files);
      // console.log(fileRef.file);
      // console.log(formData.values);
      for (var value of formData.values()) {
        console.log(value);
      }

      productService.add(formData).then((res) => {
        toast.success("Add new data successfully");
        loadData();
        handleModalClose();
      });
    } else {
      // update
      if (data.image.name === undefined) {
        productService
          .update(
            productId,
            data.code,
            data.name,
            data.proTypeId,
            data.unit,
            data.price,
            data.status,
            data.description
          )
          .then((res) => {
            toast.success("Update data successfully");
            loadData();
            handleModalClose();
          });
      } else {
        console.log("Update hinhf");
        productService
          .updateWithPhoto(
            productId,
            data.code,
            data.name,
            data.proTypeId,
            data.unit,
            data.price,
            data.status,
            data.description,
            data.image
          )
          .then((res) => {
            toast.success("Update data successfully oke");
            loadData();
            handleModalClose();
          });
      }
    }
  };

  const deleteRow = (e, dataId) => {
    e.preventDefault();
    productService.remove(dataId).then((res) => {
      toast.warning("A data has been deleted!");
      loadData();
    });
  };

  return (
    <Fragment>
      <div className="container">
        <div className="container pt-4">
          <div className="card border-primary bt-primary-5">
            <div className="card-header">
              <div className="row">
                <div className="col">
                  <h2 className="card-title">Product Type List</h2>
                </div>
                <div className="col-auto">
                  <button
                    type="button"
                    className="btn btn-primary"
                    data-toggle="modal"
                    onClick={() => handleModalShow(null, null)}
                  >
                    <i className="fas fa-plus-circle mr-1"></i>
                    Add
                  </button>
                </div>
              </div>
            </div>
            <div className="card-body">
              <table className="table table-hover table-striped table-sm table-bordered text-center">
                <thead>
                  <tr className="table-primary">
                    <th scope="col">No.</th>
                    {/* <th onClick={sortData} scope="col">
                      Name{" "}
                      <div
                        className={
                          sortDir === "asc"
                            ? "arrow arrow-up"
                            : "arrow arrow-down"
                        }
                      ></div>
                    </th> */}
                    <th scope="col">Code</th>
                    <th scope="col">Name</th>
                    <th scope="col">Unit</th>
                    <th scope="col">Price</th>
                    <th scope="col">Status</th>
                    <th scope="col">Image</th>
                    {/* <th scope="col">Description</th> */}
                    <th scope="col">Type</th>
                    <th scope="col">Actions</th>
                  </tr>
                </thead>
                <tbody>
                  {products.map((product, idx) => {
                    return (
                      <tr key={product.id}>
                        <th scope="row">{idx + 1}</th>
                        <td>{product.code}</td>
                        <td>{product.name}</td>
                        <td>{product.unit}</td>
                        <td>{product.price}</td>
                        <td>{product.status}</td>
                        <td>
                          <img
                            src={product.image}
                            alt={product.name}
                            style={{ width: "200px", height: "150px" }}
                          />
                        </td>
                        <td>{product.productType.name}</td>
                        <td>
                          <a
                            href="/#"
                            className="mr-2"
                            onClick={(e) => getProduct(e, product.id)}
                          >
                            <i className="fas fa-eye text-primary"></i>
                          </a>
                          <a
                            href="/#"
                            className="mr-2"
                            onClick={(e) => handleModalShow(e, product.id)}
                          >
                            <i className="fas fa-edit text-success"></i>
                          </a>
                          <a
                            href="/#"
                            onClick={(e) => deleteRow(e, product.id)}
                          >
                            <i className="fas fa-trash-alt text-danger"></i>
                          </a>
                        </td>
                      </tr>
                    );
                  })}
                </tbody>
              </table>
            </div>
            {/* {productTypes.length > 0 ? (
              <div className="card-footer">
                <div style={{ float: "left" }}>
                  Showing Page {currentPage} of {totalPages}
                </div>
                <div style={{ float: "right" }}>
                  <InputGroup size="sm">
                    <InputGroup.Prepend>
                      <Button
                        type="button"
                        variant="outline-primary"
                        disabled={currentPage === 1 ? true : false}
                        onClick={firstPage}
                      >
                        <i className="fas fa-fast-backward"></i> First
                      </Button>
                      <Button
                        type="button"
                        variant="outline-primary"
                        disabled={currentPage === 1 ? true : false}
                        onClick={prevPage}
                      >
                        <i className="fas fa-step-backward"></i> Prev
                      </Button>
                    </InputGroup.Prepend>
                    <FormControl
                      className={"page-num bg-light border-primary"}
                      name="currentPage"
                      value={currentPage}
                      onChange={changePage}
                    />
                    <InputGroup.Append>
                      <Button
                        type="button"
                        variant="outline-primary"
                        disabled={currentPage === totalPages ? true : false}
                        onClick={nextPage}
                      >
                        <i className="fas fa-step-forward"></i> Next
                      </Button>
                      <Button
                        type="button"
                        variant="outline-primary"
                        disabled={currentPage === totalPages ? true : false}
                        onClick={lastPage}
                      >
                        <i className="fas fa-fast-forward"></i> Last
                      </Button>
                    </InputGroup.Append>
                  </InputGroup>
                </div>
              </div>
            ) : null} */}
          </div>
        </div>
      </div>
      <Modal
        show={modalShow}
        onHide={handleModalClose}
        backdrop="static"
        keyboard={false}
      >
        <Modal.Header closeButton>
          <Modal.Title>Product Types</Modal.Title>
        </Modal.Header>
        <form onSubmit={formik.handleSubmit}>
          <Modal.Body>
            <select
              id="proTypeId"
              name="proTypeId"
              //value={formik.values.proTypeId}
              // onChange={(value) => formik.setFieldValue("proTypeId", value)}
              onChange={formik.handleChange}
              {...formik.getFieldProps("proTypeId")}
              // frmField={formik.getFieldProps("proTypeId")}
            >
              <option>--Select product Type--</option>
              {productTypes.map((type) => {
                return (
                  <option key={type.id} value={type.id}>
                    {type.name}
                  </option>
                );
              })}
            </select>
            <Input
              id="txtCode"
              type="text"
              label="Code"
              maxLength="100"
              frmField={formik.getFieldProps("code")}
              err={formik.touched.code && formik.errors.code}
              errMessage={formik.errors.code}
            />
            <Input
              id="txtName"
              type="text"
              label="Name"
              maxLength="100"
              frmField={formik.getFieldProps("name")}
              err={formik.touched.name && formik.errors.name}
              errMessage={formik.errors.name}
            />
            <Input
              id="txtUnit"
              type="text"
              label="Unit"
              maxLength="100"
              frmField={formik.getFieldProps("unit")}
              err={formik.touched.unit && formik.errors.unit}
              errMessage={formik.errors.unit}
            />
            <Input
              id="txtPrice"
              type="text"
              label="Price"
              maxLength="100"
              frmField={formik.getFieldProps("price")}
              err={formik.touched.price && formik.errors.price}
              errMessage={formik.errors.price}
            />
            <fieldset className="form-group">
              <div className="row">
                <label className="col-form-label col-sm-3 pt-0">Status</label>
                <div className="col-sm-9">
                  <div className="form-check">
                    <input
                      id="status"
                      className="form-check-input"
                      type="radio"
                      name="status"
                      // defaultChecked
                      checked={formik.values.status === "AVAILABLE"}
                      //={formik.values.status === "AVAILABLE"}
                      onChange={() => handleRadioChange("AVAILABLE")}
                      value="AVAILABLE"
                    />
                    <label className="form-check-label">AVAILABLE</label>
                  </div>
                  <div className="form-check">
                    <input
                      id="status"
                      className="form-check-input"
                      type="radio"
                      name="status"
                      checked={formik.values.status === "UNAVAILABLE"}
                      onChange={() => handleRadioChange("UNAVAILABLE")}
                      value="UNAVAILABLE"
                    />
                    <label className="form-check-label">UNAVAILABLE</label>
                  </div>
                </div>
              </div>
            </fieldset>
            <div className="form-group row">
              <label className="col-form-label col-sm-3 pt-0">Image</label>
              <div className="col-sm-9">
                <img className="img-fluid" src={imageReview} alt="example" />
                <input
                  type="file"
                  // ref={fileRef}
                  onChange={(e) => {
                    setSelectFile(e.currentTarget.files[0]);
                    setImageReview(
                      URL.createObjectURL(e.currentTarget.files[0])
                    );
                    console.log(e.currentTarget.files[0]);
                    formik.setFieldValue("image", e.currentTarget.files[0]);
                  }}
                />
              </div>
            </div>
            <Input
              id="txtDesc"
              rows="10"
              type="text"
              label="Description"
              maxLength="200"
              frmField={formik.getFieldProps("description")}
            />
          </Modal.Body>
          <Modal.Footer>
            <Button variant="secondary" onClick={handleModalClose}>
              Close
            </Button>
            <Button
              variant="primary"
              type="submit"
              disabled={!(formik.isValid && formik.dirty)}
            >
              Save
            </Button>
          </Modal.Footer>
        </form>
      </Modal>
      {/* End modal */}
    </Fragment>
  );
};

export default ProductList;
