import { Route, Routes } from "react-router";
import "./App.css";
import * as PAGE from "./pages";

const App = () => {
  return (
    <>
      <Routes>
        <Route path="/" element={<PAGE.HomePage />} />
        <Route path="/login" element={<PAGE.LoginPage />} />
        <Route path="/register" element={<PAGE.RegisterPage />} />
        <Route path="/schedule/*" element={<PAGE.SchedulePage />} />
      </Routes>
    </>
  );
};

export default App;
