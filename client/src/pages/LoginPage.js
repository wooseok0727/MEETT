import React from "react";
import AuthForm from "../components/auth/AuthForm";
import AuthTemplate from "../components/auth/AuthTemplate";

const LoginPage = () => {
  return (
    <AuthTemplate>
      <AuthForm type="LOGIN" />
    </AuthTemplate>
  );
};

export default LoginPage;
