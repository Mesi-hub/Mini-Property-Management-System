import { useState } from "react";
import { registerCurrentAsNewOwner } from "../../services/api/Owners";
import { hasRole } from "../../services/api";

const BecomeAnOwner = () => {
  const [registrationRequested, setRegistrationRequested] = useState(false);
  const onBecomeAnOnwerClick = () => {
    registerCurrentAsNewOwner()
      .then((response) => {
        console.log("onWhitelistedClick  res: ", response);
        setRegistrationRequested(true);
      })
      .catch((err) => {
        console.log("onWhitelistedClick - error: ", err);
      });
  };
  return (
    <div className="content-center  row">
      <div className="col-md-8 mx-auto mt-50 mb-50 py-5 px-5">
        <div className="jumbotron">
          <h1 className="display-4">Connect with local buyers!</h1>
          <p className="lead">
            Advertise where millions of home buyers search.
          </p>
          <hr className="my-4" />
          <p>
            Deciding to sell your home yourself is referred to as
            for-sale-by-owner (FSBO). The FSBO process is similar to traditional
            selling, but without the help of a real estate agent. In this case,
            you’re responsible for the home prep, marketing, showings, and
            negotiations..
          </p>
          {hasRole("OWNER") ? (
            <p className="lead">
              <button
                onClick={onBecomeAnOnwerClick}
                className="btn btn-primary btn-lg"
              >
                Get Started
              </button>
            </p>
          ) : (
            <p className="lead">
              <button
                className="btn btn-primary btn-lg disabled"
              >
                Acceptance Pending
              </button>
            </p>
          )}
        </div>
      </div>
    </div>
  );
};

export default BecomeAnOwner;
