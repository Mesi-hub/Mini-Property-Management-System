import { Route, Routes, Navigate } from "react-router";

import loadable from "@loadable/component";

const Dashboard = loadable(() => import("../pages/Dashboard/Dashboard"));
const Login = loadable(() => import("../pages/Auth/Login"));
const SignUp = loadable(() => import("../pages/Auth/SignUp"));
const Admin = loadable(() => import("../pages/Admin/Admin"));
const HouseDetail = loadable(() =>
    import("../components/HouseDetail/HouseDetail")
);

export default function PageRoutes(props) {
    return (
        <Routes>
            <Route path="/" element={<Dashboard />} />
            <Route path="house-detail/:id" element={<HouseDetail />} />
            <Route path="login" element={<Login />} />
            <Route path="signup" element={<SignUp />} />
            <Route path="admin" element={<Admin />} />
            {/*<Route path="students/:id" element={<StudentDetails />} />
            <Route path="add-student" element={<NewProduct />} />
            <Route path="selected-students" element={<Following />} /> */}
        </Routes>
    );
}
