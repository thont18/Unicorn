import React, { useState, Fragment, useEffect } from 'react';
import { Button, FormControl, InputGroup, Modal } from 'react-bootstrap';
import './order.css';
import productService from './../services/productService';
import './style.css';
import { toast } from 'react-toastify';
import billDetailService from './../services/BillDetailService';
import billService from './../services/BillService';
import PromotionTypeService from './../services/promotionTypeService';
import TableService from '../services/TableService';

const Order = (props) => {
	const [table, setTable] = useState([]);
	const [product, setProduct] = useState([]);
	const [billDetail, setBillDetail] = useState([]);
	const [tableId, setTableId] = useState();
	const [productId, setProductId] = useState();
	const [billId, setBillId] = useState('');
	const [value, setValue] = useState(0);
	const [tableCode, setTableCode] = useState('');
	const [tableCodeId, setTableCodeId] = useState('');
	const [totalPrice, setTotalprice] = useState(0);
	const [payMethod, setPayMethod] = useState('CASH');
	const [promotionType, setPromotionType] = useState([]);
	const [promotionId, setpromotionId] = useState('');
	const [modalShow, setModalShow] = useState(false);
	const [proDefaut, setproDefault] = useState(0);
	const [bilIdMerge, setBillidMerge] = useState('');
	const [name, setName] = useState('');
	const handleModalClose = () => setModalShow(false);
	const handleModalShow = () => {
		setModalShow(true);
	};
	const loadTable = () => {
		TableService.getAll().then((res) => {
			setTable(res);
		});
	};
	const loadPromotion = () => {
		PromotionTypeService.getAll().then((res) => {
			setPromotionType(res);
		});
	};
	const loadData = () => {
		productService.getAll().then((res) => {
			setProduct(res);
		});
		loadPromotion();
		loadTable();
	};
	const formatMoney = (money) => {
		money = money.toLocaleString('en-US', { style: 'currency', currency: 'VND' });
		return money;
	};
	useEffect(() => {
		loadData();
		loadTable();
	}, []);
	const loadBillDetail = (bId) => {
		billDetailService.get(bId).then((res) => {
			setBillDetail(res);
		});
	};
	const updateStatusTable = (tablId, status) => {
		TableService.get(tablId).then((res) => {
			res.status = status;
			TableService.updateStatus(tablId, res).then((res) => {
				loadTable();
			});
		});
	};
	const insertPro = (proId, price) => {
		if (billId === '') {
			toast.error('Please choose table ');
		} else {
			billDetailService.getById(billId, proId).then((res) => {
				if (res === null) {
					setProductId(proId);
					let tempDate = new Date();
					let a = tempDate.getMonth();
					let b = tempDate.getDate();
					if (a < 10) a = '0' + (tempDate.getMonth() + 1);
					if (b < 10) b = '0' + tempDate.getDate();
					let date = tempDate.getFullYear() + '-' + a + '-' + b;
					var data = {
						id: {
							billId: billId,
							productId: proId,
						},
						amount: 1,
						price: price,
						finishedProductNum: 1,
						orderDate: date,
						finishedProductDate: date,
					};

					billDetailService.add(data, data.id.billId, data.id.productId).then((res) => {
						loadBillDetail(data.id.billId);
						getTotalPrice(data.id.billId);
						updateStatusTable(tableId, 'BOOKED');
					});
				} else {
					res.amount = res.amount + 1;
					billDetailService.update(res.id.billId, res.id.productId, res).then((res) => {
						loadBillDetail(res.id.billId);
						getTotalPrice(res.id.billId);
					});
				}
			});
		}
	};
	const getTotalPrice = (id) => {
		var x = 0;
		billService.getTotalPrice(id).then((res) => {
			setTotalprice(res);
			x = res;
		});
		return x;
	};
	const loadBill = (tablId) => {
		setTableId(tablId);
		setproDefault(0);
		setValue(0);
		billService.getBillByTableId(tablId).then((res) => {
			if (res === '') {
				billService.add(tablId).then((data) => {
					setBillId(data.id);
					loadBillDetail(data.id);
					getTotalPrice(data.id);
				});
			} else {
				setBillId(res.id);
				loadBillDetail(res.id);
				getTotalPrice(res.id);
			}
		});
	};
	const add = (ind) => {
		billDetail[ind].amount = billDetail[ind].amount + 1;
		billDetailService
			.update(billDetail[ind].id.billId, billDetail[ind].id.productId, billDetail[ind])
			.then((res) => {
				loadBillDetail(res.id.billId);
				getTotalPrice(res.id.billId);
			});
	};
	const sub = (ind) => {
		if (billDetail[ind].amount >= 2) {
			billDetail[ind].amount = billDetail[ind].amount - 1;
			billDetailService
				.update(billDetail[ind].id.billId, billDetail[ind].id.productId, billDetail[ind])
				.then((res) => {
					loadBillDetail(res.id.billId);
					getTotalPrice(res.id.billId);
				});
		}
	};
	const deleteRow = (e, bilId, productId) => {
		e.preventDefault();
		billDetailService.remove(bilId, productId).then((res) => {
			loadBillDetail(bilId);
			var x = getTotalPrice(bilId);
			if (x === 0 || x === '') {
				updateStatusTable(tableId, 'EMPTY');
				loadData();
			}
		});
	};
	const getBgColor = (status) => {
		if (status === 'BOOKED') {
			return '#D8BFD8';
		} else {
			return '';
		}
	};
	const valueChangePa = (e) => {
		setPayMethod(e.target.value);
	};
	const promoChange = (e) => {
		setproDefault(e.target.value);
		getTotalPrice(billId);
		var m = totalPrice;
		var id = e.target.value;
		if (id > 0) {
			setpromotionId(id);
			PromotionTypeService.get(id).then((res) => {
				var x = res.percent;
				var percent = (x * m) / 100;
				setValue(percent);
			});
		}
	};
	const payBill = () => {
		if (totalPrice > 0) {
			var money = totalPrice - value;
			let tempDate = new Date();
			let date = tempDate.getFullYear() + '-' + (tempDate.getMonth() + 1) + '-' + tempDate.getDate();
			billService.update(billId, date, payMethod, tableId, '', promotionId, money).then((res) => {
				updateStatusTable(tableId, 'EMPTY');
				loadData();
				setBillId('');
				setBillDetail([]);
				setValue(0);
				setTotalprice('');
				toast.success('Pay success');
			});
		} else {
			toast.error('No bill to pay');
		}
	};
	const mergeBIll = () => {
		if (totalPrice > 0) {
			handleModalShow();
		} else {
			toast.error('no value to merge');
		}
	};
	const chooseTable = (id, code) => {
		setTableCodeId(id);
		setTableCode(code);
		getbillIdByTableId(id);
	};
	const getbillIdByTableId = (tablId) => {
		billService.getBillByTableId(tablId).then((res) => {
			if (res === '') {
				billService.add(tablId).then((data) => {
					setBillidMerge(data.id);
				});
			} else {
				setBillidMerge(res.id);
			}
		});
	};
	const mergeTable = () => {
		billDetail.map((billD, ind) => {
			billDetailService.getById(bilIdMerge, billD.id.productId).then((res) => {
				if (res === null) {
					console.log('here');
					var data = {
						id: {
							billId: bilIdMerge,
							productId: billD.id.productId,
						},
						amount: billD.amount,
						price: billD.product.price,
						finishedProductNum: billD.amount,
						orderDate: billD.orderDate,
						finishedProductDate: billD.finishedProductDate,
					};
					billDetailService.add(data, data.id.billId, data.id.productId).then((res) => {
						updateStatusTable(tableCodeId, 'BOOKED');
						loadBill(tableCodeId);
					});
				} else {
					console.log('not null');
					res.amount = res.amount + billD.amount;
					console.log(res);
					billDetailService.update(res.id.billId, res.id.productId, res).then((res) => {
						loadBill(tableCodeId);
					});
				}
				billDetailService.remove(billD.id.billId, billD.id.productId).then((res) => {});
			});
		});
		updateStatusTable(tableId, 'EMPTY');
		setTotalprice(0);
		//
		handleModalClose();
	};
	const searchData = () => {
		productService.Find(name).then((res) => {
			setProduct(res);
		});
	};
	const searchChange = (event) => {
		const searchText = event.target.value;
		setName(searchText);
	};
	const cancelSearch = () => {
		setName(' ');
		loadData();
	};
	return (
		<Fragment>
			<div className="container">
				<div className="row mt-2">
					<div className="col-lg-7">
						<div className="row ">
							<div className="col-lg-6">
								<div>
									<ul className="nav nav-pills " id="pills-tab" role="tablist">
										<li className="nav-item" role="presentation">
											<a
												className="nav-link active"
												id="pills-home-tab"
												data-toggle="pill"
												href="#pills-home"
												role="tab"
												aria-controls="pills-home"
												aria-selected="true"
											>
												Phòng Bàn
											</a>
										</li>
										<li className="nav-item" role="presentation">
											<a
												className="nav-link"
												id="pills-profile-tab"
												data-toggle="pill"
												href="#pills-profile"
												role="tab"
												aria-controls="pills-profile"
												aria-selected="false"
											>
												Thực Đơn
											</a>
										</li>
									</ul>
								</div>
							</div>
							{/* <div className="col-lg-6">
							<form className="form-inline active-purple-4">
								<input
									className="form-control form-control-sm mr-3 w-75"
									type="text"
									placeholder="Search"
									aria-label="Search"
								/>
								<i className="fas fa-search" aria-hidden="true" />
							</form>
						</div> */}
						</div>
						<div className="row">
							<div className="col-12 pl-5">
								<div className="tab-content" id="pills-tabContent">
									<div
										className="tab-pane fade show active"
										id="pills-home"
										role="tabpanel"
										aria-labelledby="pills-home-tab"
									>
										<div className="row">
											{table.map((tab, ind) => {
												return (
													<div className="col-lg-2 text-center p-2" key={tab.id}>
														<div
															style={{ backgroundColor: getBgColor(tab.status) }}
															className="orderTable"
															key={tab.id}
															onClick={() => loadBill(tab.id)}
														>
															<i className="fa fa-table mt-2"></i>
															<h6 className="mt-2"> {tab.code}</h6>
														</div>
													</div>
												);
											})}
										</div>
									</div>
									<div
										className="tab-pane fade"
										id="pills-profile"
										role="tabpanel"
										aria-labelledby="pills-profile-tab"
									>
										<div className="input-group mt-2 w-50 ml-5">
											<input
												type="text"
												value={name}
												className="form-control w-50"
												aria-label="Recipient's username"
												aria-describedby="basic-addon2"
												onChange={searchChange}
											/>
											<div className="input-group-append">
												<button
													className=" btn btn-info"
													id="basic-addon2"
													onClick={searchData}
												>
													Find
												</button>
												<button
													className=" btn btn-warning"
													id="basic-addon2"
													onClick={cancelSearch}
												>
													Cancel
												</button>
											</div>
										</div>
										<div className="row">
											{product.map((prod, ind) => {
												return (
													<div key={prod.id} className="col-lg-3 text-center p-2">
														<div
															className="orderProduct"
															key={prod.id}
															onClick={() => insertPro(prod.id, prod.price)}
														>
															<img className="mt-2" alt={prod.name} src={prod.image} />
															<h6 className="mt-2"> {prod.name}</h6>
														</div>
													</div>
												);
											})}
										</div>
									</div>
									<div
										className="tab-pane fade"
										id="pills-contact"
										role="tabpanel"
										aria-labelledby="pills-contact-tab"
									>
										...
									</div>
								</div>
							</div>
						</div>
					</div>
					<div className="col-lg-5">
						<ul className="nav nav-pills" id="pills-tab" role="tablist">
							<li className="nav-item" role="presentation">
								<p className="nav-link active">BILL: {billId}</p>
							</li>
						</ul>
						<div className="bill">
							<table className="tabless table table-hover table-striped  text-center">
								<thead>
									<tr className="table-primary">
										<th scope="col">No.</th>
										<th cope="col" className="w-50">
											Name
										</th>
										<th scope="col" className="w-50">
											Quantity
										</th>
										<th scope="col">Price</th>
										<th scope="col"></th>
									</tr>
								</thead>
								<tbody className="croll">
									{billDetail.map((billD, ind) => {
										return (
											<tr key={ind}>
												<td>{ind + 1}</td>
												<td>{billD.product.name}</td>
												<td>
													{' '}
													<div className="ml-4 spinner">
														<span className="float-left ml-1" onClick={() => sub(ind)}>
															<i className="fa fa-minus text-light  p-1 "></i>
														</span>

														<p className="float-left ml-2">{billD.amount}</p>
														<span className="float-left ml-2" onClick={() => add(ind)}>
															<i className="fa fa-plus text-light  p-1"></i>
														</span>
													</div>
												</td>
												<td>{billD.product.price}</td>
												<td>
													<i
														onClick={(e) => deleteRow(e, billD.bill.id, billD.product.id)}
														className="fas fa-trash-alt text-danger"
													></i>
												</td>
											</tr>
										);
									})}
								</tbody>
							</table>
							<div className="text-center">
								<h5 className="ml-4">Total Price: {' ' + formatMoney(totalPrice - value) + ' '}</h5>
							</div>

							<div className="form-check-inline">
								<p className="mt-3 mr-2">Payment Method:</p>
								<label className="form-check-label">
									<input
										onChange={valueChangePa}
										value="CASH"
										type="radio"
										checked
										className="form-check-input"
										name="optradio"
									/>
									CASH
								</label>
								<label className="form-check-label">
									<input
										value="CREDIT_CARD"
										type="radio"
										className="form-check-input ml-2"
										name="optradio"
										onChange={valueChangePa}
									/>
									CREDIT CARD
								</label>
							</div>
							<div className="form-check-inline">
								<p className="mt-3 mr-2">Promotion Type:</p>
								<select
									value={proDefaut}
									onChange={promoChange}
									disabled={totalPrice === '' || totalPrice === 0}
								>
									<option value="0">--Select Promotion--</option>
									{promotionType.map((promo, index) => {
										return (
											<option value={promo.id} key={promo.id}>
												{promo.name}
											</option>
										);
									})}
								</select>
							</div>

							<div className="text-center mt-2">
								<button className="btn btn-success" onClick={payBill}>
									<i className="fa fa-donate mr-2"></i>
									PAY BILL
								</button>
								<button className="btn btn-warning ml-2" onClick={mergeBIll}>
									<i className="fa fa-divide mr-2"></i>
									MERGE TABLE
								</button>
							</div>
						</div>
					</div>
				</div>
				<Modal show={modalShow} onHide={handleModalClose} backdrop="static" keyboard={false}>
					<Modal.Header closeButton>
						<Modal.Title>Choose Table:{' ' + tableCode}</Modal.Title>
					</Modal.Header>

					<Modal.Body>
						<div className="container text-center">
							<h5>Choose table to merge</h5>
							<div className="row tableMerge">
								{table.map((tab, ind) => {
									if (tab.id !== tableId) {
										return (
											<div className="col-lg-4  text-center p-" key={tab.id}>
												<div
													className="orderTable p-2"
													key={tab.id}
													onClick={() => chooseTable(tab.id, tab.code)}
												>
													<i className="fa fa-table mt-2"></i>
													<h6 className="mt-2"> {tab.code}</h6>
												</div>
											</div>
										);
									}
								})}
							</div>
						</div>
					</Modal.Body>
					<Modal.Footer>
						<Button variant="secondary" onClick={handleModalClose}>
							Close
						</Button>
						<Button variant="primary" onClick={mergeTable} type="submit" disabled={tableCode === ''}>
							Save
						</Button>
					</Modal.Footer>
				</Modal>
			</div>
		</Fragment>
	);
};

export default Order;
