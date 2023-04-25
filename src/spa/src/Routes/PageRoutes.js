import { Route, Routes } from "react-router";
import loadable from "@loadable/component";
import { injectStoreState } from "../services/api";
import { useSelector } from "react-redux";
import Header from "../components/Header";

const Dashboard = loadable(() => import("../pages/Dashboard/Dashboard"));
const Login = loadable(() => import("../pages/Auth/Login"));
const SignUp = loadable(() => import("../pages/Auth/SignUp"));
const Admin = loadable(() => import("../pages/Admin/Admin"));
const HouseDetail = loadable(() =>
  import("../components/HouseDetail/HouseDetail")
);

export default function PageRoutes(props) {
  const store = useSelector((state) => state);
  injectStoreState(store);

  const renderWithHeader = (element) => {
    return <><Header/> {element}</>
  }

  return (
    <Routes>
      <Route path="/" element={renderWithHeader(<Dashboard />)} />
      <Route path="house-detail/:id" element={renderWithHeader(<HouseDetail />)} />
      <Route path="login" element={renderWithHeader(<Login />)} />
      <Route path="signup" element={renderWithHeader(<SignUp />)} />
      <Route path="admin" element={renderWithHeader(<Admin />)} />
      {/*<Route path="students/:id" element={<StudentDetails />} />
            <Route path="add-student" element={<NewProduct />} />
            <Route path="selected-students" element={<Following />} /> */}
    </Routes>
  );
}
