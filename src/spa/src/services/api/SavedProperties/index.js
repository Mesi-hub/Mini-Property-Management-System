import { apiSecured, getErrorMessagesContext } from "..";
import { addErrorMessage } from "../../../context/errorMessagesContext";

export const getSavedProperties = async () => {
    let result = await apiSecured().get("/savedProperties").catch(error=> {
      addErrorMessage(getErrorMessagesContext(), error.message);
      return error;
    });
    return result.data;
  };
  export const savePropertyToSavedList = async (data) => {
    let result = await apiSecured().post("/savedProperties", data).catch(error=> {
      addErrorMessage(getErrorMessagesContext(), error.message);
      return error;
    });
    return result.data;
  };
  export const removePropertyfromSavedList = async (id) => {
    let result = await apiSecured().delete(`/savedProperties/${id}`).catch(error=> {
      addErrorMessage(getErrorMessagesContext(), error.message);
      return error;
    });
    return result.data;
  };