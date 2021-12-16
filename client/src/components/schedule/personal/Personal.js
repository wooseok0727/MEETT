import React from "react";
import { useSelector } from "react-redux";
import { ContainerWrapper, HeaderWrapper } from "../commons/Wrapper.style";
import PersonalCalendar from "./PersonalCalendar";
import PersonalList from "./PersonalList";

const Personal = () => {
  const { theme } = useSelector((state) => state.theme);
  const themeClass = theme === "light" ? "light" : "dark";
  return (
    <>
      <ContainerWrapper className={themeClass}>
        <HeaderWrapper>
          <h2 className={`title ${themeClass}`}>CALENDAR</h2>
        </HeaderWrapper>
        <PersonalCalendar />
      </ContainerWrapper>
      <ContainerWrapper className={themeClass}>
        <HeaderWrapper>
          <h2 className={`title ${themeClass}`}>LIST</h2>
        </HeaderWrapper>
        <PersonalList />
      </ContainerWrapper>
    </>
  );
};

export default Personal;
