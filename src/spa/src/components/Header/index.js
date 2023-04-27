import React, { useRef } from "react";
import { Link, useNavigate } from "react-router-dom";
import { AuthenticationWidget } from "../AuthenticationWidget";
import { hasRole, ownerApprovalPending } from "../../services/api";
import { isLoggedIn } from "../../feature/Authentication/authenticationSlice";
import { useSelector } from "react-redux";

const Header = () => {
  const store = useSelector((state) => state);
  const searchRef = useRef();
  const navigate = useNavigate();
  const filterHandler = (evt) => {
    evt.preventDefault();

    navigate(`/properties/${searchRef.current.value}`);
  };
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
              {isLoggedIn(store) ? (
                <li className="nav-item">
                  <Link to="/messages" className="nav-link">
                    Messages
                  </Link>
                </li>
              ) : (
                ""
              )}
              {hasRole("CUSTOMER") ? (
                <>
                  <li className="nav-item">
                    <Link to="/saved-properties" className="nav-link">
                      Saved Properties
                    </Link>
                  </li>
                  <li className="nav-item">
                    <Link to="/customer-offers-history" className="nav-link">
                      Sent Offers
                    </Link>
                  </li>
                </>
              ) : (
                ""
              )}
              {!isLoggedIn(store) && !ownerApprovalPending(store) ? (
                <li className="nav-item">
                  <Link to="/become-a-seller" className="nav-link">
                    Become a Seller
                  </Link>
                </li>
              ) : (
                ""
              )}
              {hasRole("OWNER") ? (
                <>
                  <li className="nav-item">
                    <Link to="/add-house" className="nav-link">
                      Add House
                    </Link>
                  </li>
                  <li className="nav-item">
                    <Link to="/owner-offers-history" className="nav-link">
                      Received Offers
                    </Link>
                  </li>
                </>
              ) : (
                ""
              )}

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
            </ul>
            <form className="d-flex">
              <input
                ref={searchRef}
                className="form-control me-2"
                type="search"
                placeholder="Search in City"
                aria-label="Search"
              />
              <button
                className="btn btn-outline-success"
                type="submit"
                onClick={(evt) => filterHandler(evt)}
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
