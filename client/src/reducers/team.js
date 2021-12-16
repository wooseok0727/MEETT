import { createSlice } from "@reduxjs/toolkit";
import { create, list, update, remove } from "../actions/team";

const initialState = {
  teams: null,
  listLoading: false, // 조회 시도중
  listDone: false,
  listError: null,
  createLoading: false, // 생성 시도중
  createDone: false,
  createError: null,
  updateLoading: false, // 수정 시도중
  updateDone: false,
  updateError: null,
  removeLoading: false, // 삭제 시도중
  removeDone: false,
  removeError: null,
};

const teamSlice = createSlice({
  name: "teams",
  initialState,
  reducers: {},
  extraReducers: (builder) => {
    builder
      .addCase(list.pending, (state, action) => {
        console.log("팀 리스트 조회 대기");
        state.listLoading = true;
        state.listDone = false;
        state.listError = null;
      })
      .addCase(list.fulfilled, (state, action) => {
        console.log("팀 리스트 조회 성공");
        console.log(action.payload);
        state.teams = action.payload;
        state.listLoading = false;
        state.listDone = true;
      })
      .addCase(list.rejected, (state, action) => {
        console.log("팀 리스트 조회 실패");
        state.listLoading = false;
        state.listError = action.payload;
      });
  },
});

export default teamSlice;
