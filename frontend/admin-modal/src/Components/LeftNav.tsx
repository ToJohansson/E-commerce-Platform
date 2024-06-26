import React from "react";

const LeftNav = () => {
  return (
    <div>
      <h2>Navigation</h2>
      <ul className="nav flex-column">
        <li className="nav-item">Add new Product</li>
        <li className="nav-item">Products</li>
        <li className="nav-item">Inventarie</li>
        <li className="nav-item">Orders</li>
        <li className="nav-item">Customers</li>
      </ul>
    </div>
  );
};

export default LeftNav;
