import React from "react";
import { Link} from "react-router-dom";

const Banner = () => {
    return (
        <>
            <section className="text-center position-relative ">
                <img
                    className="img-fluid "
                    src="https://static.rdc.moveaws.com/images/hero/default/2021-11/webp/hp-hero-desktop-xl.webp"
                />

                <div className="row py-lg-5  text-center position-absolute top-0 start-0">
                    <div className="col-lg col-md-8 mx-auto ">
                        <h1 className="text-white">Let's find the beautiful home you ever dreamt of.</h1>
                        <p className="lead  text-white    ">
                            Buying that special home, the dream or selling the current to elevate your imagination has never been easy.
                        </p>
                        <p>
                            <Link className="btn btn-primary my-2" to="/house-detail/1">The Masterpiece</Link> | 
                            <Link className="btn btn-primary my-2" to="/house-detail/2">The Luxury</Link>
                        </p>
                    </div>
                </div>
            </section>
        </>
    );
};

export default Banner;
