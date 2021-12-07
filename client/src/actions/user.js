import { createAsyncThunk } from "@reduxjs/toolkit";
import axios from "axios";

axios.defaults.baseURL = "http://localhost:8080/meett/user";

export const signup = createAsyncThunk(
  "user/register",
  async ({ username, password, nickname, email, rejectWithValue }) => {
    try {
      const response = await axios.post("/register", {
        username,
        password,
        nickname,
        email,
      });
      return response.data;
    } catch (error) {
      console.log(error.response.data);
      console.log(error.response.status);
      console.log(error);
      return rejectWithValue(error.response.data);
    }
  }
);

export const logIn = createAsyncThunk(
  "/user/logIn",
  async ({ username, password, rejectWithValue }) => {
    try {
      const response = await axios.post("/login", { username, password });
      return response.data;
    } catch (error) {
      console.log(error.response.data);
      console.log(error.response.status);
      console.log(error);
      return rejectWithValue(error.response.data);
    }
  }
);

export const logOut = createAsyncThunk("/user/logOut", async () => {
  const response = await axios.post("/logout");
  return response.data;
});
