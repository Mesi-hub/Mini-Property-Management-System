import { useRef } from "react"
import { authenticateUser } from "../../services/api/authentication";
import { authenticationSuccess } from "../../feature/Authentication/authenticationSlice";
import { useDispatch, useSelector } from "react-redux";

export const AuthenticationWidget = () => {
    const authenticationForm = useRef();
    const dispatch = useDispatch();
    const { value } = useSelector(state => state.authentication);
    const authenticate = async(event) => {
        event.preventDefault();
        console.log("Before Store Token:" + value.token);
        let authResult = await authenticateUser(authenticationForm.current['username'].value, authenticationForm.current['password'].value);
        console.log("Returned Token from API" + authResult.token);
        dispatch(authenticationSuccess(authResult));
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