import React from "react";
import AuthForm from "../components/auth/AuthForm";
import AuthTemplate from "../components/auth/AuthTemplate";

const RegisterPage = () => {
  return (
    <>
      <AuthTemplate>
        <AuthForm type="REGISTER" />
      </AuthTemplate>
    </>
  );
};

export default RegisterPage;
