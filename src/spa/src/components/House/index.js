import React from "react";
import "./House.css";
import { Link } from "react-router-dom";

const House = (props) => {
    return (
        <>
            <div className="col">
                <div className="card shadow-sm">
                    <div>
                        <Link to="/house-detail/1">
                            <a href="#" className="img-wrap" data-abc="true">
                                <img src="https://ap.rdcpix.com/5fe850a043989a3b24730a05621a8849l-m563540908od-w480_h360.webp" />
                            </a>
                        </Link>
                    </div>

                    <div className="card-body">
                        <Link to="/house-detail/1">
                            <h5 class="card-title">Card title</h5>
                        </Link>
                        <p className="card-text">
                            {props.property.description}
                        </p>
                        <ul class="list-group list-group-flush">
                            <li class="list-group-item">Price $  {props.property.price}</li>
                            <li class="list-group-item">BedRooms:  {props.property.noOfBedrooms}</li>
                            <li class="list-group-item">Bathrooms.  {props.property.noOfBathrooms}</li>
                            <li class="list-group-item">Area.  {props.property.area}</li>
                            <li class="list-group-item">Land Extent.  {props.property.plotSize}</li>
                        </ul>
                    </div>
                </div>
            </div>
        </>
    );
};

export default House;
