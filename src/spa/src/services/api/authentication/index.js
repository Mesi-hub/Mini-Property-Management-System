import {
  apiSecured,
  apiUnsecured,
  getErrorMessagesContext,
  getStoreState,
} from "..";
import { addErrorMessage } from "../../../context/errorMessagesContext";

const authenticateUser = async (username, password) => {
  let result = await apiUnsecured
    .post("/authenticate", {
      username: username,
      password: password,
    })
    .catch((error) => {
      addErrorMessage(getErrorMessagesContext(), error.message);
      return error;
    });
  return result.data;
};

const logoutUser = async () => {
  let result = await apiSecured()
    .get("/authenticate/logout")
    .catch((error) => {
      addErrorMessage(getErrorMessagesContext(), error.message);
      return error;
    });
  return result.data;
};

export { authenticateUser, logoutUser };
