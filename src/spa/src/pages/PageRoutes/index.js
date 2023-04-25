import { Route, Routes } from "react-router-dom"
import { HomePage } from "../HomePage"
import { HousesPage } from "../HousesPage"
import { HousePage } from "../HousePage"

export const PageRoutes = (props) => {
    return (
        <Routes>
            <Route path="/" element={<HomePage />}/>
            <Route path="/houses" element={<HousesPage />}/>
            <Route path="/houses/:id" element={<HousePage />}/>
        </Routes>
    )
}