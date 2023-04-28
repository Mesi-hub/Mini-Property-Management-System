import React, { useEffect, useState } from "react";
import {
  addToBlacklist,
  addToWhitelist,
  fetchAllCustomers,
} from "../../services/api/Customers";

const Customers = () => {
  const [customersState, setCustomersState] = useState([]);
  const [reloadCustomers, setReloadCustomers] = useState(false);

  useEffect(() => {
    fetchAllCustomers()
      .then((response) => {
        console.log("fetchAllCustomers  res in comp: ", response);
        setCustomersState(response);
      })
      .catch((err) => {
        console.log("fetchAllCustomers - error: ", err);
      });
  }, [reloadCustomers]);

  const onWhitelistedClick = (cust) => {
    console.log("onWhitelistedClick");
    addToWhitelist(cust)
      .then((response) => {
        console.log("onWhitelistedClick  res: ", response);
        setReloadCustomers(!reloadCustomers);
      })
      .catch((err) => {
        console.log("onWhitelistedClick - error: ", err);
      });
  };

  const onBlacklistedClick = (cust) => {
    console.log("onBlacklistedClick");
    addToBlacklist(cust)
      .then((response) => {
        console.log("onBlacklistedClick  res: ", response);
        setReloadCustomers(!reloadCustomers);
      })
      .catch((err) => {
        console.log("onBlacklistedClick - error: ", err);
      });
  };

  const prepareAddressString = (cust) => {
    return (
      (cust.address.street ? cust.address.street + ", " : "") +
      (cust.city ? cust.city + ", " : "") +
      (cust.state ? cust.state + ", " : "") +
      (cust.zip ? cust.zip : "")
    );
  };

  const prepareStatus = (cust) => {
    return cust.blackListed ? "Black listed" : "Regular   ";
  };

  const createActionsUI = (cust) => {
    if (cust.blackListed) {
      return (
        <button
          type="button"
          className="btn btn-primary btn-sm"
          onClick={() => onWhitelistedClick(cust)}
        >
          White list
        </button>
      );
    } else {
      return (
        <button
          type="button"
          className="btn btn-primary btn-sm"
          onClick={() => onBlacklistedClick(cust)}
        >
          Black List
        </button>
      );
    }
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
        <td>{createActionsUI(cust)}</td>
      </tr>
    );
  };

  let customersAllRowsUI = [];
  if (customersState && customersState.length > 0) {
    customersAllRowsUI = customersState.map((cust) => {
      return createSingleRowUIFromCustomer(cust);
    });
  }

  return (
    <div className="content-center  row">
      <div className="col-md-8 mx-auto mt-50 mb-50 py-5 px-5">
          <div className="jumbotron">
            <h1 className="display-4">Customers!</h1>
            
            
          </div>
        </div><div className="col-md-10 mx-auto mt-50 mb-50 py-5 px-5">
        <table className="table table-bordered">
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
