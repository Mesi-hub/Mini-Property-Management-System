import { useRef } from "react";
import { useNavigate } from "react-router-dom";
import { registerNewCustomer } from "../../services/api/Customers";
import UsStatesDropDownOptions from "../../components/UsStatesDropDownOptions";

const SignUp = () => {
  const formRef = useRef();
  const navigate = useNavigate();

  const AddHandler = (evt) => {
    evt.preventDefault();

    const form = formRef.current;

    const newCustomerData = {
      firstName: form["firstname"].value,
      lastName: form["lastname"].value,
      email: form["email"].value,
      blackListed: false,
      password: form["password"].value,
      address: prepareAddress(),
    };

    registerNewCustomer(newCustomerData)
      .then((res) => {
        console.log(res);
        navigate("/customers");
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
    <div>
      <div className="content-center  row">
        <div className="col-md-8 mx-auto mt-50 mb-50 py-5 px-5">
          <div>
            <h3>Sign up</h3>
          </div>
          <form id="newcustomerform" ref={formRef} onSubmit={AddHandler}>
            <div className="form-group">
              <label for="firstname" className="form-label">
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
              <label for="lastname" className="form-label">
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
              <label for="email" className="form-label">
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
              <label for="password" className="form-label">
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
              <label for="street" className="form-label">
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
              <label for="city" className="form-label">
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
              <label for="state" className="form-label">
                State
              </label>
              <select className="form-control"
                id="state"
                name="state">
                <UsStatesDropDownOptions/> 
              </select>              
            </div>
            <div className="form-group">
              <label for="zip" className="form-label">
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
              <label for="longitude" className="form-label">
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
              <label for="longitude" className="form-label">
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
  );
};

export default SignUp;
