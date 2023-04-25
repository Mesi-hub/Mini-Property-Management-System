import React from "react";

const Admin = () => {
    return (
        <div className="content-center  row">
            <div className="col-md-10 mx-auto mt-50 mb-50 py-5 px-5">
                <div>
                    <h3>Owner List </h3>
                </div>
                <table class="table table-bordered">
                    <thead>
                        <tr>
                            <th scope="col">#</th>
                            <th scope="col">First</th>
                            <th scope="col">Last</th>
                            <th scope="col">Handle</th>
                            <th scope="col">Status</th>
                            <th scope="col">Action</th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr>
                            <th scope="row">1</th>
                            <td>Mark</td>
                            <td>Otto</td>
                            <td>@mdo</td>
                            <td>Ready for post</td>
                            <td>
                                <button
                                    type="button"
                                    class="btn btn-primary btn-sm"
                                >
                                    Approve
                                </button>{" "}
                                &nbsp;
                                <button
                                    type="button"
                                    class="btn btn-primary btn-sm"
                                >
                                    Disable
                                </button>
                            </td>
                        </tr>
                        <tr>
                            <th scope="row">2</th>
                            <td>Jacob</td>
                            <td>Thornton</td>

                            <td>@fat</td>
                            <td>Ready for post</td>
                            <td>
                                <button
                                    type="button"
                                    class="btn btn-primary btn-sm"
                                >
                                    Approve
                                </button>
                                &nbsp;
                                <button
                                    type="button"
                                    class="btn btn-primary btn-sm"
                                >
                                    Disable
                                </button>
                            </td>
                        </tr>
                        <tr>
                            <th scope="row">3</th>
                            <td>Larry the Bird</td>
                            <td>Thornton</td>

                            <td>@twitter</td>
                            <td>Disabled</td>
                            <td>
                                <button
                                    type="button"
                                    class="btn btn-primary btn-sm"
                                >
                                    Approve
                                </button>
                                &nbsp;
                                <button
                                    type="button"
                                    class="btn btn-primary btn-sm"
                                >
                                    Disable
                                </button>
                            </td>
                        </tr>
                    </tbody>
                </table>
            </div>
        </div>
    );
};

export default Admin;
