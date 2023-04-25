import { apiUnsecured, getStoreState } from "..";
import { setErrorToastMessage } from "../../../feature/Toasts/errorToastSlice";

const authenticateUser = async (username, password) => {
  let result = await apiUnsecured.post("/authenticate", {
    username: username,
    password: password,
  }).catch(error=> {
    setErrorToastMessage(getStoreState(), error.message);
    return error;
  });
  return result.data;
};

export { authenticateUser };
