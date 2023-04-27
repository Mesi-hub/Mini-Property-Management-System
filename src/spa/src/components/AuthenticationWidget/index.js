import { useNavigate } from "react-router";
import { isLoggedIn, logoff } from "../../feature/Authentication/authenticationSlice";
import { useDispatch, useSelector } from "react-redux";
import { logoutUser } from "../../services/api/authentication";
import { logoffUserSuccess } from "../../feature/Authentication/loggedinUserSlice";

export const AuthenticationWidget = () => {
  const store = useSelector((state) => state);
  const navigate = useNavigate();
  const dispatch = useDispatch();
  const logout = async () => {
    await logoutUser();
    await dispatch(logoff({}));
    await dispatch(logoffUserSuccess({}));
    navigate("/");

  }
  return (
    <>
      {isLoggedIn(store) ? (
        <>
          <span className="badge text-bg-primary">
            {store?.loggedinUser?.value?.name}
          </span>
          <button type="button" className="btn btn-link" onClick={logout}>
            Logout
          </button>
        </>
      ) : (
        ""
      )}
    </>
  );
};
