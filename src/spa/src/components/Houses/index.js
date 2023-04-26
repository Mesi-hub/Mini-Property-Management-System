import React, { useEffect, useState } from "react";
import "./Houses.css";
import House from "../House";
import { Link } from "react-router-dom";
import { getProperties } from "../../services/api/properties";
const Houses = (props) => {
  const [propertiesList, setPropertiesList] = useState([]);
  useEffect(() => {
    getProperties()
      .then((data) => setPropertiesList(data));
  }, [props]);

  const cardsCompo = propertiesList? propertiesList.map((property) => <House property={property} key={property.id}/>) :"Loading...";

  return (
    <div>
      <div className="album py-5 bg-light">
        <div className="container">
          <div className="row row-cols-1 row-cols-sm-2 row-cols-md-3 g-3">
            {cardsCompo}
          </div>
        </div>
      </div>
    </div>
  );
};

export default Houses;
