import axiosUnsecuredInstance from "axios";
import axiosSecuredInstance from "axios";

let storeState;

export const injectStoreState = (_storeState) => {
  storeState = _storeState;
};

export const apiUnsecured = axiosUnsecuredInstance.create({
  baseURL: "http://localhost:8080",
  headers: {
    "Content-Type": "application/json",
  },
});

// Request interceptor for API calls
axiosSecuredInstance.interceptors.request.use(
  async (config) => {
    const token = storeState?.authentication?.value?.token;
    config.headers = {
      Authorization: `Bearer ${token}`,
      Accept: "application/json",
      "Content-Type": "application/json",
    };
    return config;
  },
  (error) => {
    Promise.reject(error);
  }
);

export const apiSecured = () => {
  const token = storeState?.authentication?.value?.token;
  let headers = {
    Authorization: `Bearer ${token}`,
    Accept: "application/json",
    "Content-Type": "application/json",
  };

  let instance = axiosSecuredInstance.create({
    baseURL: "http://localhost:8080",
    headers: headers,
  });
  return instance;
};
