import React from "react";
import { NavLink } from "react-router-dom";

const LeftNav: React.FC = () => {
  return (
    <div>
      <h2>
        <NavLink
          to="/home"
          className={({ isActive }) => "nav-link" + (isActive ? " active" : "")}
        >
          Navigation
        </NavLink>
      </h2>
      <ul className="nav flex-column">
        <li className="nav-item">
          <NavLink
            to="/add-product"
            className={({ isActive }) =>
              "nav-link" + (isActive ? " active" : "")
            }
          >
            Add new Product
          </NavLink>
        </li>
        <li className="nav-item">
          <NavLink
            to="/products"
            className={({ isActive }) =>
              "nav-link" + (isActive ? " active" : "")
            }
          >
            Products
          </NavLink>
        </li>
        <li className="nav-item">
          <NavLink
            to="/orders"
            className={({ isActive }) =>
              "nav-link" + (isActive ? " active" : "")
            }
          >
            Orders
          </NavLink>
        </li>
        <li className="nav-item">
          <NavLink
            to="/customers"
            className={({ isActive }) =>
              "nav-link" + (isActive ? " active" : "")
            }
          >
            Customers
          </NavLink>
        </li>
      </ul>
    </div>
  );
};

export default LeftNav;
