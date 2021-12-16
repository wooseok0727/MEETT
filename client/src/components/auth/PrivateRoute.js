import React from "react";
import { Navigate } from "react-router-dom";

const PrivateRoute = ({ children }) => {
  const authenticatedUser = localStorage.getItem("authenticatedUser");
  return authenticatedUser ? children : <Navigate to="/login" />;
};

export default PrivateRoute;
