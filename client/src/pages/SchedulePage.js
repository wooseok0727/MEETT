import React from "react";
import SideBar from "../components/schedule/SideBar";
import Schedule from "../components/schedule/Schedule";
import { useSelector } from "react-redux";
import styled from "styled-components";
import { Route, Routes } from "react-router-dom";
import Transition from "../lib/Transition";
import gsap from "gsap";

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
  const schedule = gsap.timeline();

  return (
    <>
      <Transition timeline={schedule} />
      <Container className={themeClass}>
        <div className="main">
          <SideBar />

          <Routes>
            <Route path="/team" element={<Schedule title="ROOM LIST" />} />
            <Route path="/my" element={<Schedule title="MY SCHEDULE" />} />
            <Route path="/mypage" element={<Schedule title="MY PAGE" />} />
            <Route path="/settings" element={<Schedule title="SETTINGS" />} />
          </Routes>
        </div>
      </Container>
    </>
  );
};

export default SchedulePage;
