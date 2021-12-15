import { Route, Routes } from "react-router";
import "./App.css";
import PrivateRoute from "./components/auth/PrivateRoute";
import PublicRoute from "./components/auth/PublicRoute";
import * as PAGE from "./pages";

const App = () => {
  return (
    <Routes>
      <Route
        path="/"
        restricted={true}
        element={
          <PublicRoute>
            <PAGE.HomePage />
          </PublicRoute>
        }
      />
      <Route
        path="/login"
        restricted={true}
        element={
          <PublicRoute>
            <PAGE.LoginPage />
          </PublicRoute>
        }
      />
      <Route
        path="/register"
        restricted={true}
        element={
          <PublicRoute>
            <PAGE.RegisterPage />
          </PublicRoute>
        }
      />
      <Route
        path="/schedule/*"
        element={
          <PrivateRoute>
            <PAGE.SchedulePage />
          </PrivateRoute>
        }
      />
    </Routes>
  );
};

export default App;
