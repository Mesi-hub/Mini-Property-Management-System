import { useRef } from "react"
import { authenticateUser } from "../../services/api/authentication";
import { authenticationSuccess } from "../../feature/Authentication/authenticationSlice";
import { useDispatch, useSelector } from "react-redux";
import { getUserInfo } from "../../services/api/loggedinUser";
import { userinfoSuccess } from "../../feature/Authentication/loggedinUserSlice";

export const AuthenticationWidget = () => {
    const authenticationForm = useRef();
    const dispatch = useDispatch();
    const { authenticationStoreValue } = useSelector(state => state.authentication);
    //const { loggedinUserStoreValue } = useSelector(state => state.loggedinUser);
    const authenticate = async(event) => {
        event.preventDefault();
        console.log("Before Store Token:" + authenticationStoreValue?.token);
        let authResult = await authenticateUser(authenticationForm.current['username'].value, authenticationForm.current['password'].value);
        console.log("Returned Token from API" + authResult.token);
        await dispatch(authenticationSuccess(authResult));
        let userInfo = await getUserInfo();
        console.log("Returned UserInfo from API" + userInfo.name);
        dispatch(userinfoSuccess(userInfo));
    }
    return (
        <div>
            <form ref={authenticationForm}  onSubmit={authenticate}>
            <input name="username"></input><br/>
            <input name="password"></input><br/>
            <button>Login</button>
            </form>
        </div>
    )
}