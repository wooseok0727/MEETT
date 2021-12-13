import { createSlice } from "@reduxjs/toolkit";

const initialState = {
  theme: "light",
  themeText: "Dark Mode",
};

const themeSlice = createSlice({
  name: "theme",
  initialState,
  reducers: {
    setTheme: (state) => {
      state.theme = state.theme === "light" ? "dark" : "light";
      state.themeText = state.theme === "light" ? "Dark Mode" : "Light Mode";
    },
  },
});

export const { setTheme } = themeSlice.actions;
export default themeSlice;
