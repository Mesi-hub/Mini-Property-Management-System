import { apiSecured, getErrorMessagesContext } from "..";
import { addErrorMessage } from "../../../context/errorMessagesContext";

const getUserInfo = async (username, password) => {
  let result = await apiSecured().post("/authenticate/userinfo").catch(error=> {
    addErrorMessage(getErrorMessagesContext(), error.message);
    return error;
  });
  return result.data;
};

export { getUserInfo };
