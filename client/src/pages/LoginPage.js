import React from "react";
import AuthForm from "../components/auth/AuthForm";
import AuthTemplate from "../components/auth/AuthTemplate";
import HomeNavbar from "../components/HomeNavbar";

const LoginPage = () => {
  return (
    <>
      <HomeNavbar />
      <AuthTemplate>
        <AuthForm />
      </AuthTemplate>
    </>
  );
};

export default LoginPage;
