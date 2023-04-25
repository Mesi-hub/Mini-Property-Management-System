import React from "react";

const SignUp = () => {
    return (
        <div>
            <div className="content-center  row">
                <div className="col-md-4 mx-auto mt-50 mb-50 py-5 px-5">
                    <div>
                        <h3>Sign up</h3>
                    </div>
                    <form>
                        <div className="mb-3">
                            <label
                                for="exampleInputEmail1"
                                className="form-label"
                            >
                                Email address
                            </label>
                            <input
                                type="email"
                                className="form-control"
                                id="exampleInputEmail1"
                                aria-describedby="emailHelp"
                            />
                        </div>
                        <div className="mb-3">
                            <label
                                for="exampleInputPassword1"
                                className="form-label"
                            >
                                Password
                            </label>
                            <input
                                type="password"
                                className="form-control"
                                id="exampleInputPassword1"
                            />
                        </div>
                        <button type="submit" className="btn btn-primary">
                            Submit
                        </button>
                    </form>
                </div>
            </div>
        </div>
    );
};

export default SignUp;
