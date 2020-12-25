import React, { useState, Fragment, useEffect } from "react";
// import Input from "../../controls/Input";
// import { Button, FormControl, InputGroup, Modal } from "react-bootstrap";

const UploadFile = (props) => {
  const [file, setFile] = useState(null);

  useEffect(() => {}, []);

  const handleChange = (event) => {
    setFile(URL.createObjectURL(event.target.files[0]));
  };

  return (
    <div>
      <input type="file" onChange={handleChange} />
      <img src={file} alt="Example img" />
    </div>
  );
};

export default UploadFile;
