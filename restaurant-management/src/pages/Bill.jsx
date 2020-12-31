/* eslint-disable no-unused-vars */
import React, { useState, Fragment, useEffect } from 'react';
import Input from '../controls/Input';
import Select from '../controls/select';
import { Button, FormControl, InputGroup, Modal } from 'react-bootstrap';
import { useFormik } from 'formik';
import * as Yup from 'yup';
import axios from 'axios';
import billService from './../services/BillService';
import employeeService from './../services/employeeService';
import promotionService from './../services/productTypeService';
import tableService from './../services/TableService';
import { Link } from 'react-router-dom';

const Bill = (props) => {
	const [bills, setBill] = useState([]);
	const [employee, setEmployee] = useState([]);
	const [promotion, setPromotion] = useState([]);
	const [table, setTable] = useState([]);
	const [typesPerPage, setTypesPerPage] = useState(10);
	const [currentPage, setCurrentPage] = useState(1);
	const [totalPages, setTotalPages] = useState(0);
	const [totalElements, setTotalElements] = useState(0);
	const [sortDir, setSortDir] = useState('asc');
	const [search, setSearch] = useState('');
	const [billId, setBillId] = useState();

	const [modalShow, setModalShow] = useState(false);
	const handleModalClose = () => setModalShow(false);
	const getCurrenDate = () => {
		let tempDate = new Date();
		let date = tempDate.getFullYear() + '-' + (tempDate.getMonth() + 1) + '-' + tempDate.getDate();
		return date;
	};
	const [currentDate, setCurrentdate] = useState(getCurrenDate());
	// Validation

	// paymentdate, paymentMethod, table, promotionType, employee

	const loadData = (currentPage) => {
		// productTypeService.getAll().then((res) => {
		//   setProductTypes(res);
		// });
		currentPage -= 1;
		axios
			.get(
				'http://localhost:8080/bills?pageNumber=' +
					currentPage +
					'&pageSize=' +
					typesPerPage +
					'&sortBy=id&sortDir=' +
					sortDir
			)
			.then((res) => res.data)
			.then((data) => {
				//console.log(data);
				setBill(data.content);
				setTotalPages(data.totalPages);
				setTotalElements(data.totalElements);
				setCurrentPage(data.number + 1);
			});
	};

	useEffect(() => {
		getCurrenDate();
		loadData(currentPage);
	}, []);
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
	const searchData = (currentPage) => {
		currentPage -= 1;
		axios
			.get(
				'http://localhost:8080/bills/findDate?date=' + search + '&page=' + currentPage + '&size=' + typesPerPage
			)
			.then((response) => response.data)
			.then((data) => {
				setBill(data.content);
				setTotalPages(data.totalPages);
				setTotalElements(data.totalElements);
				setCurrentPage(data.number + 1);
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
	const deleteRow = (e, dataId) => {
		e.preventDefault();
		billService.remove(dataId).then((res) => {
			loadData(currentPage);
		});
	};

	const sortData = () => {
		setTimeout(() => {
			sortDir === 'asc' ? setSortDir('desc') : setSortDir('asc');
			loadData(currentPage);
		}, 500);
	};
	const searchChange = (event) => {
		const searchText = event.target.value;
		setSearch(searchText);
	};
	const cancelSearch = () => {
		setSearch('');
		loadData(currentPage);
	};
	// const searchData = () => {};
	return (
		<Fragment>
			<div className="container text-center">
				<div className="input-group mt-2 ml-5">
					<div aria-label="Username" aria-describedby="basic-addon1">
						<input
							value={search}
							type="date"
							className="form-control"
							placeholder="Username"
							onChange={searchChange}
						/>
					</div>

					<div className="input-group-prepend">
						<button className="input-group-text" id="basic-addon1" onClick={searchData}>
							Find
						</button>
						<button className="input-group-text" id="basic-addon1" onClick={cancelSearch}>
							Cancel
						</button>
					</div>
				</div>

				<div className="container mt-1 text-center">
					<div className="card border-primary bt-primary-5">
						<div className="card-header">
							<div className="row">
								<div className="col">
									<h2 className="card-title">Bill List</h2>
								</div>
							</div>
						</div>

						<div className="card-body">
							<table className="table table-hover table-striped table-bordered text-center">
								<thead>
									<tr className="table-primary">
										<th scope="col">No.</th>
										{/* <th onClick={sortData} scope="col">
											Payment Date
											<div
												className={sortDir === 'asc' ? 'arrow arrow-up' : 'arrow arrow-down'}
											></div>
										</th> */}
										<th scope="col">Payment Date</th>
										<th scope="col">Payment Method</th>
										{/* <th scope="col">Employee Id</th> */}
										{/* <th scope="col">Promotion Id</th> */}
										<th scope="col">Table Id</th>
										<th onClick={sortData} scope="col">
											Total price
											<div
												className={sortDir === 'asc' ? 'arrow arrow-up' : 'arrow arrow-down'}
											></div>
										</th>
										{/* <th scope="col">Total price</th> */}
										<th scope="col"></th>
									</tr>
								</thead>
								<tbody>
									{bills.map((bill, idx) => {
										return (
											<tr key={bill.id}>
												<th scope="row">{idx + 1}</th>
												<td>{bill.paymentDate}</td>
												<td>{bill.paymentMethod}</td>
												{/* <td>{bill.employee.id}</td> */}
												{/* <td>{bill.promotionType.name}</td> */}
												<td>{bill.table.code}</td>
												<td>{bill.totalPrice}</td>
												<td>
													<Link className="mr-2" to={`/billDetails?id=${bill.id}`}>
														<i className="fa fa-eye text-info"></i>
													</Link>
													<a href="/#" onClick={(e) => deleteRow(e, bill.id)}>
														<i className="fas fa-trash-alt text-danger"></i>
													</a>
												</td>
											</tr>
										);
									})}
								</tbody>
							</table>
						</div>
						{bills.length > 0 ? (
							<div className="card-footer">
								<div style={{ float: 'left' }}>
									Showing Page {currentPage} of {totalPages}
								</div>
								<div style={{ float: 'right' }}>
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
											className={'page-num bg-light border-primary'}
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
		</Fragment>
	);
};
export default Bill;
