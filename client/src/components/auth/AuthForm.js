import React, { useCallback, useEffect, useRef, useState } from "react";
import login from "../../assets/login.svg";
import register from "../../assets/register.svg";
import avatar from "../../assets/profile.svg";
import PersonIcon from "@mui/icons-material/Person";
import PersonOutlineIcon from "@mui/icons-material/PersonOutline";
import HttpsIcon from "@mui/icons-material/Https";
import EmailIcon from "@mui/icons-material/Email";
import NaverIcon from "../../assets/Naver.png";
import GoogleIcon from "../../assets/Google.png";
import FaceBookIcon from "../../assets/Facebook-white.png";
import { Link, useNavigate } from "react-router-dom";
import {
  Content,
  FormWrapper,
  LineDivider,
  SnsImage,
  Input,
  InputBox,
  SnsLink,
} from "./AuthForm.style";
import useInput from "../../hooks/useInput";
import { useSelector, useDispatch } from "react-redux";
import { logIn, signup } from "../../actions/user";

const AuthForm = ({ timeline, type }) => {
  const [{ username, password, email, nickname }, onChange, reset] = useInput({
    username: "",
    password: "",
    email: "",
    nickname: "",
  });

  const [passwordCheck, setPasswordCheck] = useState("");
  const [passwordError, setPasswordError] = useState(false);

  const { signupError, signupDone, loginError, loginDone } = useSelector(
    (state) => state.user
  );

  console.log(passwordError, signupError, loginError, reset);

  const dispatch = useDispatch();
  const navigate = useNavigate();

  const onChangePasswordCheck = useCallback(
    (e) => {
      setPasswordCheck(e.target.value);
      setPasswordError(e.target.value !== password);
    },
    [password]
  );

  useEffect(() => {
    if (loginDone) {
      navigate("/schedule/team", { replace: true });
    }
    if (signupDone) {
      navigate("/", { replace: true });
    }
  }, [signupDone, loginDone, navigate]);

  const onSubmitForm = useCallback(
    (e) => {
      e.preventDefault();
      if (type === "REGISTER") {
        if (password !== passwordCheck) {
          return setPasswordError(true);
        }
        dispatch(
          signup({
            username: username,
            password: password,
            nickname: nickname,
            email: email,
          })
        );
      } else {
        dispatch(
          logIn({
            username: username,
            password: password,
          })
        );
      }
    },
    [dispatch, username, password, nickname, email, passwordCheck, type]
  );

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
  }, [timeline]);

  return (
    <Content>
      <div className="image-wrapper" ref={login_img}>
        {type === "REGISTER" ? (
          <img src={register} alt="" />
        ) : (
          <img src={login} alt="" />
        )}
      </div>
      <FormWrapper ref={auth_form}>
        <form onSubmit={onSubmitForm}>
          {type === "REGISTER" ? (
            <h2>REGISTER</h2>
          ) : (
            <>
              <img src={avatar} alt="" width="80" />
              <h2>Welcome</h2>
            </>
          )}
          <InputBox className={`"one" ${username && "focus"}`}>
            <div className="icon-wrapper">
              <PersonIcon sx={{ fontSize: 30 }} />
            </div>
            <div>
              <h5>Username</h5>
              <Input
                type="text"
                name="username"
                value={username}
                onChange={onChange}
              />
            </div>
          </InputBox>
          <InputBox className={password && "focus"}>
            <div className="icon-wrapper">
              <HttpsIcon sx={{ fontSize: 28 }} />
            </div>
            <div>
              <h5>Password</h5>
              <Input
                type="password"
                name="password"
                value={password}
                onChange={onChange}
              />
            </div>
          </InputBox>
          {type === "REGISTER" && (
            <>
              <InputBox className={passwordCheck && "focus"}>
                <div className="icon-wrapper">
                  <HttpsIcon sx={{ fontSize: 28 }} />
                </div>
                <div>
                  <h5>Password Confirm</h5>
                  <Input
                    type="password"
                    value={passwordCheck}
                    onChange={onChangePasswordCheck}
                  />
                </div>
              </InputBox>
              <InputBox className={nickname && "focus"}>
                <div className="icon-wrapper">
                  <PersonOutlineIcon sx={{ fontSize: 28 }} />
                </div>
                <div>
                  <h5>Nickname</h5>
                  <Input
                    type="text"
                    name="nickname"
                    value={nickname}
                    onChange={onChange}
                  />
                </div>
              </InputBox>
              <InputBox className={email && "focus"}>
                <div className="icon-wrapper">
                  <EmailIcon sx={{ fontSize: 28 }} />
                </div>
                <div>
                  <h5>E-mail</h5>
                  <Input
                    type="email"
                    name="email"
                    value={email}
                    onChange={onChange}
                  />
                </div>
              </InputBox>
            </>
          )}

          {type === "REGISTER" ? (
            <>
              <button>CREATE ACCOUNT</button>
              <Link to="/login" className="change">
                Login
              </Link>
            </>
          ) : (
            <>
              <button>LOGIN</button>
              <LineDivider>
                <span>OR</span>
              </LineDivider>
              <SnsLink to="#" background="#23c704">
                <SnsImage src={NaverIcon} width="18" />
                네이버 로그인
              </SnsLink>
              <SnsLink to="#" background="#1877f2">
                <SnsImage src={FaceBookIcon} width="18" />
                페이스북 로그인
              </SnsLink>
              <SnsLink to="#" color="#000" border="true">
                <SnsImage src={GoogleIcon} width="18" />
                구글 로그인
              </SnsLink>
              <Link to="/register" className="change">
                Register
              </Link>
            </>
          )}
        </form>
      </FormWrapper>
    </Content>
  );
};

export default AuthForm;
