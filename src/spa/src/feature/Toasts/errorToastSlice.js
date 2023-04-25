import { createSlice } from "@reduxjs/toolkit";

export const errorToastSlice = createSlice({
  name: "errorToast",
  initialState: {
    value: {},
  },
  reducers: {
    errorStoreInit: (state, action) => {
      state.value = action.payload;
    },
  },
});

export const setErrorToastMessage = (store, errorMessage) => {
  if (store?.errorToast?.value?.setErrorMessage) {
    store.errorToast.value.setErrorMessage(errorMessage);
  }
};

export const { errorStoreInit } = errorToastSlice.actions;

export default errorToastSlice.reducer;
