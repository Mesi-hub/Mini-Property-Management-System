import React from "react";
import "./HouseDetail";

const HouseDetail = () => {
    return (
        <div>
            <div className="container mt-5 mb-5">
                <div className="card">
                    <div className="row g-0">
                        <div className="col-md-6 border-end">
                            <img
                                src="https://ap.rdcpix.com/de02eeb19bbf4ed177ba516795534095l-m3043654886od-w480_h360_x2.webp"
                                className="main_product_image"
                                width={640}
                                height={481}
                            />
                        </div>
                        <div className="col-md-6">
                            <div className="p-3 right-side">
                                <div className="d-flex justify-content-between align-items-center">
                                    <h3>IIlana</h3>
                                    <span className="heart">
                                        <i className="bx bx-heart"></i>
                                    </span>
                                </div>
                                <div className="mt-2 pr-3 content">
                                    <p>
                                        Lorem ipsum dolor sit amet, consectetur
                                        adipiscing elit, sed do eiusmod tempor
                                        incididunt ut labore et dolore magna
                                        aliqua Lorem ipsum dolor sit amet,
                                        consectetur adipiscing elit, sed do
                                        eiusmod tempor incididunt ut labore et
                                        dolore magna aliqua
                                    </p>
                                </div>
                                <h3>$430.99</h3>
                                <div className="ratings d-flex flex-row align-items-center">
                                    <div className="d-flex flex-row">
                                        <i className="bx bxs-star"></i>{" "}
                                        <i className="bx bxs-star"></i>
                                        <i className="bx bxs-star"></i>{" "}
                                        <i className="bx bxs-star"></i>
                                        <i className="bx bx-star"></i>
                                    </div>
                                    <span>441 reviews</span>
                                </div>

                                <div className="buttons d-flex flex-row mt-5 gap-3">
                                    <button className="btn btn-outline-dark">
                                        Buy Now
                                    </button>
                                    <button className="btn btn-dark">
                                        Add to Basket
                                    </button>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    );
};

export default HouseDetail;
