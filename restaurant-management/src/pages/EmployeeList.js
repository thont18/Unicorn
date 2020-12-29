import React, { useState, Fragment, useEffect } from "react";
import { Button, FormControl, InputGroup, Modal } from "react-bootstrap";
import { useFormik } from "formik";
import * as Yup from "yup";
import axios from "axios";
import "./style.css";
import { event } from "jquery";
import Input from "../controls/Input";
import employeeService from "../services/employeeService";
import positionService from "../services/positionService";
import { toast } from "react-toastify";

const EmployeeList = (props) => {
  const [employees, setEmployees] = useState([]);
  const [employeesPerPage, setEmployeesPerPage] = useState(3);
  const [currentPage, setCurrentPage] = useState(1);
  const [totalPages, setTotalPages] = useState(0);
  const [totalElements, setTotalElements] = useState(0);
  const [sortDir, setSortDir] = useState("asc");
  const [search, setSearch] = useState("");
  const [positions, setPositions] = useState([]);
  const [position, setPosition] = useState({});
  const [sites, setSites] = useState([]);
  const [site, setSite] = useState({});
  const phoneRegExp = /^((\\+[1-9]{1,4}[ \\-]*)|(\\([0-9]{2,3}\\)[ \\-]*)|([0-9]{2,4})[ \\-]*)*?[0-9]{3,4}?[ \\-]*[0-9]{3,4}?$/



  //validation
  const formik = useFormik({
    initialValues: {
      lastName: "",
      firstName: "",
      phoneNumber: "",
      address: "",
      identityCardNumber: "",
      posId: "",
      siteId: "",
      // position: "",
    },
    validationSchema: Yup.object({
      lastName: Yup.string().required("Last name is not null !!!"),
      firstName: Yup.string().required("First name is not null !!!"),
      phoneNumber: Yup.string().required("Phone number is not null !!!").matches(phoneRegExp, 'Phone number is not valid'),
      identityCardNumber: Yup.number().typeError("The input values is number").max(9999999999999,'Do not exceed 13 numbers').min(1000000000,'Phai lon hon 10 so').required("ID card is not null !!!"),
      address: Yup.string().required("Adress is not null !!!")
    }),
    onSubmit: (values) => {
      console.log(values);
      handleFormSubmit(values);
    },
  });

  const [modalShow, setModalShow] = useState(false);
  const [id, setId] = useState(1);
  const [employeeId, setEmployeeId] = useState(0);
  const handleModalClose = () => setModalShow(false);
  const handleModalShow = (e, dataId) => {
    if (e) {
      e.preventDefault();
    }
    setEmployeeId(dataId);
    if (dataId > 0) {
      //edit
      employeeService.get(dataId).then((res) => {
        formik.setValues({
          lastName: res.lastName,
          firstName: res.firstName,
          phoneNumber: res.phoneNumber,
          address: res.address,
          identityCardNumber: res.identityCardNumber,
          posId: res.position.id,
          siteId: res.site.id,
        });
        console.log(res);
        setModalShow(true);
      });
    } else {
      // add
      formik.resetForm();
      setModalShow(true);
    }
  };

  const sortData = () => {
    setTimeout(() => {
      sortDir === "asc" ? setSortDir("desc") : setSortDir("asc");
      loadData(currentPage);
    }, 500);
  };

  useEffect(() => {
    getPositions();
    getSites();
    loadData(currentPage);
  }, []);

  const loadData = (currentPage) => {
    // pagination
    currentPage -= 1;
    axios
      .get(
        "http://localhost:8080/employees?pageNumber=" +
        currentPage +
        "&pageSize=" +
        employeesPerPage +
        "&sortBy=firstName&sortDir=" +
        sortDir
      )
      .then((res) => res.data)
      .then((data) => {
        setEmployees(data.content);
        setTotalPages(data.totalPages);
        setTotalElements(data.totalElements);
        setCurrentPage(data.number + 1);
      });
    // employeeService.getAll().then((res) => {
    //   // console.log(res);
    //   setEmployees(res);
    // });
  };

  // lay du lieu tat ca cac hang trong bang site
  const getSites = () => {
    axios
      .get("http://localhost:8080/sites/getAllSites")
      .then((res) => res.data)
      .then((data) => {
        // console.log(data);
        setSites(data);
      });
  };

  // lay du lieu tat ca cac hang trong bang position
  const getPositions = () => {
    positionService.getAll().then((res) => {
      // console.log(res);
      setPositions(res);
    });
  };

  useEffect(() => {
    getPositions();
    getSites();
    loadData(currentPage);
  }, []);

  const handleFormSubmit = (data) => {
    const formData = new FormData();
    formData.append("lastName", data.lastName);
    formData.append("firstName", data.firstName);
    formData.append("phoneNumber", data.phoneNumber);
    formData.append("address", data.address);
    formData.append("identityCardNumber", data.identityCardNumber);
    formData.append("posId", data.posId);
    formData.append("siteId", data.siteId);

    if (employeeId === null) {
      console.log(data);
      // add
      for (let value of formData.values()) {
        console.log(value);
      }
      employeeService.add(formData).then((res) => {
        toast.success('Add new data successfully!')
        loadData(currentPage);
        handleModalClose();
      });
    } else {
      //update
      employeeService.update(employeeId, formData).then((res) => {
        toast.success('Update existing data successfully!')
        loadData(currentPage);
        handleModalClose();
      });
    }
  };

  const deleteRow = (e, dataId) => {
    e.preventDefault();
    employeeService.remove(dataId).then((res) => {
      toast.error('A data has been deleted!')
      loadData(currentPage);
    });
  };

  const changePage = (event) => {
    let targetPage = parseInt(event.target.value);
    if (search) {
      searchData(targetPage);
    } else {
      loadData(targetPage);
    }

    event.target.name = targetPage;
  };

  const firstPage = () => {
    let firstPage = 1;
    if (currentPage > firstPage) {
      if (search) {
        searchData(firstPage);
      } else {
        loadData(firstPage);
      }
    }
  };

  const prevPage = () => {
    let prevPage = 1;
    if (currentPage > prevPage) {
      if (search) {
        searchData(currentPage - prevPage);
      } else {
        loadData(currentPage - prevPage);
      }
    }
  };

  const lastPage = () => {
    let condition = Math.ceil(totalElements / employeesPerPage);
    if (currentPage < condition) {
      if (search) {
        searchData(condition);
      } else {
        loadData(condition);
      }
    }
  };

  const nextPage = () => {
    if (currentPage < Math.ceil(totalElements / employeesPerPage)) {
      if (search) {
        searchData(currentPage + 1);
      } else {
        loadData(currentPage + 1);
      }
    }
  };

  const searchChange = (event) => {
    const searchText = event.target.value;
    setSearch(searchText);
  };

  const cancelSearch = () => {
    setSearch("");
    loadData(currentPage);
  };

  const searchData = (currentPage) => {
    currentPage -= 1;
    axios
      .get(
        "http://localhost:8080/employees/search/" +
        search +
        "?page=" +
        currentPage +
        "&size=" +
        employeesPerPage
      )
      .then((Response) => Response.data)
      .then((data) => {
        setEmployees(data.content);
        setTotalPages(data.totalPages);
        setTotalElements(data.totalElements);
        setCurrentPage(data.number + 1);
      });
  };

  return (
    <Fragment>
      <div className="container">
        <div className="container pt-4">
          <div className="row">
            <div className="col-sm-12 col-md-6 col-lg-6">
              <InputGroup size="sm">
                <FormControl
                  placeholder="Search"
                  name="search"
                  className={"border-primary bg-light text-dark"}
                  onChange={searchChange}
                  value={search}
                />
                <InputGroup.Append>
                  <Button
                    size="sm"
                    variant="outline-success"
                    type="button"
                    onClick={searchData}
                  >
                    <i className="fas fa-search"></i>
                  </Button>
                  <Button
                    size="sm"
                    variant="outline-danger"
                    type="button"
                    onClick={cancelSearch}
                  >
                    <i className="fas fa-times"></i>
                  </Button>
                </InputGroup.Append>
              </InputGroup>
            </div>
          </div>
        </div>
        <div className="container pt-4">
          <div className="card border-primary bt-primary-5">
            <div className="card-header">
              <div className="row">
                <div className="col">
                  <h2 className="card-title">Employee List</h2>
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
              <table className="table table-hover table-sm table-striped table-bordered text-center">
                <thead>
                  <tr className="table-primary">
                    <th scope="col">No.</th>
                    {/* <th>Last Name</th> */}
                    <th onClick={sortData} scope="col">
                      Last Name{" "}
                      <div
                        className={
                          sortDir === "asc"
                            ? "arrow arrow-up"
                            : "arrow arrow-down"
                        }
                      ></div>
                    </th>

                    {/* <th>First Name</th> */}
                    <th onClick={sortData} scope="col">
                      First Name{" "}
                      <div
                        className={
                          sortDir === "asc"
                            ? "arrow arrow-up"
                            : "arrow arrow-down"
                        }
                      ></div>
                    </th>

                    <th scope="col">Phone Number</th>

                    <th scope="col">Adress</th>

                    {/* <th>Identity Card Number</th> */}
                    <th onClick={sortData} scope="col">
                      Identity CardNumber{" "}
                      <div
                        className={
                          sortDir === "asc"
                            ? "arrow arrow-up"
                            : "arrow arrow-down"
                        }
                      ></div>
                    </th>
                    <th>Position</th>
                    <th>Working Site</th>

                    <th scope="col"></th>
                  </tr>
                </thead>
                <tbody>
                  {employees.map((employee, idx) => {
                    return (
                      <tr key={employee.id}>
                        <th scope="row">{idx + 1}</th>
                        <td>{employee.lastName}</td>
                        <td>{employee.firstName}</td>
                        <td>{employee.phoneNumber}</td>
                        <td>{employee.address}</td>
                        <td>{employee.identityCardNumber}</td>
                        <td>{employee.position.name}</td>
                        <td>{employee.site.name}</td>
                        <td>
                          <a
                            href="/#"
                            className="mr-2"
                            onClick={(e) => handleModalShow(e, employee.id)}
                          >
                            <i className="fas fa-edit text-success"></i>
                          </a>
                          <a
                            href="/#"
                            onClick={(e) => deleteRow(e, employee.id)}
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
            {employees.length > 0 ? (
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
            ) : null}
          </div>
        </div>
      </div>

      {/* Modal */}
      <Modal
        show={modalShow}
        onHide={handleModalClose}
        backdrop="static"
        keyboard={false}
      >
        <Modal.Header closeButton>
          <Modal.Title>Employees</Modal.Title>
        </Modal.Header>
        <form onSubmit={formik.handleSubmit} className="mb-3">
          <Modal.Body>
            <div className="row mb-3">
              <div className="col-4">
                <select
                  onChange={formik.handleChange}
                  name="posId"
                  id="posId"
                  label="Position:"
                  {...formik.getFieldProps("posId")}
                >
                  <option value="" label="--- Select position ---"></option>
                  {positions.map((position) => {
                    return (
                      <option key={position.id} value={position.id}>
                        {position.name}
                      </option>
                    );
                  })}
                </select>
              </div>

              
              <div className="col-4 offset-4">
                <select
                  onChange={formik.handleChange}
                  name="siteId"
                  id="siteId"
                  label="Working Site:"
                  {...formik.getFieldProps("siteId")}
                >
                  <option value="" label="--- Select site ---"></option>
                  {sites.map((site) => {
                    return (
                      <option key={site.id} value={site.id}>
                        {site.name}
                      </option>
                    );
                  })}
                </select>
              </div>
            </div>

            <Input
              className="mr-2 font-weight-bold"
              id="txtLastName"
              type="text"
              label="Last Name :"
              maxLength="50"
              frmField={formik.getFieldProps("lastName")}
              err={formik.touched.lastName && formik.errors.lastName}
              errMessage={formik.errors.lastName}
            />

            <Input
              id="txtFirstName"
              type="text"
              label="First Name :"
              maxLength="50"
              frmField={formik.getFieldProps("firstName")}
              err={formik.touched.firstName && formik.errors.firstName}
              errMessage={formik.errors.firstName}
            />

            <Input
              id="txtphoneNumber"
              type="text"
              label="Phone Number :"
              maxLength="50"
              frmField={formik.getFieldProps("phoneNumber")}
              err={formik.touched.phoneNumber && formik.errors.phoneNumber}
              errMessage={formik.errors.phoneNumber}
            />

            <Input
              id="txtaddress"
              type="text"
              label="Address :"
              maxLength="200"
              frmField={formik.getFieldProps("address")}
              err={formik.touched.address && formik.errors.address}
              errMessage={formik.errors.address}
            />

            <Input
              id="txtidentityCardNumber"
              type="text"
              label="CMND:"
              frmField={formik.getFieldProps("identityCardNumber")}
              err={
                formik.touched.identityCardNumber &&
                formik.errors.identityCardNumber
              }
              errMessage={formik.errors.identityCardNumber}
            />
          </Modal.Body>
          <Modal.Footer>
            <Button variant="secondary" onClick={handleModalClose}>
              Close
            </Button>
            <Button
              variant="primary"
              type="submit"
              disabled={!(formik.isValid) && formik.dirty}
            >
              Save
            </Button>
          </Modal.Footer>
        </form>
      </Modal>
    </Fragment>
  );
};

export default EmployeeList;
