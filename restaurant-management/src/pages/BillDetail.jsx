/* eslint-disable no-unused-vars */
import React, { Fragment, useState, useEffect } from 'react';
import billDetailService from './../services/BillDetailService.js';
import { useFormik } from 'formik';
import * as Yup from 'yup';
import { Button, FormControl, InputGroup, Modal } from 'react-bootstrap';
import Input from '../controls/Input';
import Select from '../controls/select';
import queryString from 'query-string';
import productService from './../services/productService';

const BillDetail = (props) => {
	const [billDetail, setBillDetail] = useState([]);
	const [billId, setBillId] = useState(new URLSearchParams(props.location.search).get('id'));
	const getCurrenDate = () => {
		let tempDate = new Date();
		let date = tempDate.getFullYear() + '-' + (tempDate.getMonth() + 1) + '-' + tempDate.getDate();
		return date;
	};
	const [currentDate, setCurrentdate] = useState(getCurrenDate());

	const loadBillDetail = (id) => {
		billDetailService.get(id).then((res) => {
			setBillDetail(res);
		});
	};
	useEffect(() => {
		loadBillDetail(billId);
	}, []);
	return (
		<Fragment>
			<div className="container">
				<div className="card border-primary bt-primary-5">
					<div className="card-header">
						<div className="row">
							<div className="col">
								<h2 className="card-title">Bill Details List</h2>
							</div>
						</div>
					</div>
					<div className="card-body">
						<table className="table table-hover table-striped table-bordered text-center">
							<thead>
								<tr className="table-primary">
									<th scope="col">No.</th>
									<th scope="col">Bill Id</th>
									<th scope="col">Product Id</th>
									<th scope="col">Amount</th>
									<th scope="col">Order Date</th>
									<th scope="col">Price</th>
									<th scope="col">quantity completed</th>
									<th scope="col">finish day </th>
								</tr>
							</thead>
							<tbody>
								{billDetail.map((billD, idx) => {
									return (
										<tr key={idx}>
											<th scope="row">{idx + 1}</th>
											<td>{billD.bill.id}</td>
											<td>{billD.product.id}</td>
											<td>{billD.amount}</td>
											{/* <td>{moment(billD.orderDate).format('DD/MM/YYYY')}</td> */}
											<td>{billD.orderDate}</td>
											<td>{billD.price}</td>
											<td>{billD.finishedProductNum}</td>
											<td>{billD.finishedProductDate}</td>
										</tr>
									);
								})}
							</tbody>
						</table>
					</div>
				</div>
			</div>
		</Fragment>
	);
};

export default BillDetail;
