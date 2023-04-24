import { configureStore } from '@reduxjs/toolkit'
import authenticationReducer from '../feature/Authentication/authenticationSlice';
import loggedinUserReducer from '../feature/Authentication/loggedinUserSlice';

export const setupStore = (preloadedState)  =>  { return configureStore({
  reducer:{
    authentication: authenticationReducer,
    loggedinUser: loggedinUserReducer
  },
  preloadedState,
})}

const store = setupStore({});

export default store