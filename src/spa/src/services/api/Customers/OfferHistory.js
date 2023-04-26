import { apiSecured, getErrorMessagesContext } from "..";
import { addErrorMessage } from "../../../context/errorMessagesContext";

export const fetchAllOffersByCustomer = async (customerId) => {
  let result = await apiSecured()
    .get("/customers/" + customerId + "/offers")
    .catch((error) => {
      addErrorMessage(getErrorMessagesContext(), error.message);
      return error;
    });
  console.log("fetchAllOffersByCustomer - result in api:", result);
  return result.data;
};

export const updateOfferStatus = async (offer, customerId) => {
  let result = await apiSecured()
    .post("/customers/" + customerId + "/offers", offer)
    .catch((error) => {
      addErrorMessage(getErrorMessagesContext(), error.message);
      return error;
    });
  console.log("fetchAllOffersByCustomer - result in api:", result);
  return result.data;
};
