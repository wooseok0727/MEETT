import React from "react";
import { useSelector } from "react-redux";
import { Navigate } from "react-router-dom";

const PrivateRoute = ({ children }) => {
  const { username } = useSelector((state) => state.user.user);
  return username ? children : <Navigate to="/login" />;
};

export default PrivateRoute;
