import { createSlice } from "@reduxjs/toolkit";
import { signup, logIn, logOut } from "../actions/user";

const initialState = {
  user: {
    username: "",
    password: "",
    nickname: "",
    email: "",
  },
  loginLoading: false, // 로그인 시도중
  loginDone: false,
  loginError: null,
  logoutLoading: false, // 로그아웃 시도중
  logoutDone: false,
  logoutError: null,
  signupLoading: false, // 회원가입 시도중
  signupDone: false,
  signupError: null,
};

const userSlice = createSlice({
  name: "user",
  initialState,
  reducers: {},
  extraReducers: (builder) => {
    builder
      // 회원가입
      .addCase(signup.pending, (state, action) => {
        console.log("회원가입 대기");
        state.signupLoading = true;
        state.signupDone = false;
        state.signupError = null;
      })
      .addCase(signup.fulfilled, (state, action) => {
        console.log("회원가입 성공");
        console.log(action.payload);
        state.signupLoading = false;
        state.signupDone = true;
      })
      .addCase(signup.rejected, (state, action) => {
        console.log("회원가입 실패");
        state.signupLoading = false;
        state.signupError = action.payload;
      })
      // 로그인
      .addCase(logIn.pending, (state, action) => {
        console.log("로그인 대기");
        state.loginLoading = true;
        state.loginDone = false;
        state.loginError = null;
      })
      .addCase(logIn.fulfilled, (state, action) => {
        console.log("로그인 성공");
        state.loginLoading = false;
        state.user.username = action.payload.username;
        state.user.nickname = action.payload.nickname;
        state.user.email = action.payload.email;
        state.loginDone = true;
      })
      .addCase(logIn.rejected, (state, action) => {
        console.log("로그인 실패");
        state.loginLoading = false;
        state.loginError = action.payload;
      })
      // 로그아웃
      .addCase(logOut.pending, (state, action) => {
        console.log("로그아웃 대기");
        state.logoutLoading = false;
        state.logoutDone = false;
        state.logoutError = null;
      })
      .addCase(logOut.fulfilled, (state, action) => {
        console.log("로그아웃 성공");
        state.logoutLoading = false;
        state.logoutDone = true;
        state.user = null;
      })
      .addCase(logOut.rejected, (state, action) => {
        console.log("로그아웃 실패");
        state.logoutLoading = false;
        state.logoutError = action.payload;
      });
  },
});

export default userSlice;
