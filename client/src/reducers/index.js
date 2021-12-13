import { combineReducers } from "redux";
import themeSlice from "./theme";

import userSlice from "./user";

const rootReducer = combineReducers({
  user: userSlice.reducer,
  theme: themeSlice.reducer,
});

export default rootReducer;
