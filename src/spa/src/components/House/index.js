import React from "react";
import "./House.css";
import { Link } from "react-router-dom";

const House = () => {
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
                            This is a wider card with supporting text below as a
                            natural lead-in to additional content.
                        </p>
                        <ul class="list-group list-group-flush">
                            <li class="list-group-item">An item</li>
                            <li class="list-group-item">A second item</li>
                            <li class="list-group-item">A third item</li>
                        </ul>
                    </div>
                </div>
            </div>
        </>
    );
};

export default House;
