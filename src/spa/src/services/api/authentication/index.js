import { apiUnsecured, getErrorMessagesContext, getStoreState } from "..";
import { addErrorMessage } from "../../../context/errorMessagesContext";

const authenticateUser = async (username, password) => {
  let result = await apiUnsecured.post("/authenticate", {
    username: username,
    password: password,
  }).catch(error=> {
    addErrorMessage(getErrorMessagesContext(), error.message);
    return error;
  });
  return result.data;
};

export { authenticateUser };
