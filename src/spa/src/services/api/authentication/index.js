import { apiUnsecured } from "..";

const authenticateUser = async (username, password) => {
  let result = await apiUnsecured.post("/authenticate", {
    username: username,
    password: password,
  });
  return result.data;
};

export { authenticateUser };
