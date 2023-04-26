import { useRef, useState } from "react";
import { authenticateUser } from "../../services/api/authentication";
import { authenticationSuccess } from "../../feature/Authentication/authenticationSlice";
import { useDispatch, useSelector } from "react-redux";
import { getUserInfo } from "../../services/api/loggedinUser";
import { userinfoSuccess } from "../../feature/Authentication/loggedinUserSlice";
import { useNavigate } from "react-router";

const Login = () => {
  const navigate = useNavigate();
  const authenticationForm = useRef();
  const [authenticationStoreValueChanged, setAuthenticationStoreValueChanged] =
    useState(false);
  const [loggedinUserStoreValueChanged, setLoggedinUserStoreValueChanged] =
    useState(false);
  const dispatch = useDispatch();
  const store = useSelector((state) => state);
  const authenticate = async (event) => {
    event.preventDefault();
    console.log("Before Store Token:" + store?.authentication?.value?.token);
    let authResult = await authenticateUser(
      authenticationForm.current["username"].value,
      authenticationForm.current["password"].value
    );
    console.log("Returned Token from API" + authResult.token);
    await dispatch(authenticationSuccess(authResult));
    let userInfo = await getUserInfo();
    console.log("Returned UserInfo from API" + userInfo.name);
    await dispatch(userinfoSuccess(userInfo));
    setAuthenticationStoreValueChanged(true);
    setLoggedinUserStoreValueChanged(true);
    navigate("/");
  };
  return (
    <div className="content-center  row">
      <div className="col-md-8 mx-auto mt-50 mb-50 py-5 px-5">
        <div>
          <h3>Login</h3>
        </div>
        <form ref={authenticationForm} onSubmit={authenticate}>
          <div className="mb-3">
            <label for="email" className="form-label">
              Email address
            </label>
            <input
              type="email"
              className="form-control"
              id="email"
              name="username"
            />
          </div>
          <div className="mb-3">
            <label for="password" className="form-label">
              Password
            </label>
            <input
              type="password"
              className="form-control"
              id="password"
              name="password"
            />
          </div>
          <button className="btn btn-primary">Submit</button>
        </form>
      </div>
    </div>
  );
};

export default Login;
