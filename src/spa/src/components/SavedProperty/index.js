import React from "react";
import { Link, useNavigate } from "react-router-dom";
import { removePropertyfromSavedList } from "../../services/api/SavedProperties";

const SavedProperty = (props) => {
    const navigate = useNavigate();
    const onDelete = (id) => {
        removePropertyfromSavedList(id)
          .then((res) => {
            console.log(res);
            navigate("/");
          })
          .catch((err) => {
            console.log(err);
          });
      };
  return (
    <>
      <div className="col">
        <div className="card shadow-sm">
          <div>
            <Link to={"/house-detail/" + props.savedProperty.id}>
              <img
                src={props?.savedProperty?.images[0].fullPath}
                alt={props?.property?.title}
              />
            </Link>
          </div>

          <div className="card-body">
            <Link to={"/house-detail/" + props?.savedProperty?.id}>
              <h5 className="card-title">
                {props.savedProperty.property.title}
              </h5>
            </Link>
            <p className="card-text">
              {props.savedProperty.property.description.substring(0, 200) +
                (props.savedProperty.property.description.length > 200
                  ? "..."
                  : "")}
            </p>
          </div>
          <div className="buttons d-flex flex-row mt-5 gap-3">
            <button className="btn btn-dark" onClick={()=>{onDelete(props.savedProperty.id)}}>Remove from saved list</button>
          </div>
        </div>
      </div>
    </>
  );
};

export default SavedProperty;
