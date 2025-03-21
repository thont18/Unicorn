import React, { Component } from 'react';

class Input extends Component {
	state = {};
	render() {
		const {
			type,
			id,
			name,
			label,
			labelSize,
			placeholder,
			value,
			readonly,
			rows,
			cols,
			inputRef,
			maxLength,
			checked,
			onChange,

			// add validations
			frmField,
			err,
			errMessage,
			// ...others
		} = this.props;
		const size = labelSize ? labelSize : 3;
		const classLeft = `col-sm-${size} col-form-label`;
		const classRight = `col-sm-${12 - size}`;
		const numRows = rows ? rows : 1;
		const inputClass = `form-control ${err ? 'is-invalid' : ''}`;
		return (
			<div className="form-group row">
				<label htmlFor={id} className={classLeft}>
					{label}
				</label>
				<div className={classRight}>
					{/* {others["rows"] > 1 ? (
            <textarea
              ref={inputRef}
              id={id}
              className={inputClass}
              {...others}
              {...frmField}
            ></textarea>
          ) : (
            <input
              ref={inputRef}
              id={id}
              className={inputClass}
              {...others}
              {...frmField}
            />
          )} */}
					{numRows === 1 ? (
						<input
							ref={inputRef}
							type={type}
							// className="form-control"
							className={inputClass}
							id={id}
							name={name}
							value={value}
							checked={checked}
							onChange={onChange}
							maxLength={maxLength}
							placeholder={placeholder}
							readOnly={readonly}
							{...frmField}
						/>
					) : (
						<textarea
							ref={inputRef}
							name={name}
							id={id}
							// className="form-control"
							className={inputClass}
							maxLength={maxLength}
							cols={cols}
							value={value}
							checked={checked}
							onChange={onChange}
							rows={numRows}
							placeholder={placeholder}
							readOnly={readonly}
							{...frmField}
						></textarea>
					)}
					{err ? <div className="invalid-feedback">{errMessage}</div> : null}
				</div>
			</div>
		);
	}
}

export default Input;
