import React from "react";
import { useSelector } from "react-redux";
import Header from "./Header";
import Personal from "./personal/Personal";
import Room from "./room/Room";
import {
  ScrollWrapper,
  ContainerWrapper,
  SchedulerWrapper,
} from "./Schedule.style";

const Schedule = ({ title }) => {
  const { theme } = useSelector((state) => state.theme);
  const themeClass = theme === "light" ? "light" : "dark";
  return (
    <ScrollWrapper className={themeClass}>
      <ContainerWrapper className={themeClass}>
        <SchedulerWrapper>
          <Header title={title} />
          <div className="content">
            {title === "ROOM LIST" && <Room />}
            {title === "MY SCHEDULE" && <Personal />}
            {title === "MY PAGE" && <Personal />}
            {title === "SETTINGS" && <Personal />}
          </div>
        </SchedulerWrapper>
      </ContainerWrapper>
    </ScrollWrapper>
  );
};

export default Schedule;
