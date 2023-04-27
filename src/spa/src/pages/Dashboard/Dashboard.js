import React from "react";
import Banner from "../../components/Banner";
import Houses from "../../components/Houses";
import { useParams } from "react-router";

const Dashboard = () => {
    const params= useParams();  
    return (
        <div>
            <Banner />
            <Houses citySearch={params?.citySearch}/>
        </div>
    );
};

export default Dashboard;
