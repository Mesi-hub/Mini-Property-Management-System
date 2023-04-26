import React, { useEffect, useRef, useState } from "react";
import "./Houses.css";
import House from "../House";
import { Link } from "react-router-dom";
import { getProperties } from "../../services/api/properties";
const Houses = (props) => {
    const formRef = useRef();
    const [propertiesList, setPropertiesList] = useState([]);
    const [filterData, setFilterData] = useState({
        city: "",
        max: 0,
        min: 0,
        room: 0,
    });

    useEffect(() => {
        getProperties(filterData).then((data) => setPropertiesList(data));
    }, [props, filterData]);

    const cardsCompo = propertiesList
        ? propertiesList.map((property) => (
              <House property={property} key={property.id} />
          ))
        : "Loading...";

    const filterHandler = (evt) => {
        evt.preventDefault();

        const form = formRef.current;
        setFilterData({
            city: form["city"].value,
            max: form["max"].value,
            min: form["min"].value,
            room: form["room"].value,
        });
    };
    return (
        <div>
            <div className="album py-5 bg-light">
                <div className="container">
                    <div className="row">
                        <div className="d-flex justify-content-center py-3">
                            <form ref={formRef} onSubmit={filterHandler}>
                                <ul className="nav nav-pills">
                                    <li className="nav-item">
                                        <div className="mb-2">
                                            <label
                                                for="exampleFormControlInput1"
                                                className="form-label"
                                            >
                                                City:
                                            </label>
                                            <input
                                                type="text"
                                                name="city"
                                                className="form-control form-control-sm"
                                                id="exampleFormControlInput1"
                                                placeholder="Location"
                                            />
                                        </div>
                                    </li>
                                    &nbsp;
                                    <li className="nav-item">
                                        <div className="mb-2">
                                            <label
                                                for="exampleFormControlInput1"
                                                className="form-label"
                                            >
                                                Max price:
                                            </label>
                                            <input
                                                type="number"
                                                name="max"
                                                className="form-control form-control-sm"
                                                id="exampleFormControlInput1"
                                                placeholder="Max price"
                                            />
                                        </div>
                                    </li>
                                    &nbsp;
                                    <li className="nav-item">
                                        <div className="mb-2">
                                            <label
                                                for="exampleFormControlInput1"
                                                className="form-label"
                                            >
                                                Min price:
                                            </label>
                                            <input
                                                type="number"
                                                name="min"
                                                className="form-control form-control-sm"
                                                id="exampleFormControlInput1"
                                                placeholder="Min price"
                                            />
                                        </div>
                                    </li>
                                    &nbsp;
                                    <li className="nav-item">
                                        <div className="mb-2">
                                            <label
                                                for="exampleFormControlInput1"
                                                className="form-label"
                                            >
                                                No. of bedroom:
                                            </label>
                                            <select
                                                className="form-select form-select-sm"
                                                aria-label=".form-select-sm example"
                                                name="room"
                                            >
                                                <option selected value="0">
                                                    Open this select menu
                                                </option>
                                                <option value="1">One</option>
                                                <option value="2">Two</option>
                                                <option value="3">Three</option>
                                                <option value="4">Four</option>
                                                <option value="5">Five</option>
                                                <option value="6">Six</option>
                                            </select>
                                        </div>
                                    </li>
                                    &nbsp;
                                    <li className="nav-item">
                                        <label
                                            for="exampleFormControlInput1"
                                            className="form-label"
                                        >
                                            &nbsp;
                                        </label>
                                        <div className="col-auto">
                                            <button
                                                type="submit"
                                                className="btn btn-primary mb-3 btn-sm"
                                            >
                                                Filter
                                            </button>
                                        </div>
                                    </li>
                                </ul>
                            </form>
                        </div>
                    </div>
                    <div className="row row-cols-1 row-cols-sm-2 row-cols-md-3 g-3">
                        {cardsCompo}
                    </div>
                </div>
            </div>
        </div>
    );
};

export default Houses;
