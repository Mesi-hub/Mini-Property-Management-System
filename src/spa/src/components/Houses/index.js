import React from "react";
import "./Houses.css";
import House from "../House";
const Houses = () => {
    const cards = [1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1];

    const cardsCompo = cards.map((card) => <House />);

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
