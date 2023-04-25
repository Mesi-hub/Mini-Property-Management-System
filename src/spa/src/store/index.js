import { configureStore } from '@reduxjs/toolkit'
import authenticationReducer from '../feature/Authentication/authenticationSlice';
import loggedinUserReducer from '../feature/Authentication/loggedinUserSlice';
import errorStoreReducer from '../feature/Toasts/errorToastSlice';

export const setupStore = (preloadedState)  =>  { return configureStore({
  reducer:{
    authentication: authenticationReducer,
    loggedinUser: loggedinUserReducer,
    errorToast: errorStoreReducer
  },
  preloadedState,
})}

const store = setupStore({});

export default store