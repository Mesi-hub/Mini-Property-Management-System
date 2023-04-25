import React from "react";
import { Link } from "react-router-dom";
import { AuthenticationWidget } from "../AuthenticationWidget";
import { hasRole } from "../../services/api";

const Header = () => {
    return (
        <header>
            <nav className="navbar navbar-expand-md navbar-dark  bg-dark">
                <div className="container-fluid">
                    <Link to="/" className="navbar-brand">
                        Property Management
                    </Link>
                    <button
                        className="navbar-toggler"
                        type="button"
                        data-bs-toggle="collapse"
                        data-bs-target="#navbarCollapse"
                        aria-controls="navbarCollapse"
                        aria-expanded="false"
                        aria-label="Toggle navigation"
                    >
                        <span className="navbar-toggler-icon"></span>
                    </button>
                    <div
                        className="collapse navbar-collapse"
                        id="navbarCollapse"
                    >
                        <ul className="navbar-nav me-auto mb-2 mb-md-0">
                            <li className="nav-item">
                                <Link to="/" className="nav-link">
                                    Home
                                </Link>
                            </li>
                            { hasRole("ADMIN") ?
                            <li className="nav-item">
                                <Link to="/admin" className="nav-link">
                                    Admin
                                </Link>
                            </li>
                            : ""}
                        </ul>
                        <form className="d-flex">
                            <input
                                className="form-control me-2"
                                type="search"
                                placeholder="Search"
                                aria-label="Search"
                            />
                            <button
                                className="btn btn-outline-success"
                                type="submit"
                            >
                                Search
                            </button>
                        </form>
                    </div>
                    <AuthenticationWidget />
                </div>
            </nav>
        </header>
    );
};

export default Header;
