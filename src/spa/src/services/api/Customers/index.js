import { apiSecured, apiUnsecured } from "..";

const fetchAllCustomers = async () => {
  console.log("fetchAllCustomers - api");
  let result = await apiSecured().get("/customers");
  console.log("fetchAllCustomers - result in api:", result);
  return result.data;
};

const addToBlacklist = async (customer) => {
  console.log("addToBlacklist - api");
  let result = await apiSecured().post("/customers/black-list/" + customer.id);
  console.log("addToBlacklist - result in api:", result);
  return result.data;
};

const addToWhitelist = async (customer) => {
  console.log("addToWhitelist - api");
  let result = await apiSecured().post("/customers/white-list/" + customer.id);
  console.log("addToWhitelist - result in api:", result);
  return result.data;
};

const registerNewCustomer = async (newCustomer) => {
  console.log("registerNewCustomer - api newCustomer data: ", newCustomer);
  let result = await apiSecured().post("/customers", newCustomer);
  console.log("registerNewCustomer - result in api:", result);
  return result.data;
};

export {
  fetchAllCustomers,
  addToBlacklist,
  addToWhitelist,
  registerNewCustomer,
};
