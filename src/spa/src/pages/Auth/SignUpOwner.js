import { useRef } from "react";
import { useNavigate } from "react-router-dom";
import { registerNewOwner } from "../../services/api/Owners";
import UsStatesDropDownOptions from "../../components/UsStatesDropDownOptions";
import { hasRole, ownerApprovalPending } from "../../services/api";
import { useSelector } from "react-redux";
import { isLoggedIn } from "../../feature/Authentication/authenticationSlice";

const SignUpOwner = () => {
  const store = useSelector((state) => state);
  const formRef = useRef();
  const navigate = useNavigate();

  const AddHandler = (evt) => {
    evt.preventDefault();

    const form = formRef.current;

    const newOwnerData = {
      firstName: form["firstname"].value,
      lastName: form["lastname"].value,
      email: form["email"].value,
      blackListed: false,
      password: form["password"].value,
      address: prepareAddress(),
    };

    registerNewOwner(newOwnerData)
      .then((res) => {
        console.log(res);
        navigate("/");
      })
      .catch((err) => {
        console.log(err);
      });
  };

  const prepareAddress = () => {
    const form = formRef.current;
    let addr = {
      street: form["street"].value,
      city: form["city"].value,
      state: form["state"].value,
      zip: form["zip"].value,
      longitude: form["longitude"].value,
      latitude: form["latitude"].value,
    };
    return addr;
  };

  return (
    <>
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
              for-sale-by-owner (FSBO). The FSBO process is similar to
              traditional selling, but without the help of a real estate agent.
              In this case, you’re responsible for the home prep, marketing,
              showings, and negotiations..
            </p>
          </div>
        </div>
        {!isLoggedIn(store) ? (
          <div>
            <div className="content-center  row">
              <div className="col-md-8 mx-auto mt-50 mb-50 py-5 px-5">
                <div>
                  <h3>Sign up</h3>
                </div>
                <form id="newownerform" ref={formRef} onSubmit={AddHandler}>
                  <div className="form-group">
                    <label htmlFor="firstname" className="form-label">
                      First Name
                    </label>
                    <input
                      type="text"
                      className="form-control"
                      id="firstname"
                      name="firstname"
                    />
                  </div>
                  <div className="form-group">
                    <label htmlFor="lastname" className="form-label">
                      Last Name
                    </label>
                    <input
                      type="text"
                      className="form-control"
                      id="lastname"
                      name="lastname"
                    />
                  </div>
                  <div className="form-group">
                    <label htmlFor="email" className="form-label">
                      Email
                    </label>
                    <input
                      type="email"
                      className="form-control"
                      id="email"
                      name="email"
                    />
                  </div>
                  <div className="form-group">
                    <label htmlFor="password" className="form-label">
                      Password
                    </label>
                    <input
                      type="password"
                      className="form-control"
                      id="password"
                      name="password"
                    />
                  </div>
                  Address:
                  <div className="form-group">
                    <label htmlFor="street" className="form-label">
                      Street
                    </label>
                    <input
                      type="text"
                      className="form-control"
                      id="street"
                      name="street"
                    />
                  </div>
                  <div className="form-group">
                    <label htmlFor="city" className="form-label">
                      City
                    </label>
                    <input
                      type="text"
                      className="form-control"
                      id="city"
                      name="city"
                    />
                  </div>
                  <div className="form-group">
                    <label htmlFor="state" className="form-label">
                      State
                    </label>
                    <select className="form-control" id="state" name="state">
                      <UsStatesDropDownOptions />
                    </select>
                  </div>
                  <div className="form-group">
                    <label htmlFor="zip" className="form-label">
                      Zip
                    </label>
                    <input
                      type="text"
                      className="form-control"
                      id="zip"
                      name="zip"
                    />
                  </div>
                  <div className="form-group">
                    <label htmlFor="longitude" className="form-label">
                      Longitude
                    </label>
                    <input
                      type="number"
                      className="form-control"
                      id="longitude"
                      name="longitude"
                      step="0.0000001"
                    />
                  </div>
                  <div className="form-group">
                    <label htmlFor="longitude" className="form-label">
                      Latitude
                    </label>
                    <input
                      type="number"
                      className="form-control"
                      id="latitude"
                      name="latitude"
                      step="0.0000001"
                    />
                  </div>
                  <button type="submit" className="btn btn-primary">
                    Submit
                  </button>
                </form>
              </div>
            </div>
          </div>
        ) : (
          ""
        )}
        {hasRole("OWNER") &&
          ownerApprovalPending(store)(
            <p className="lead">
              <button className="btn btn-primary btn-lg disabled">
                Acceptance Pending
              </button>
            </p>
          )}
      </div>
    </>
  );
};

export default SignUpOwner;
