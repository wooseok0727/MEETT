import React from "react";
import { useSelector } from "react-redux";
import { ContainerWrapper, HeaderWrapper } from "./Team.style";
import TeamCalendar from "./TeamCalendar";
import TeamList from "./TeamList";

const Team = () => {
  const { theme } = useSelector((state) => state.theme);
  const themeClass = theme === "light" ? "light" : "dark";
  return (
    <>
      <ContainerWrapper className={themeClass}>
        <HeaderWrapper>
          <h2 className={`title ${themeClass}`}>CALENDAR</h2>
        </HeaderWrapper>
        <TeamCalendar />
      </ContainerWrapper>
      <ContainerWrapper className={themeClass}>
        <HeaderWrapper>
          <h2 className={`title ${themeClass}`}>LIST</h2>
        </HeaderWrapper>
        <TeamList />
      </ContainerWrapper>
    </>
  );
};

export default Team;
