import React from "react";
import { BrowserRouter as Router, Routes, Route } from "react-router-dom";
import Login from "./components/Login";

import SubjectTable from "./components/SubjectTable";

function App() {
  return (
    <Router>
      <Routes>
        <Route path="/" element={<Login />} />
        <Route path="/monhoc" element={<SubjectTable />} />
      </Routes>
    </Router>
  );
}

export default App;
