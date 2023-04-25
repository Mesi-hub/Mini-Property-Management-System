import { useSelector } from "react-redux";
import { injectStoreState } from "../../services/api";
import "./App.css";
import House from "../House/House";
import Header from "../Header/Header";
import Banner from "../Banner/Banner";
import Houses from "../Houses/Houses";

function App() {
  const store = useSelector((state) => state);
  injectStoreState(store);
  return (
    <div>
      <Header />
      <Banner />
      <Houses />
    </div>
  );
}

export default App;
