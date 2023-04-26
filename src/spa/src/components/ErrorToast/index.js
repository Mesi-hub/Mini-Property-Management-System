import { useContext, useEffect, useState } from "react";
import "./ErrorToast.css";
import {
  ErrorMessagesContext,
  initContextWithNewParams,
} from "../../context/errorMessagesContext";
import { injectErrorMessagesContext } from "../../services/api";

export const ErrorToast = (props) => {
  const errorMessagesContext = useContext(ErrorMessagesContext);
  const [errorTexts, setErrorTexts] = useState([]);

  useEffect(() => {
    initContextWithNewParams(errorMessagesContext, errorTexts, setErrorTexts);
    injectErrorMessagesContext(errorMessagesContext);
  }, []);

  if (errorTexts && errorTexts.length > 0) {
    setTimeout(() => {
      setErrorTexts([]);
    }, 5000);
  }

  let errorHtml = () => {
    let html = "";
    let index = 1;
    if (errorTexts && errorTexts.length > 0) {
      html = (
        <div className="bottomright">
          {errorTexts.map((errorText) => (
            <p key={index++}>{errorText}</p>
          ))}
        </div>
      );
    } else {
      html = "";
    }
    return html;
  };
  return errorHtml();
};
