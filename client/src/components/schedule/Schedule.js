import React from "react";
import { useSelector } from "react-redux";
import Header from "./Header";
import Personal from "./personal/Personal";
import {
  ScrollWrapper,
  ContainerWrapper,
  SchedulerWrapper,
} from "./Schedule.style";

const Schedule = () => {
  const { theme } = useSelector((state) => state.theme);
  const themeClass = theme === "light" ? "light" : "dark";
  return (
    <ScrollWrapper className={themeClass}>
      <ContainerWrapper className={themeClass}>
        <SchedulerWrapper>
          <Header />
          <div className="content">
            <Personal />
          </div>
        </SchedulerWrapper>
      </ContainerWrapper>
    </ScrollWrapper>
  );
};

export default Schedule;
