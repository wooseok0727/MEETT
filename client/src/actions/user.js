import { createAsyncThunk } from "@reduxjs/toolkit";
import api from "./api.ctrl";

export const signup = createAsyncThunk(
  "user/register",
  async ({ username, password, nickname, email, rejectWithValue }) => {
    try {
      const response = await api.post("/user/register", {
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
  "user/logIn",
  async ({ username, password, rejectWithValue }) => {
    try {
      const response = await api.post("/user/login", { username, password });
      return response.data;
    } catch (error) {
      console.log(error.response.data);
      console.log(error.response.status);
      console.log(error);
      return rejectWithValue(error.response.data);
    }
  }
);
