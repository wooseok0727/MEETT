import React from "react";
import SideBar from "../components/schedule/SideBar";
import Schedule from "../components/schedule/Schedule";
import { useSelector } from "react-redux";
import styled from "styled-components";

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
        <Schedule />
      </div>
    </Container>
  );
};

export default SchedulePage;
