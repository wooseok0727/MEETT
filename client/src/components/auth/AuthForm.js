import React, { useEffect, useRef } from "react";
import login from "../../assets/login.svg";
import avatar from "../../assets/profile.svg";
import styled from "styled-components";
import PersonIcon from "@mui/icons-material/Person";
import HttpsIcon from "@mui/icons-material/Https";
import { Link } from "react-router-dom";

const Content = styled.div`
  width: 100%;
  display: grid;
  grid-template-columns: repeat(2, 1fr);
  grid-gap: 7rem;
  position: relative;
  z-index: 10;
  font-family: "Poppins", sans-serif;
  text-transform: uppercase;
  font-size: 1.9rem;
  .image-wrapper {
    display: flex;
    justify-content: flex-end;
    align-items: center;
    img {
      width: 50rem;
    }
  }
  h2 {
    margin: 1.5rem 0;
    color: #363949;
    font-size: calc(0.8rem + 4vw);
  }
  button {
    font-family: "Poppins", sans-serif;
    font-size: 1.9rem;
    display: block;
    margin: 1rem auto;
    width: 100%;
    height: 5rem;
    border-radius: 1.5rem;
    outline: none;
    border: none;
    cursor: pointer;
    font-weight: bold;
    letter-spacing: 1px;
    color: #fff;
    background: linear-gradient(to right, #7380ec, #6873d9, #7380ec);
    background-size: 200%;
    transition: all 0.5s;
    &:hover {
      background-position: right;
    }
  }
`;

const FormWrapper = styled.div`
  display: flex;
  align-items: center;
  text-align: center;
  .register {
    display: block;
    text-align: right;
    text-decoration: none;
    color: #999;
    font-size: 1.3rem;
    font-weight: bold;
    transition: all 0.3s;
    &:hover {
      color: #ff7782;
    }
  }
`;

const InputBox = styled.div`
  flex: 1;
  position: relative;
  display: grid;
  grid-template-columns: 7% 93%;
  margin: 2.5rem 0;
  padding: 0.5rem 0;
  border-bottom: 2px solid #d9d9d9;
  &::before,
  &::after {
    content: "";
    position: absolute;
    bottom: -2px;
    width: 0%;
    height: 2px;
    background-color: #7380ec;
    transition: all 0.4s;
  }
  &::before {
    right: 50%;
  }
  &::after {
    left: 50%;
  }
  &.one {
    margin-top: 0;
  }
  &.focus::before,
  &.focus::after {
    width: 50%;
  }
  &.focus div h5 {
    top: -0.5rem;
    font-size: 1.3rem;
  }
  &.focus .icon-wrapper {
    color: #7380ec;
  }

  div {
    position: relative;
    height: 4.5rem;
    h5 {
      position: absolute;
      left: 1rem;
      top: 50%;
      transform: translateY(-50%);
      transition: all 0.3s;
      color: #9392b1;
    }
  }
  .icon-wrapper {
    color: #9392b1;
    display: flex;
    justify-content: center;
    align-items: center;
    transition: all 0.3s;
  }
`;

const Input = styled.input`
  position: absolute;
  left: 0;
  top: 0;
  width: 100%;
  height: 100%;
  border: none;
  outline: none;
  background: transparent;
  padding: 0.7rem 0.9rem;
  color: #000;
  font-size: 2rem;
  font-family: "Poppins", sans-serif;
`;

const AuthForm = ({ timeline }) => {
  const login_img = useRef(null);
  const auth_form = useRef(null);

  useEffect(() => {
    timeline.from(
      login_img.current,
      {
        duration: 0.5,
        y: -200,
        opacity: 0,
      },
      "-=3"
    );
    timeline.from(
      auth_form.current,
      {
        duration: 0.5,
        x: 100,
        opacity: 0,
      },
      "-=2.5"
    );
  });

  return (
    <Content>
      <div className="image-wrapper" ref={login_img}>
        <img src={login} alt="" />
      </div>
      <FormWrapper ref={auth_form}>
        <form>
          <img src={avatar} alt="" width="80" />
          <h2>Welcome</h2>
          <InputBox className="one">
            <div className="icon-wrapper">
              <PersonIcon sx={{ fontSize: 30 }} />
            </div>
            <div>
              <h5>Username</h5>
              <Input type="text" />
            </div>
          </InputBox>
          <InputBox className="">
            <div className="icon-wrapper">
              <HttpsIcon sx={{ fontSize: 28 }} />
            </div>
            <div>
              <h5>Password</h5>
              <Input type="password" />
            </div>
          </InputBox>
          <button>LOGIN</button>
          <Link to="/register" className="register">
            Register
          </Link>
        </form>
      </FormWrapper>
    </Content>
  );
};

export default AuthForm;
