import { apiSecured, getErrorMessagesContext } from "..";
import { addErrorMessage } from "../../../context/errorMessagesContext";

export const registerCurrentAsNewOwner = async () => {
    let result = await apiSecured().post("/owners/register").catch(error=> {
      addErrorMessage(getErrorMessagesContext(), error.message);
      return error;
    });
    return result.data;
  };
