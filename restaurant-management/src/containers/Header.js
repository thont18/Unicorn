/* eslint-disable jsx-a11y/anchor-is-valid */
import React, { Component } from 'react';
import { Link } from 'react-router-dom';

class Header extends Component {
	render() {
		return (
			<nav className="navbar navbar-expand-md navbar-dark bg-primary">
				<div className="container">
					<Link className="navbar-brand" to="/">
						Restaurant Management
					</Link>
					<button
						className="navbar-toggler"
						type="button"
						data-toggle="collapse"
						data-target="#navbarSupportedContent"
						aria-controls="navbarSupportedContent"
						aria-expanded="false"
						aria-label="Toggle navigation"
					>
						<span className="navbar-toggler-icon"></span>
					</button>

					<div className="collapse navbar-collapse" id="navbarSupportedContent">
						<ul className="navbar-nav mr-auto">
							<li className="nav-item nav-link active">
								<Link className="nav-link" to="/order">
									Order <span className="sr-only">(current)</span>
								</Link>
							</li>
							<li className="nav-item nav-link">
								<Link className="nav-link" to="/productTypes">
									Product Types <span className="sr-only">(current)</span>
								</Link>
							</li>

							<li className="nav-item nav-link">
								<Link className="nav-link" to="/positions">
									Positions <span className="sr-only">(current)</span>
								</Link>
							</li>

							<li className="nav-item nav-link">
								<Link className="nav-link" to="/employees">
									Employees <span className="sr-only">(current)</span>
								</Link>
							</li>

							<li className="nav-item nav-link">
								<Link className="nav-link" to="/products">
									Products <span className="sr-only">(current)</span>
								</Link>
							</li>
							<li className="nav-item nav-link">
								<Link className="nav-link" to="/bills">
									Bill <span className="sr-only">(current)</span>
								</Link>
							</li>
							{/* <li className="nav-item nav-link">
                <Link className="nav-link" to="/accounts">
                  Accounts <span className="sr-only">(current)</span>
                </Link>
              </li> */}
						</ul>
						<ul className="navbar-nav">
							<li className="nav-item">
								<Link to="/home" className="nav-link">
									Welcome to our Website
								</Link>
							</li>
							<li className="nav-item">
								<Link to="/login" className="nav-link">
									<i className="fas fa-sign-out-alt text-danger"></i>
								</Link>
							</li>
						</ul>
					</div>
				</div>
			</nav>
		);
	}
}

export default Header;
