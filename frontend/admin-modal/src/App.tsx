import React from "react";
import {
  BrowserRouter as Router,
  Routes,
  Route,
  Navigate,
} from "react-router-dom";
import ProductForm from "./Forms/ProductForm";
import Header from "./Components/Header";
import LeftNav from "./Components/LeftNav";
import "./app.css";
import Products from "./Components/Products";
import Orders from "./Components/Orders";
import Customers from "./Components/Customers";
import HomePage from "./Components/HomePage";

const App = () => {
  return (
    <Router>
      <div className="container-fluid d-flex flex-column">
        <div className="row header">
          <div className="col">
            <Header />
          </div>
        </div>
        <div className="row flex-grow-1">
          <div className="col-md-2 leftNav">
            <LeftNav />
          </div>
          <div className="col-md-10 mainContent">
            <Routes>
              <Route path="/" element={<Navigate to="/home" />} />
              <Route path="/home" element={<HomePage />} />{" "}
              <Route path="/add-product" element={<ProductForm />} />
              <Route path="/products" element={<Products />} />
              <Route path="/orders" element={<Orders />} />
              <Route path="/customers" element={<Customers />} />
            </Routes>
          </div>
        </div>
      </div>
    </Router>
  );
};

export default App;
