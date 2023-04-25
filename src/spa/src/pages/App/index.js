import { useSelector } from "react-redux";
import { injectStoreState } from "../../services/api";
import "./App.css";
import Header from "../../components/Header";
import { BrowserRouter } from "react-router-dom";
import { PageRoutes } from "../PageRoutes";
function App() {
  const store = useSelector((state) => state);
  injectStoreState(store);
  return (
    <BrowserRouter>
      <div>
        <Header />
        <PageRoutes />
      </div>
    </BrowserRouter>
  );
}

export default App;
