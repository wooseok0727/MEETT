import React from "react";
import { useSelector } from "react-redux";
import { Navigate } from "react-router-dom";

const PublicRoute = ({ children, restricted }) => {
  const { username } = useSelector((state) => state.user.user);
  return username && restricted ? <Navigate to="/schdule/my" /> : children;
};

export default PublicRoute;
