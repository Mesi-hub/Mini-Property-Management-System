import { apiSecured } from "..";

const getUserInfo = async (username, password) => {
  let result = await apiSecured().post("/authenticate/userinfo");
  return result.data;
};

export { getUserInfo };
