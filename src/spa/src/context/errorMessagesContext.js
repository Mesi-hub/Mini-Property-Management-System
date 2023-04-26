import React from "react";

export const ErrorMessagesContext = React.createContext({errorMessages: [], setErrorMessages: () => {}});
export const addErrorMessage = (ErrorMessageContext, errorMessage) => {
    var errorMessagesCopy = [...(ErrorMessageContext.errorMessages)];
    errorMessagesCopy = errorMessagesCopy.filter(x=> x!==errorMessage);
    errorMessagesCopy.push(errorMessage);
    ErrorMessageContext.setErrorMessages(errorMessagesCopy);
}
export const initContextWithNewParams = (ErrorMessageContext, errorMessages, setErrorMessages) => {
    ErrorMessageContext.errorMessages = errorMessages;
    ErrorMessageContext.setErrorMessages = setErrorMessages;
}
export const removeErrorMessage = (ErrorMessageContext, errorMessage) =>
{
    var errorMessagesCopy = [...(ErrorMessageContext.errorMessages)]
    errorMessagesCopy = errorMessagesCopy.filter(x=> x!==errorMessage);
    ErrorMessageContext.setErrorMessages(errorMessagesCopy);
}