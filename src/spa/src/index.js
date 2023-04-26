import React from "react";
import ReactDOM from "react-dom/client";
import { BrowserRouter } from "react-router-dom";
import "./index.css";
import reportWebVitals from "./reportWebVitals";
import store from "./store";
import { Provider } from "react-redux";
import "bootstrap/dist/css/bootstrap.css";
import PageRoutes from "./Routes/PageRoutes";
import { ErrorToast } from "./components/ErrorToast";
import { ErrorMessagesContext } from "./context/errorMessagesContext";

const root = ReactDOM.createRoot(document.getElementById("root"));
root.render(
  <React.StrictMode>
    <Provider store={store}>
      <ErrorMessagesContext.Provider
        value={{ errorMessages: [], setErrorMessages: () => {} }}
      >
        <BrowserRouter>
          <PageRoutes />
        </BrowserRouter>
        <ErrorToast />
      </ErrorMessagesContext.Provider>
    </Provider>
  </React.StrictMode>
);

// If you want to start measuring performance in your app, pass a function
// to log results (for example: reportWebVitals(console.log))
// or send to an analytics endpoint. Learn more: https://bit.ly/CRA-vitals
reportWebVitals();
