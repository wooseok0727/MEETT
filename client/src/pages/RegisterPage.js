import React from "react";
import AuthForm from "../components/auth/AuthForm";
import AuthTemplate from "../components/auth/AuthTemplate";

const RegisterPage = () => {
  return (
    <>
      <AuthTemplate>
        <AuthForm />
      </AuthTemplate>
    </>
  );
};

export default RegisterPage;
