import { useSelector } from "react-redux";
import { injectStoreState } from "../../services/api";
import logo from "../../static/images/logo.svg";
import { AuthenticationWidget } from "../AuthenticationWidget";
import "./App.css";

function App() {
  const store = useSelector(state => state)
  injectStoreState(store);
  return (
    <div className="App">
      <AuthenticationWidget />
      <p>Student Project</p>
    </div>
  );
}

export default App;
