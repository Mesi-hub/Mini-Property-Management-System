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
    .post("/customers/" + customerId + "/offer", offer)
    .catch((error) => {
      addErrorMessage(getErrorMessagesContext(), error.message);
      return error;
    });
  console.log("fetchAllOffersByCustomer - result in api:", result);
  return result.data;
};

export const cancelOffer = async (offerId, customerId) => {
  let result = await apiSecured()
    .post(`/customers/${customerId}/offer/${offerId}/cancel`)
    .catch((error) => {
      addErrorMessage(getErrorMessagesContext(), error.message);
      return error;
    });
  return result.data;
};