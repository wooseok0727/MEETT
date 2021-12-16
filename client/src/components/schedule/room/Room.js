import React from "react";
import { useSelector } from "react-redux";
import { ContainerWrapper, HeaderWrapper } from "../commons/Wrapper.style";
import RoomList from "./RoomList";

const Room = () => {
  const { theme } = useSelector((state) => state.theme);
  const themeClass = theme === "light" ? "light" : "dark";
  return (
    <ContainerWrapper className={themeClass}>
      <HeaderWrapper>
        <h2 className={`title ${themeClass}`}>LIST</h2>
      </HeaderWrapper>
      <RoomList />
    </ContainerWrapper>
  );
};

export default Room;
