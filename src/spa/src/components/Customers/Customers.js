import React, { useEffect, useState } from "react";
import { fetchAllCustomers } from "../../services/api/Customers";

const Customers = () => {
  const [customersState, setCustomersState] = useState([]);

  useEffect(() => {
    fetchAllCustomers()
      .then((response) => {
        console.log("fetchAllCustomers  res in comp: ", response);
        setCustomersState(response);
      })
      .catch((err) => {
        console.log("fetchAllCustomers - error: ", err);
      });
  }, []);

  const onApproveClick = (cust) => {
    console.log("onApproveClick");
  };
  const onDisableClick = (cust) => {
    console.log("onDisableClick");
  };

  const prepareAddressString = (cust) => {
    return (
      "" +
      (cust.address.street ? cust.address.street + ", " : "") +
      (cust.city ? cust.city + ", " : "") +
      (cust.state ? cust.state + ", " : "") +
      (cust.zip ? cust.zip : "")
    );
  };
  const prepareStatus = (cust) => {
    return cust.blackListed ? "Black listed" : "approved";
  };

  const isAlreadyApproved = (cust) => {
    return false;
  };

  const createSingleRowUIFromCustomer = (cust) => {
    return (
      <tr>
        <th scope="row">{cust.id}</th>
        <td>{cust.firstName}</td>
        <td>{cust.lastName}</td>
        <td>{cust.email}</td>
        <td>{prepareAddressString(cust)}</td>
        <td>{prepareStatus(cust)}</td>
        <td>
          {isAlreadyApproved() ? (
            <button
              type="button"
              class="btn btn-primary btn-sm"
              onClick={() => onDisableClick(cust)}
            >
              Disable
            </button>
          ) : (
            <button
              type="button"
              class="btn btn-primary btn-sm"
              onClick={() => onApproveClick(cust)}
            >
              Approve
            </button>
          )}
        </td>
      </tr>
    );
  };

  let customersAllRowsUI = [];
  if (customersState.length > 0) {
    customersAllRowsUI = customersState.map((cust) => {
      return createSingleRowUIFromCustomer(cust);
    });
  }

  return (
    <div className="content-center  row">
      <div className="col-md-10 mx-auto mt-50 mb-50 py-5 px-5">
        <div>
          <h3>Customers List </h3>
        </div>
        <table class="table table-bordered">
          <thead>
            <tr>
              <th scope="col">#</th>
              <th scope="col">First Name</th>
              <th scope="col">Last Name</th>
              <th scope="col">Email</th>
              <th scope="col">Address</th>
              <th scope="col">Status</th>
              <th scope="col">Action</th>
            </tr>
          </thead>
          <tbody>
            {customersAllRowsUI.length > 0
              ? customersAllRowsUI
              : "No customers found !!!"}
          </tbody>
        </table>
      </div>
    </div>
  );
};

export default Customers;
