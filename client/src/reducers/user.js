import { createSlice } from "@reduxjs/toolkit";
import { signup, logIn } from "../actions/user";

const initialState = {
  user: {
    username: null,
    password: null,
    nickname: null,
    email: null,
  },
  loginLoading: false, // 로그인 시도중
  loginDone: false,
  loginError: null,
  signupLoading: false, // 회원가입 시도중
  signupDone: false,
  signupError: null,
};

const userSlice = createSlice({
  name: "user",
  initialState,
  reducers: {
    logOut: (state) => {
      state.user.username = null;
      state.user.nickname = null;
      state.user.email = null;
      state.user.password = null;
      state.loginDone = false;
      state.signupDone = false;
    },
  },
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
        state.loginDone = true;
        localStorage.setItem("token", action.payload.token);
        localStorage.setItem("authenticatedUser", action.payload.username);
      })
      .addCase(logIn.rejected, (state, action) => {
        console.log("로그인 실패");
        state.loginLoading = false;
        state.loginError = action.payload;
      });
  },
});

export const { logOut } = userSlice.actions;
export default userSlice;
