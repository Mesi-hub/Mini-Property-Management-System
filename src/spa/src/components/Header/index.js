import React from "react";
import { Link } from "react-router-dom";
import { AuthenticationWidget } from "../AuthenticationWidget";
import { hasRole } from "../../services/api";
import { isLoggedIn } from "../../feature/Authentication/authenticationSlice";
import { useSelector } from "react-redux";

const Header = () => {
  const store = useSelector((state) => state);
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
          <div className="collapse navbar-collapse" id="navbarCollapse">
            <ul className="navbar-nav me-auto mb-2 mb-md-0">
              <li className="nav-item">
                <Link to="/" className="nav-link">
                  Home
                </Link>
              </li>
              {!isLoggedIn(store) ? (
                <>
                  <li className="nav-item">
                    <Link to="/login" className="nav-link">
                      Login
                    </Link>
                  </li>
                  <li className="nav-item">
                    <Link to="/signup" className="nav-link">
                      SignUp
                    </Link>
                  </li>
                </>
              ) : (
                ""
              )}
              {
                //TODO  loggedin
                true ? (
                  <>
                    <li className="nav-item">
                      <Link to="/messages" className="nav-link">
                        Messages
                      </Link>
                    </li>
                    <li className="nav-item">
                      <Link to="/saved-properties" className="nav-link">
                        Saved Properties
                      </Link>
                    </li>
                  </>
                ) : (
                  ""
                )
              }
              {
                //TODO !hasRole(OWNER) and loggedin
                true ? (
                  <>
                    <li className="nav-item">
                      <Link to="/become-a-seller" className="nav-link">
                        Become a Seller
                      </Link>
                    </li>
                  </>
                ) : (
                  ""
                )
              }

              {hasRole("ADMIN") ? (
                <>
                  <li className="nav-item">
                    <Link to="/admin" className="nav-link">
                      Admin
                    </Link>
                  </li>
                  <li className="nav-item">
                    <Link to="/customers" className="nav-link">
                      Customers
                    </Link>
                  </li>
                </>
              ) : (
                ""
              )}
              <li className="nav-item">
                <Link to="/customer-offers-history" className="nav-link">
                  My Offers
                </Link>
              </li>
            </ul>
            <form className="d-flex">
              <input
                className="form-control me-2"
                type="search"
                placeholder="Search"
                aria-label="Search"
              />
              <button className="btn btn-outline-success" type="submit">
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
