import { createSlice } from "@reduxjs/toolkit";

export const authenticationSlice = createSlice({
  name: "authentication",
  initialState: {
    value: {},
  },
  reducers: {
    authenticationSuccess: (state, action) => {
      state.value = action.payload;
    },
    refreshSuccess: (state, action) => {
      state.value = action.payload;
    },
    logoff: (state, action) => {
      state.value = action.payload;
    },
  },
});

export const { authenticationSuccess, refreshSuccess, logoff } =
  authenticationSlice.actions;

export const isAuthStatusValid = (store) => {
  if (
    store?.authentication?.value?.token &&
    store?.authentication?.value?.expiresAt
  ) {
    if (Date.parse(store.authentication.value.expiresAt) > Date.now()) {
      return true;
    }
  }
  return false;
};

export const isLoggedIn = (store) => {
  if (
    store?.authentication?.value?.token &&
    store?.authentication?.value?.expiresAt
  ) {
      return true;    
  }
  return false;
};

export default authenticationSlice.reducer;
