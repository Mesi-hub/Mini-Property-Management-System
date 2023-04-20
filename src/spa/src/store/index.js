import { configureStore } from '@reduxjs/toolkit'
import authenticationReducer from '../feature/Authentication/authenticationSlice';
import loggedinUserReducer from '../feature/Authentication/loggedinUserSlice';

const store = configureStore({
  reducer:{
    authentication: authenticationReducer,
    loggedinUser: loggedinUserReducer,
  },
})

export default store