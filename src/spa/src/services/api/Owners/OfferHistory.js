import { apiSecured, getErrorMessagesContext } from "..";
import { addErrorMessage } from "../../../context/errorMessagesContext";

export const fetchAllOffersToOwner = async (ownerId) => {
  let result = await apiSecured()
    .get("/owners/" + ownerId + "/offers")
    .catch((error) => {
      addErrorMessage(getErrorMessagesContext(), error.message);
      return error;
    });
  return result.data;
};

export const updateOfferStatus = async (offer, ownerId) => {
  let result = await apiSecured()
    .post("/owners/" + ownerId + "/offers", offer)
    .catch((error) => {
      addErrorMessage(getErrorMessagesContext(), error.message);
      return error;
    });
  return result.data;
};

export const doOfferAction = async (offerId, ownerId, action) => {
  let result = await apiSecured()
    .post(`/owners/${ownerId}/offer/${offerId}/${action}`)
    .catch((error) => {
      addErrorMessage(getErrorMessagesContext(), error.message);
      return error;
    });
  return result.data;
};
