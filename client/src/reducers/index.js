import { combineReducers } from "redux";
import teamSlice from "./team";
import themeSlice from "./theme";
import userSlice from "./user";

const rootReducer = combineReducers({
  user: userSlice.reducer,
  theme: themeSlice.reducer,
  team: teamSlice.reducer,
});

export default rootReducer;
