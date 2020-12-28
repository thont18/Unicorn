import React, { useState, Fragment, useEffect } from "react";
import { Button, FormControl, InputGroup, Modal } from "react-bootstrap";
import { useFormik } from "formik";
import * as Yup from "yup";
import axios from "axios";
import "./style.css";
import { event } from "jquery";
import Input from "../controls/Input";
import positionService from "../services/positionService";
import { toast } from "react-toastify";

const PositionList = (props) => {
  const [positions, setPositions] = useState([]);
  const [typesPerPage, setTypesPerPage] = useState(6);
  const [currentPage, setCurrentPage] = useState(1);
  const [totalPages, setTotalPages] = useState(0);
  const [totalElements, setTotalElements] = useState(0);
  const [sortDir, setSortDir] = useState("asc");
  const [search, setSearch] = useState("");

  //validation
  const formik = useFormik({
    initialValues: {
      name: "",
    },
    validationSchema: Yup.object({
      name: Yup.string().required("Name is not null !!!"),
    }),
    onSubmit: (values) => {
      console.log(values);
      handleFormSubmit(values);
    },
  });

  const [modalShow, setModalShow] = useState(false);
  const [id, setId] = useState(1);
  const [positionId, setPositionId] = useState(0);
  const handleModalClose = () => setModalShow(false);
  const handleModalShow = (e, dataId) => {
    if (e) {
      e.preventDefault();
    }
    setPositionId(dataId);
    if (dataId > 0) {
      //edit
      positionService.get(dataId).then((res) => {
        formik.setValues(res);
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

  const loadData = (currentPage) => {
    currentPage -= 1;
    axios
      .get(
        "http://localhost:8080/positions?pageNumber=" +
          currentPage +
          "&pageSize=" +
          typesPerPage +
          "&sortBy=name&sortDir=" +
          sortDir
      )
      .then((res) => res.data)
      .then((data) => {
        setPositions(data.content);
        setTotalPages(data.totalPages);
        setTotalElements(data.totalElements);
        setCurrentPage(data.number + 1);
      });
    // positionService.getAll().then(res => {
    //   console.log(res);
    //   setPositions(res)
    // })
  };

  useEffect(() => {
    loadData(currentPage);
  }, []);

  const handleFormSubmit = (data) => {
    if (positionId === null) {
      // add
      positionService.add(data).then((res) => {
        toast.success('Add new data successfully!');
        loadData(currentPage);
        handleModalClose();
      });
    } else {
      //update
      positionService.update(positionId, data).then((res) => {
        toast.success('Update existing data successfully!');
        loadData(currentPage);
        handleModalClose();
      });
    }
  };

  const deleteRow = (e, dataId) => {
    e.preventDefault();
    positionService.remove(dataId).then((res) => {
      toast.error('A data has been deleted');
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
    let condition = Math.ceil(totalElements / typesPerPage);
    if (currentPage < condition) {
      if (search) {
        searchData(condition);
      } else {
        loadData(condition);
      }
    }
  };

  const nextPage = () => {
    if (currentPage < Math.ceil(totalElements / typesPerPage)) {
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
        "http://localhost:8080/positions/search/" +
          search +
          "?page=" +
          currentPage +
          "&size=" +
          typesPerPage
      )
      .then((Response) => Response.data)
      .then((data) => {
        setPositions(data.content);
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
                  <h2 className="card-title">Position List</h2>
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
              <table className="table table-hover table-striped table-bordered text-center">
                <thead>
                  <tr className="table-primary">
                    <th scope="col">No.</th>
                    <th onClick={sortData} scope="col">
                      Name{" "}
                      <div
                        className={
                          sortDir === "asc"
                            ? "arrow arrow-up"
                            : "arrow arrow-down"
                        }
                      ></div>
                    </th>
                    <th scope="col"></th>
                  </tr>
                </thead>
                <tbody>
                  {positions.map((position, idx) => {
                    return (
                      <tr key={position.id}>
                        <th scope="row">{idx + 1}</th>
                        <td>{position.name}</td>
                        <td>
                          <a
                            href="/#"
                            className="mr-2"
                            onClick={(e) => handleModalShow(e, position.id)}
                          >
                            <i className="fas fa-edit text-success"></i>
                          </a>
                          <a
                            href="/#"
                            onClick={(e) => deleteRow(e, position.id)}
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
            {positions.length > 0 ? (
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
          <Modal.Title>Positions</Modal.Title>
        </Modal.Header>
        <form onSubmit={formik.handleSubmit}>
          <Modal.Body>
            <Input
              id="txtName"
              type="text"
              label="Name :"
              maxLength="100"
              frmField={formik.getFieldProps("name")}
              err={formik.touched.name && formik.errors.name}
              errMessage={formik.errors.name}
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
    </Fragment>
  );
};

export default PositionList;
