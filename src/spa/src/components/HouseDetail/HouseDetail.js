import React, { useEffect, useState } from "react";
import "./HouseDetail";
import { getPropertyById } from "../../services/api/properties";
import { useNavigate, useParams } from "react-router";
import { hasRole } from "../../services/api";
import { savePropertyToSavedList } from "../../services/api/SavedProperties";

const HouseDetail = () => {
  const params = useParams();
const navigate = useNavigate();
  const [property, setProperty] = useState({});
  useEffect(() => {
    getPropertyById(params.id).then((data) => setProperty(data));
  }, []);

  const onSave = (id) => {
    savePropertyToSavedList({property :{id: id}})
      .then((res) => {
        console.log(res);
        navigate("/saved-properties");
      })
      .catch((err) => {
        console.log(err);
      });
  };

  return property ? (
    <div>
      <div className="container mt-5 mb-5">
        <div className="card">
          <div className="row g-0">
            <div className="col-md-6 border-end">
              <img
                alt="house"
                src="https://ap.rdcpix.com/de02eeb19bbf4ed177ba516795534095l-m3043654886od-w480_h360_x2.webp"
                className="image"
              />
            </div>
            <div className="col-md-6">
              <div className="p-3 right-side">
                <div className="d-flex justify-content-between align-items-center">
                  <h3>{property.title}</h3>
                  <span className="heart">
                    <i className="bx bx-heart"></i>
                  </span>
                </div>
                <ul className="list-group list-group-flush">
                  <li className="list-group-item">Price : {property.price}$</li>
                  <li className="list-group-item">
                    Status : <b>{property.status}</b>
                  </li>
                </ul>
                <div className="mt-2 pr-3 content">
                  <p>{property.description}</p>
                </div>
                <br />
                <h4>Additional information</h4>
                <ul className="list-group list-group-flush">
                  <li className="list-group-item">
                    BedRooms: {property.noOfBedrooms}
                  </li>
                  <li className="list-group-item">
                    Bathrooms. {property.noOfBathrooms}
                  </li>
                  <li className="list-group-item">Area. {property?.area}</li>
                  <li className="list-group-item">
                    Land Extent. {property?.plotSize}
                  </li>
                </ul>
                {hasRole("CUSTOMER") ? (
                  <>
                    <div className="buttons d-flex flex-row mt-5 gap-3">
                      <button className="btn btn-dark" onClick={()=>{onSave(property?.id)}}>Save</button>
                    </div>
                    <div className="buttons d-flex flex-row mt-5 gap-3">
                      <button className="btn btn-dark">Make an offer</button>
                    </div>

                    <div className="buttons d-flex flex-row mt-5 gap-3">
                      <button className="btn btn-dark">Send Message</button>
                    </div>
                  </>
                ) : (
                  ""
                )}
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  ) : (
    ""
  );
};

export default HouseDetail;
