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
      <header className="App-header">
        <img src={logo} className="App-logo" alt="logo" />
        <p>
          Edit <code>src/App.js</code> and save to reload.
        </p>
        <a
          className="App-link"
          href="https://reactjs.org"
          target="_blank"
          rel="noopener noreferrer"
        >
          Learn React
        </a>
      </header>
    </div>
  );
}

export default App;
