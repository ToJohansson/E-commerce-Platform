import React from "react";
import { BrowserRouter as Router, Routes, Route } from "react-router-dom";
import ProductForm from "./Forms/ProductForm";
import Header from "./Components/Header";
import LeftNav from "./Components/LeftNav";
import DisplayContent from "./Components/DisplayContent";
import "./app.css";

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
              <Route path="/add-product" element={<ProductForm />} />
              <Route
                path="/products"
                element={<DisplayContent title="Products" />}
              />
              <Route
                path="/orders"
                element={<DisplayContent title="Orders" />}
              />
              <Route
                path="/customers"
                element={<DisplayContent title="Customers" />}
              />
            </Routes>
          </div>
        </div>
      </div>
    </Router>
  );
};

export default App;
