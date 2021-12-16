import React from "react";
import { Navigate } from "react-router-dom";

const PublicRoute = ({ children }) => {
  const authenticatedUser = localStorage.getItem("authenticatedUser");
  return authenticatedUser ? <Navigate to="/schedule/my" /> : children;
};

export default PublicRoute;
