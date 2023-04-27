import { apiSecured, getErrorMessagesContext } from "..";
import { addErrorMessage } from "../../../context/errorMessagesContext";

const getProperties = async (filter) => {
  let result = await apiSecured().get("/properties", {params: filter}).catch((error) => {
    addErrorMessage(getErrorMessagesContext(), error.message);
    return error;
  });
  return result.data;
};

const getPropertyById = async (id) => {
  let result = await apiSecured().get("/properties/" + id).catch((error) => {
    addErrorMessage(getErrorMessagesContext(), error.message);
    return error;
  });
  return result.data;
};

const saveProperty = async (property) => {
  let result = await apiSecured()
    .post("/properties", property)
    .catch((error) => {
      addErrorMessage(getErrorMessagesContext(), error.message);
      return error;
    });
  return result.data;
};

const saveOffer = async (newOffer) => {
    let result = await apiSecured()
        .post("/customers/3/offer", newOffer)
        .catch((error) => {
            addErrorMessage(getErrorMessagesContext(), error.message);
            return error;
        });
    return result.data;
};



export { getProperties, getPropertyById, saveProperty , saveOffer};
