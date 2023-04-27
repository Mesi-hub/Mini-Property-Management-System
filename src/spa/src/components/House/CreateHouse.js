import React, { useEffect, useState } from "react";
import { Form, useNavigate } from "react-router-dom";
import { FaHome, FaMapMarkerAlt } from "react-icons/fa";
import { saveProperty, savePropertyImage  } from "../../services/api/properties";

const CreateHouse = (props) => {
  const navigate = useNavigate();
  const [inputState, setInputState] = useState({});
  const [selectedFile, setSelectedFile] = useState();

  const onChanged = (e) => {
    setInputState({
      ...inputState,
      [e.target.name]:
        e.target.files == null ? e.target.value : e.target.files[0],
    });
    console.log(inputState);
  };

  const onSubmitted = (e) => {
    e.preventDefault();

    const propeerty = {
      noOfBedrooms: inputState.bedrooms,
      noOfBathrooms: inputState.bathrooms,
      plotSize: inputState.plotSize,
      price: inputState.price,
      area: inputState.area,
      address: {
        street: inputState.street,
        city: inputState.city,
        state: inputState.state,
        zip: inputState.zip,
        longitude: null,
        latitude: null,
      },
      status: "PENDING",
      title: inputState.title,
      description: inputState.description,
      images: null,
    };

    const formData = new FormData();
    formData.append("file", inputState.files);

    savePropertyImage(formData)
      .then((res) => {
        console.log(res);
        const img = {
          id: res.id,
          fullPath: res.fullPath,
        };
        propeerty.images = [img];
        console.log(propeerty);
        saveProperty(propeerty)
          .then((res) => {
            console.log(res);
            navigate("/");
          })
          .catch((err) => {
            console.log(err);
          });
        });
  };

  return (
    <div>
      <div>
        <div className="container">
          <div className="row  mt-3">
            <div className="col-xs-12">
              <h4>
                <FaHome /> Property Information
              </h4>
            </div>
          </div>
          <div className="row mt-3">
            <div className="col-xs-12 col-sm-12">
              <label>Property Title</label>
              <input
                type="text"
                className="form-control"
                onChange={onChanged}
                name="title"
                placeholder="Property Name"
              />
            </div>
          </div>
          <div className="row  mt-3">
            <div className="col-xs-6 col-sm-4">
              <label>No of Bedrooms</label>
              <input
                type="number"
                name="bedrooms"
                id="bedrooms"
                onChange={onChanged}
                className="form-control"
                placeholder="No of bedrooms"
              />
            </div>
            <div className="col-xs-6 col-sm-4">
              <label>No of Bathrooms</label>
              <input
                type="number"
                name="bathrooms"
                id="bathrooms"
                onChange={onChanged}
                className="form-control"
                placeholder="No of bathrooms"
              />
            </div>
            <div className="col-xs-6 col-sm-4">
              <label>Price</label>
              <input
                type="number"
                name="price"
                id="price"
                onChange={onChanged}
                className="form-control"
                placeholder="Price"
              />
            </div>

            <div className="col-xs-6 col-sm-4">
              <label>Plot Size</label>
              <input
                type="number"
                name="plotSize"
                id="plotSize"
                onChange={onChanged}
                className="form-control"
                placeholder="Plot Size"
              />
            </div>
            <div className="col-xs-6 col-sm-4">
              <label>Household Area</label>
              <input
                type="number"
                name="area"
                id="area"
                onChange={onChanged}
                className="form-control"
                placeholder="Size"
              />
            </div>
          </div>
          <div className="row  mt-3">
            <div className="col-xs-12">
              <label>Description:</label>
              <textarea
                className="form-control"
                onChange={onChanged}
                name="description"
                id="description"
                placeholder="Property description"
              ></textarea>
            </div>
          </div>
          <div className="row  mt-3">
            <div className="col-xs-12">
              <label>Upload pictures:</label>
              <input
                type="file"
                id="files"
                onChange={onChanged}
                name="files"
                multiple
                className="form-control"
              />
            </div>
          </div>
          <h4>
            <FaMapMarkerAlt /> Address
          </h4>
          <div className="row  mt-3">
            <div className="col-xs-12 col-sm-12">
              <label>Street</label>
              <input
                type="text"
                name="street"
                onChange={onChanged}
                id="street"
                className="form-control"
                placeholder="Street"
              />
            </div>
            <div className="col-xs-12 col-sm-4">
              <label>City</label>
              <input
                type="text"
                name="city"
                id="city"
                onChange={onChanged}
                className="form-control"
                placeholder="City"
              />
            </div>
            <div className="col-xs-12 col-sm-4">
              <label>State</label>
              <input
                type="text"
                name="state"
                id="state"
                onChange={onChanged}
                className="form-control"
                placeholder="state"
              />
            </div>
            <div className="col-xs-12 col-sm-4">
              <label>Zip Code</label>
              <input
                type="number"
                name="zip"
                id="zip"
                onChange={onChanged}
                className="form-control"
                placeholder="Zip code"
              />
            </div>
          </div>
          <div className="row  mt-3">
            <div className="col-xs-8">
              <input
                type="button"
                name=""
                onClick={onSubmitted}
                className="form-control btn btn-success"
                value="Submit"
              />
            </div>
          </div>
        </div>
        <br />
        <br />
      </div>
    </div>
  );
};

CreateHouse.propTypes = {};

export default CreateHouse;
