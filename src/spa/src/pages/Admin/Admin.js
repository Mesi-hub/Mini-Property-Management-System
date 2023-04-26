import React from "react";
import { useEffect } from "react";
import { useState } from "react";
import { useParams } from "react-router";
import {
    approveOwner,
    blackListOwner,
    fetchAllOwner,
} from "../../services/api/Admin";

const Admin = () => {
    const params = useParams();

    const [owners, setOwners] = useState([]);
    const [reload, setReload] = useState(true);
    useEffect(() => {
        fetchAllOwner(params.id).then((data) => setOwners(data));
    }, [reload, params.id]);

    const approveButtonClicked = (id, status) => {
        approveOwner(id, status).then((data) => {
            // console.log(data);
            setReload(!reload);
        });
    };

    const disableButtonClicked = (id, status) => {
        blackListOwner(id, status).then((data) => {
            // console.log(data);
            setReload(!reload);
        });
    };

    return (
        <div className="content-center  row">
            <div className="col-md-10 mx-auto mt-50 mb-50 py-5 px-5">
                <div>
                    <h3>Owner List </h3>
                </div>
                <table className="table table-bordered">
                    <thead>
                        <tr>
                            <th scope="col">First name</th>
                            <th scope="col">Last name</th>
                            <th scope="col">Email</th>
                            <th scope="col">Status</th>
                            <th scope="col">Action</th>
                        </tr>
                    </thead>
                    <tbody>
                        {owners.map((owner) => {
                            return (
                                <tr key={owner.id}>
                                    <td>{owner?.firstName}</td>
                                    <td>{owner?.lastName}</td>
                                    <td>{owner?.email}</td>
                                    <td>
                                        {owner.approved ? (
                                            <span className="badge bg-success">
                                                Approved
                                            </span>
                                        ) : null}
                                        &nbsp;
                                        {owner.blackListed ? (
                                            <span className="badge bg-danger">
                                                Disabled
                                            </span>
                                        ) : null}
                                    </td>
                                    <td>
                                        {!owner.approved ? (
                                            <button
                                                onClick={() => {
                                                    approveButtonClicked(
                                                        owner.id,
                                                        true
                                                    );
                                                }}
                                                type="button"
                                                className="btn btn-primary btn-sm"
                                            >
                                                Approve
                                            </button>
                                        ) : null}
                                        &nbsp;
                                        {owner.blackListed ? (
                                            <button
                                                onClick={() => {
                                                    disableButtonClicked(
                                                        owner.id,
                                                        false
                                                    );
                                                }}
                                                type="button"
                                                className="btn btn-primary btn-sm"
                                            >
                                                Enable
                                            </button>
                                        ) : (
                                            <button
                                                onClick={() => {
                                                    disableButtonClicked(
                                                        owner.id,
                                                        true
                                                    );
                                                }}
                                                type="button"
                                                className="btn btn-primary btn-sm"
                                            >
                                                Disable
                                            </button>
                                        )}
                                    </td>
                                </tr>
                            );
                        })}
                    </tbody>
                </table>
            </div>
        </div>
    );
};

export default Admin;
