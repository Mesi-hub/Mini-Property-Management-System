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
        <div className="col-md-8 mx-auto mt-50 mb-50 py-5 px-5">
          <div className="jumbotron">
            <h1 className="display-4">View the Houses you've bookmarked!</h1>
            <p className="lead">
              Look at them again. Click Save from House detail to refer them later here.
            </p>
            
          </div>
        </div><div className="row row-cols-1 row-cols-sm-2 row-cols-md-3 g-3">
            {cardsCompo}
          </div>
        </div>
      </div>
    </div>
  );
};

export default SavedProperties;
