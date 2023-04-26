import { useEffect, useState } from "react";
import "./ErrorToast.css";
import { useDispatch } from "react-redux";
import { errorStoreInit } from "../../feature/Toasts/errorToastSlice";

export const ErrorToast = (props) => {
  const dispatch = useDispatch();
  const [errorText, setErrorText] = useState("");

  useEffect(() => {
    //dispatch(errorStoreInit({ setErrorMessage: setErrorText }));
  }, [props]);

  setTimeout(() => {
    setErrorText("");
  }, 5000);

  return errorText && errorText.length > 0 ? (
    <div className="bottomright">{errorText}</div>
  ) : (
    ""
  );
};
