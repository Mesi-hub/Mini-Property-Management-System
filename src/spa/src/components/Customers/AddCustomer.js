import { useRef } from "react";
import { Link, useNavigate } from "react-router-dom";
import { registerNewCustomer } from "../../services/api/Customers";

const AddCustomer = (props) => {
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
      user: { id: 3 },
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
    <div className="NewCustomer">
      <form id="newcustomerform" ref={formRef} onSubmit={AddHandler}>
        <h1>Register Customer</h1>
        <table>
          <tbody>
            <tr>
              <td></td>
              <td></td>
            </tr>
            <tr>
              <td>
                <label>First Name</label>
              </td>
              <td>
                <input type="text" label={"title"} name={"firstname"} />
              </td>
            </tr>
            <tr>
              <td>
                <label>Last Name</label>
              </td>
              <td>
                <input type="text" label={"title"} name={"lastname"} />
              </td>
            </tr>
            <tr>
              <td>
                <label>Email</label>
              </td>
              <td>
                <input type="text" label={"title"} name={"email"} />
              </td>
            </tr>
            <tr>
              <td>Address</td>
              <td></td>
            </tr>
            <tr>
              <td>
                <label>Street</label>
              </td>
              <td>
                <input type="text" label={"street"} name={"street"} />
              </td>
            </tr>
            <tr>
              <td>
                <label>City</label>
              </td>
              <td>
                <input type="text" label={"city"} name={"city"} />
              </td>
            </tr>
            <tr>
              <td>
                <label>State</label>
              </td>
              <td>
                <input type="text" label={"state"} name={"state"} />
              </td>
            </tr>
            <tr>
              <td>
                <label>Zip</label>
              </td>
              <td>
                <input type="text" label={"zip"} name={"zip"} />
              </td>
            </tr>
            <tr>
              <td>
                <label> Longitude</label>
              </td>
              <td>
                <input type="text" name={"longitude"} />{" "}
              </td>
            </tr>
            <tr>
              <td>
                <label> Latitude</label>
              </td>
              <td>
                <input type="text" name={"latitude"} />{" "}
              </td>
            </tr>
            <tr>
              <td></td>
              <td></td>
            </tr>
            <tr>
              <td></td>
              <td></td>
            </tr>
            <tr>
              <td colSpan={2}>
                <button>Register </button>
              </td>
            </tr>
          </tbody>
        </table>
      </form>
    </div>
  );
};

export default AddCustomer;

/*
[
    {
        "id": 3,
        "firstName": "Ben",
        "lastName": "Alpha",
        "email": "customer@customer.com",
        "address": {
            "id": 3,
            "street": "Lone Tree & Slatten Ranch - Antioch_5779 Lone Tree Way",
            "city": "Antioch",
            "state": "CA",
            "zip": "94531",
            "longitude": 37.96796292,
            "latitude": -121.7860158
        },
        "user": {
            "id": 3,
            "name": "customer",
            "roles": [
                {
                    "role": "CUSTOMER",
                    "description": "Customer Privileges"
                }
            ]
        },
        "blackListed": false
    }
]

*/
