import React, { useEffect } from "react";
import { useSelector } from "react-redux";
import { useNavigate } from "react-router-dom";

const AuthRoute = ({ children }) => {
  const { user } = useSelector((state) => state.user);
  const navigate = useNavigate();

  const authenticated = user.username == null;

  console.log(authenticated);
  useEffect(() => {
    if (authenticated) {
      navigate("/login");
    }
  }, [authenticated, navigate]);

  return <>{children}</>;
};

export default AuthRoute;
