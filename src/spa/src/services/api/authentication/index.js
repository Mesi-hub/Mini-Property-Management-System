import { api } from "..";

const authenticateUser = async (username, password) => {
  let result = await api.post("/authenticate", {
    username: username,
    password: password,
  });
  return result.data;
};

export { authenticateUser };
