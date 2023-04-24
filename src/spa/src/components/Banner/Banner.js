import React from "react";

const Banner = () => {
    return (
        <>
            <section className="text-center position-relative ">
                <img
                    className="img-fluid "
                    src="https://static.rdc.moveaws.com/images/hero/default/2021-11/webp/hp-hero-desktop-xl.webp"
                />

                <div className="row py-lg-5  text-center position-absolute top-0 start-0">
                    <div className="col-lg-6 col-md-8 mx-auto mt-8">
                        <h1 className="text-white">Album example</h1>
                        <p className="lead  text-white    ">
                            Something short and leading about the collection
                            below—its contents, the creator, etc. Make it short
                            and sweet, but not too short so folks don’t simply
                            skip over it entirely.
                        </p>
                        <p>
                            <a href="#" className="btn btn-primary my-2">
                                Main call to action
                            </a>{" "}
                            &nbsp;
                            <a href="#" className="btn btn-secondary my-2">
                                Secondary action
                            </a>
                        </p>
                    </div>
                </div>
            </section>
        </>
    );
};

export default Banner;
