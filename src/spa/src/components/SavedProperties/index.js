import React, { useEffect, useState } from "react";
import { getSavedProperties } from "../../services/api/SavedProperties";
import SavedProperty from "../SavedProperty";
const SavedProperties = (props) => {
  const [savedPropertiesList, setSavedPropertiesList] = useState([]);
  useEffect(() => {
    getSavedProperties()
      .then((data) => setSavedPropertiesList(data));
  }, [props]);

  const cardsCompo = savedPropertiesList? savedPropertiesList.map((savedProperty) => <SavedProperty savedProperty={savedProperty} key={savedProperty.id}/>) :"Loading...";

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

export default SavedProperties;
