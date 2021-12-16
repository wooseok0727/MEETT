import { createAsyncThunk } from "@reduxjs/toolkit";
import api from "./api.ctrl";
import qs from "qs";

export const create = createAsyncThunk(
  "team/create",
  async ({ username, title, password, rejectWithValue }) => {
    try {
      const response = await api.post("/schedule/team/create", {
        username,
        title,
        password,
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

export const list = createAsyncThunk(
  "/team/list",
  async ({ title, rejectWithValue }) => {
    const queryString = qs.stringify({
      title,
    });
    try {
      const response = await api.get(`/schedule/team?${queryString}`);
      return response.data;
    } catch (error) {
      console.log(error.response.data);
      console.log(error.response.status);
      console.log(error);
      return rejectWithValue(error.response.data);
    }
  }
);

export const update = createAsyncThunk(
  "team/update",
  async ({ teamId, title, username, password, rejectWithValue }) => {
    try {
      const response = await api.put(`/schedule/team/${teamId}`, {
        teamId,
        title,
        username,
        password,
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

export const remove = createAsyncThunk(
  "team/remove",
  async ({ teamId, rejectWithValue }) => {
    try {
      const response = await api.delete(`/schedule/team/${teamId}`);
      return response.data;
    } catch (error) {
      console.log(error.response.data);
      console.log(error.response.status);
      console.log(error);
      return rejectWithValue(error.response.data);
    }
  }
);
