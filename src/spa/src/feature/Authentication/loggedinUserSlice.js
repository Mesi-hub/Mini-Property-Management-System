import { createSlice } from '@reduxjs/toolkit'

export const loggedinUserSlice = createSlice({
  name: 'loggedinUser',
  initialState: {
    value: {}
  },
  reducers: {
    userinfoSuccess: (state, action) => {
      state.value = action.payload;
    },
    logoff: (state, action) => {
      state.value = action.payload;
    }
  }
})

export const { userinfoSuccess, logoff} = loggedinUserSlice.actions

export default loggedinUserSlice.reducer