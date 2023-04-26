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
    logoff: (state, action) => {
      state.value = action.payload;
    },
  },
});

export const { userinfoSuccess, logoff } = loggedinUserSlice.actions;

export const loggedinUserHasRole = (store, roleName) => {
  //TODO uncomment below line
  return true;
  if (
    store?.loggedinUser?.value?.roles
  ) {
    return (store.loggedinUser.value.roles.filter(role => role.role === roleName).length > 0)
  }
  return false;
};


export default loggedinUserSlice.reducer;
