import { useEffect, useState } from "react";
import {
  doOfferAction,
  fetchAllOffersToOwner,
  updateOfferStatus,
} from "../../services/api/Owners/OfferHistory";

const OwnerOffersHistory = () => {
  const [offersState, setOffersState] = useState([]);
  const [reloadOffers, setReloadOffers] = useState(false);

  useEffect(() => {
    fetchAllOffersToOwner(3)
      .then((response) => {
        setOffersState(response);
      })
      .catch((err) => {
        console.log("fetchAllOffersToOwner - error: ", err);
      });
  }, [reloadOffers]);

  const onActionClick = (offer, action) => {
    doOfferAction(offer.id, 3, action)
      .then((response) => {
        setReloadOffers(!reloadOffers);
      })
      .catch((err) => {
        console.log("onActionClick - error: ", err);
      });
  };

  const onEvaluateClick = (offer) => {
    doOfferAction(offer.id, 3, 'evaluate')
      .then((response) => {
        console.log("onCancleClick  res in comp: ", response);
        setReloadOffers(!reloadOffers);
      })
      .catch((err) => {
        console.log("onCancleClick - error: ", err);
      });
  };

  const onOfferClick = (offer) => {
    offer.status = "PENDING";
    //TODO: need to change customer id by loggeduser Id
    updateOfferStatus(offer, 3)
      .then((response) => {
        console.log("onOfferClick  res in comp: ", response);
        setReloadOffers(!reloadOffers);
      })
      .catch((err) => {
        console.log("onOfferClick - error: ", err);
      });
  };

  const prepareAddressString = (property) => {
    return (
      (property.address.street ? property.address.street + ", " : "") +
      (property.city ? property.city + ", " : "") +
      (property.state ? property.state + ", " : "") +
      (property.zip ? property.zip : "")
    );
  };

  const prepareStatus = (offer) => {
    return offer.status;
  };

  const createActionsUI = (offer) => {
    
      return (
        <>
        {(offer.status === "PENDING" || offer.status === "EVALUATING") ?
        <button
          type="button"
          className="btn btn-primary btn-sm"
          onClick={() => {
            onActionClick(offer, 'cancel');
          }}
        >
          Cancel
        </button> 
        : ""}
      
      {(offer.status === "PENDING") ?
        <button
          type="button"
          className="btn btn-primary btn-sm"
          onClick={() => {
            onActionClick(offer, 'evaluate');
          }}
        >
          Start Evaluating
        </button> : ""}
      {(offer.status === "EVALUATING") ?
        <button
          type="button"
          className="btn btn-primary btn-sm"
          onClick={() => {
            onActionClick(offer, 'accept');
          }}
        >
          Accept
        </button> : ""}
      {(offer.status === "ACCEPTED") ?
        <button
          type="button"
          className="btn btn-primary btn-sm"
          onClick={() => {
            onActionClick(offer, 'close');
          }}
        >
          Close
        </button> : ""}
        {(offer.status === "ACCEPTED") ?
        <button
          type="button"
          className="btn btn-primary btn-sm"
          onClick={() => {
            onActionClick(offer, 'cancel');
          }}
        >
          Cancel (Contingent!!!)
        </button> : ""}
        </>
      );
    }

  const createSingleRowUIFromData = (offer) => {
    return (
      <tr>
        <th scope="row">{offer.id}</th>
        <td>{offer.offerAmount}</td>
        <td>{offer.date}</td>
        <td>{offer.property.title}</td>
        <td>{prepareAddressString(offer.property)}</td>
        <td>{prepareStatus(offer)}</td>
        <td>{createActionsUI(offer)}</td>
      </tr>
    );
  };

  let offersAllRowsUI = [];
  if (offersState?.length && offersState.length > 0) {
    offersAllRowsUI = offersState.map((offer) => {
      return createSingleRowUIFromData(offer);
    });
  }

  return (
    <div className="content-center  row">
      <div className="col-md-8 mx-auto mt-50 mb-50 py-5 px-5">
          <div className="jumbotron">
            <h1 className="display-4">The offers you've got!</h1>            
          </div>
        </div><div className="col-md-10 mx-auto mt-50 mb-50 py-5 px-5">
        <table className="table table-bordered">
          <thead>
            <tr>
              <th scope="col">#</th>
              <th scope="col">Offer Amout</th>
              <th scope="col">Date</th>
              <th scope="col">Property Title</th>
              <th scope="col">Property Address</th>
              <th scope="col">Status</th>
              <th scope="col">Action</th>
            </tr>
          </thead>
          <tbody>
            {offersAllRowsUI.length > 0
              ? offersAllRowsUI
              : "No Offers found !!!"}
          </tbody>
        </table>
      </div>
    </div>
  );
};

export { OwnerOffersHistory };

/*

public enum OfferState {
    PENDING,
    EVALUATING,
    ACCEPTED,
    CANCELLED
}
  
  [
    {
        "id": 1,
        "date": "2023-04-11",
        "time": "10:00:00",
        "offerAmount": 225000.0,
        "property": {
            "id": 1,
            "noOfBedrooms": 2,
            "noOfBathrooms": 1.0,
            "plotSize": 6500.5,
            "price": 265000.0,
            "area": 1250.0,
            "address": {
                "id": 5,
                "street": "96th & Madison_1378",
                "city": "Madison",
                "state": "NY",
                "zip": "10128",
                "longitude": 40.78722936,
                "latitude": -73.95426939
            },
            "status": "PENDING",
            "title": "A masterpiece. With stunning views.",
            "description": "Leave your worries behind when you come home to Vail Quarters. Our pet-friendly apartments and townhomes in North Dallas near Galleria offer a sense of escape from the daily hustle. This feeling of tranquility and relaxation starts when you glimpse the verdant landscaping in our garden-style community and is enhanced by resort-style amenities and a variety of upscale features and floor plans designed to complement your lifestyle."
        },
        "customer": {
            "id": 3,
            "firstName": "Ben",
            "lastName": "Alpha",
            "email": "customer@customer.com",
            "address": {
                "id": 3,
                "street": "Lone Tree & Slatten Ranch - Antioch_5779 Lone Tree Way",
                "city": "Antioch",
                "state": "CA",
                "zip": "94531",
                "longitude": 37.96796292,
                "latitude": -121.7860158
            },
            "password": null,
            "blackListed": false
        },
        "status": "EVALUATING"
    }
]
  
  */
