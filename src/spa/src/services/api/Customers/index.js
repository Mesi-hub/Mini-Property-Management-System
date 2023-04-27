import { apiSecured, getErrorMessagesContext } from "..";
import { addErrorMessage } from "../../../context/errorMessagesContext";

const fetchAllCustomers = async () => {
  console.log("fetchAllCustomers - api");
  let result = await apiSecured().get("/customers").catch(error=> {
    addErrorMessage(getErrorMessagesContext(), error.message);
    return error;
  });
  console.log("fetchAllCustomers - result in api:", result);
  return result.data;
};

const addToBlacklist = async (customer) => {
  console.log("addToBlacklist - api");
  let result = await apiSecured().post("/customers/black-list/" + customer.id).catch(error=> {
    addErrorMessage(getErrorMessagesContext(), error.message);
    return error;
  });
  console.log("addToBlacklist - result in api:", result);
  return result.data;
};

const addToWhitelist = async (customer) => {
  console.log("addToWhitelist - api");
  let result = await apiSecured().post("/customers/white-list/" + customer.id).catch(error=> {
    addErrorMessage(getErrorMessagesContext(), error.message);
    return error;
  });
  console.log("addToWhitelist - result in api:", result);
  return result.data;
};

const registerNewCustomer = async (newCustomer) => {
  console.log("registerNewCustomer - api newCustomer data: ", newCustomer);
  let result = await apiSecured().post("/customers/register", newCustomer).catch(error=> {
    addErrorMessage(getErrorMessagesContext(), error.message);
    return error;
  });
  console.log("registerNewCustomer - result in api:", result);
  return result.data;
};

export {
  fetchAllCustomers,
  addToBlacklist,
  addToWhitelist,
  registerNewCustomer,
};
