import { apiSecured, apiUnsecured } from "..";

const fetchAllCustomers = async () => {
  console.log("fetchAllCustomers - api");
  let result = await apiSecured().get("/customers");
  console.log("fetchAllCustomers - result in api:", result);
  return result.data;
};

export { fetchAllCustomers };
