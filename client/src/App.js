import { Route, Routes } from "react-router";
import PrivateRoute from "./components/auth/PrivateRoute";
import PublicRoute from "./components/auth/PublicRoute";
import * as PAGE from "./pages";

const App = () => {
  return (
    <Routes>
      <Route
        path="/"
        element={
          <PublicRoute>
            <PAGE.HomePage />
          </PublicRoute>
        }
      />
      <Route
        path="/login"
        element={
          <PublicRoute>
            <PAGE.LoginPage />
          </PublicRoute>
        }
      />
      <Route
        path="/register"
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
