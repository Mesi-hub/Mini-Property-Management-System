import { apiSecured, getErrorMessagesContext } from "..";
import { addErrorMessage } from "../../../context/errorMessagesContext";

export const registerNewOwner = async (newOwner) => {
  let result = await apiSecured().post("/owners/register", newOwner).catch(error=> {
    addErrorMessage(getErrorMessagesContext(), error.message);
    return error;
  });
  return result.data;
};


