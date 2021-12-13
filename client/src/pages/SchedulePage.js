import React from "react";
import SideBar from "../components/schedule/SideBar";
import Schedule from "../components/schedule/Schedule";
import { useSelector } from "react-redux";
import styled from "styled-components";
import { Route, Routes } from "react-router-dom";
import { MyPage } from "./index";

const Container = styled.div`
  &.light {
    background: #fff;
  }
  &.dark {
    background: #161c24;
  }
  .main {
    max-width: 1920px;
    margin: 0 auto;
  }
`;

const SchedulePage = () => {
  const { theme } = useSelector((state) => state.theme);
  const themeClass = theme === "light" ? "light" : "dark";

  return (
    <Container className={themeClass}>
      <div className="main">
        <SideBar />
        <Routes>
          <Route path="/team" element={<Schedule title="TEAM SCHEDULE" />} />
          <Route path="/my" element={<Schedule title="MY SCHEDULE" />} />
          <Route path="/mypage" element={<Schedule title="MY SCHEDULE" />} />
          <Route path="/settings" element={<Schedule title="MY SCHEDULE" />} />
        </Routes>
      </div>
    </Container>
  );
};

export default SchedulePage;
