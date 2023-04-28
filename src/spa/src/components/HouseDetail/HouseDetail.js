import React, {useEffect, useRef, useState} from "react";
import "./HouseDetail";
import {useNavigate, useParams} from "react-router";
import {hasRole} from "../../services/api";
import {savePropertyToSavedList} from "../../services/api/SavedProperties";
import {getPropertyById, saveOffer} from "../../services/api/properties";

const HouseDetail = () => {
    const params = useParams();
    const navigate = useNavigate();
    const [property, setProperty] = useState({});
    const offerAmountRef = useRef()
    useEffect(() => {
        getPropertyById(params.id).then((data) => setProperty(data));
    }, []);

    const makeOfferClick = () => {
        const newOffer = {
            "offerAmount": offerAmountRef.current.value,
            "property": {
                "id": property.id
            },
            "customer": {
                "id": 3
            },
            "status": "PENDING"
        }
        saveOffer(newOffer)
            .then(response => {
                navigate("/customer-offers-history");
                console.log("makeOfferClick saved")
            })
            .catch(err => {
                console.log("makeOfferClick error")
            })
    }
    const onSave = (id) => {
        savePropertyToSavedList({property: {id: id}})
            .then((res) => {
                console.log(res);
                navigate("/saved-properties");
            })
            .catch((err) => {
                console.log(err);
            });
    };

    const onSendMessage = (id) => {
        navigate("/send_message/property/" + id);
    };

    return (property && property.id) ? (
        <div>
            <div className="container mt-5 mb-5">
                <div className="card">
                    <div className="row g-0">
                        <div className="col-md-6 border-end">
                            <img
                                alt="house"
                                src={property?.images[0].fullPath}
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
                                <br/>
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
                                        
                                            <button
                                                className="btn btn-dark"
                                                onClick={() => {
                                                    onSave(property?.id);
                                                }}
                                            >
                                                Save
                                            </button>
                                        {(property.status === 'AVAILABLE' || property.status === 'PENDING' ) ?
                                        <>

                                        <div className="buttons d-flex flex-row mt-1 gap-3">
                                            <button
                                                className="btn btn-dark"
                                                onClick={() => {
                                                    onSendMessage(property?.id);
                                                }}
                                            >
                                                Send Message
                                            </button>
                                        </div> <br/><br/>
                                        <div className="buttons d-flex flex-row mt-1 gap-3">
                                            <label className="form-label">Offer amount: </label>
                                            <input className="form-control" type="number" ref={offerAmountRef} name="offeramount"/>
                                            <button className="btn btn-dark" onClick={makeOfferClick}>
                                                Make an offer
                                            </button>
                                        </div>
                                        </>: ""}
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
