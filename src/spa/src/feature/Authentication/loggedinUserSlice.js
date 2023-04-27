import { createSlice } from "@reduxjs/toolkit";

export const loggedinUserSlice = createSlice({
  name: "loggedinUser",
  initialState: {
    value: {},
  },
  reducers: {
    userinfoSuccess: (state, action) => {
      state.value = action.payload;
    },
    logoffUserSuccess: (state, action) => {
      state.value = action.payload;
    },
  },
});

export const { userinfoSuccess, logoffUserSuccess } = loggedinUserSlice.actions;

export const loggedinUserHasRole = (store, roleName) => {
  if (
    store?.loggedinUser?.value?.roles
  ) {
    return (store.loggedinUser.value.roles.filter(role => role.role === roleName).length > 0)
  }
  return false;
};


export default loggedinUserSlice.reducer;
