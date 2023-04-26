import React from "react";
import { Link } from "react-router-dom";

const SavedProperty = (props) => {
    return (
        <>
            <div className="col">
                <div className="card shadow-sm">
                    <div>
                        <Link to={"/house-detail/" + props.savedProperty.id}>
                            <img src="https://ap.rdcpix.com/5fe850a043989a3b24730a05621a8849l-m563540908od-w480_h360.webp" alt={props?.property?.title}/>
                        </Link>
                    </div>

                    <div className="card-body">
                        <Link to={"/house-detail/" + props?.property?.id}>
                            <h5 className="card-title">{props.savedProperty.property.title}</h5>
                        </Link>
                        <p className="card-text">
                            {props.savedProperty.property.description.substring(0, 200) + (props.savedProperty.property.description.length > 200 ? "..." : "")}
                        </p>                        
                    </div>
                </div>
            </div>
        </>
    );
};

export default SavedProperty;
