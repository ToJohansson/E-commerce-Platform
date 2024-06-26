import React from "react";
import Header from "./Components/Header";
import LeftNav from "./Components/LeftNav";
import DisplayContent from "./Components/DisplayContent";
import "./app.css";

const App = () => {
  return (
    <div className="container-fluid d-flex flex-column">
      <div className="row header">
        <div className="col">
          <Header />
        </div>
      </div>
      <div className="row flex-grow-1 d-flex flex-row">
        <div className="col-md-2 leftNav d-flex flex-column">
          <LeftNav />
        </div>
        <div className="col-md-10 mainContent d-flex flex-column">
          <DisplayContent />
        </div>
      </div>
    </div>
  );
};

export default App;
