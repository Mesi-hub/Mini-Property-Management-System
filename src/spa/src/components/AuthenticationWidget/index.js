import { isAuthStatusValid } from "../../feature/Authentication/authenticationSlice";
import { useSelector } from "react-redux";

export const AuthenticationWidget = () => {
  const store = useSelector((state) => state);
  return (
    <>
      {isAuthStatusValid(store) ? (
        <>
          <span className="badge text-bg-primary">
            {store?.loggedinUser?.value?.name}
          </span>
          <button type="button" className="btn btn-link">
            Logout
          </button>
        </>
      ) : (
        ""
      )}
    </>
  );
};
