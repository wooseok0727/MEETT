import { Route, Routes } from "react-router";
import "./App.css";
import AuthRoute from "./components/auth/AuthRoute";
import * as PAGE from "./pages";

const App = () => {
  return (
    <>
      <Routes>
        <Route path="/" element={<PAGE.HomePage />} />
        <Route path="/login" element={<PAGE.LoginPage />} />
        <Route path="/register" element={<PAGE.RegisterPage />} />
        <Route
          path="/schedule/*"
          element={
            <AuthRoute>
              <PAGE.SchedulePage />
            </AuthRoute>
          }
        />
      </Routes>
    </>
  );
};

export default App;
