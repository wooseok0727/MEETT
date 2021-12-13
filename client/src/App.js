import { Route, Routes } from "react-router";
import "./App.css";
import * as PAGE from "./pages";

const App = () => {
  return (
    <>
      <Routes>
        <Route path="/" element={<PAGE.HomePage />} />
        <Route path="/mypage" element={<PAGE.MyPage />} />
        <Route path="/login" element={<PAGE.LoginPage />} />
        <Route path="/register" element={<PAGE.RegisterPage />} />
        <Route path="/schedule" element={<PAGE.SchedulePage />} />
        <Route path="/team" element={<PAGE.TeamSchedulePage />} />
        <Route path="/my" element={<PAGE.MySchedulePage />} />
      </Routes>
    </>
  );
};

export default App;
