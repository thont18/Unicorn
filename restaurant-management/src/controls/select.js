import React, { Component } from 'react';

class select extends Component {
	render() {
		const {
			id,
			name,
			label,
			labelSize,
			placeholder,
			readonly,
			rows,
			cols,
			inputRef,
			maxLength,
			// add validations
			frmField,
			err,
			errMessage,
			...others
		} = this.props;
		const size = labelSize ? labelSize : 3;
		const classLeft = `col-sm-${size} col-form-label`;
		const classRight = `col-sm-${12 - size}`;
		// const numRows = rows ? rows : 1;
		const inputClass = `form-control ${err ? 'is-invalid' : ''}`;
		return (
			<div className="form-group row">
				<label htmlFor={id} className={classLeft}>
					{label}
				</label>
				<div className={classRight}>
					<select ref={inputRef} id={id} className={inputClass} {...others} {...frmField}></select>
					{err ? <div className="invalid-feedback">{errMessage}</div> : null}
				</div>
			</div>
		);
	}
}

export default select;
