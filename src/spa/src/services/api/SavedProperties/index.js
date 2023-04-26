import { apiSecured, getErrorMessagesContext } from "..";
import { addErrorMessage } from "../../../context/errorMessagesContext";

export const getSavedProperties = async () => {
    let result = await apiSecured().get("/savedProperties").catch(error=> {
      addErrorMessage(getErrorMessagesContext(), error.message);
      return error;
    });
    return result.data;
  };