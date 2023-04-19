import { configureStore } from '@reduxjs/toolkit'
import authenticationReducer from '../feature/Authentication/authenticationSlice';

const store = configureStore({
  reducer:{
    authentication: authenticationReducer,
  },
})

export default store