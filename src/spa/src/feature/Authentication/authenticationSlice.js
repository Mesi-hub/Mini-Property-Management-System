import { createSlice } from '@reduxjs/toolkit'

export const authenticationSlice = createSlice({
  name: 'authentication',
  initialState: {
    value: {}
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
    }
  }
})

export const { authenticationSuccess, refreshSuccess, logoff} = authenticationSlice.actions

export default authenticationSlice.reducer