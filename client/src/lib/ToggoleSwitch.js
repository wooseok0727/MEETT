import React, { useState } from "react";
import styled from "styled-components";
import { useDispatch } from "react-redux";
import { setTheme } from "../reducers/theme";

const Switch = styled.div`
  width: 56px;
  height: 24px;
  border-radius: 50px;
  background: #e5e5e5;
  position: relative;
  cursor: pointer;
  transition: background 0.3s ease;

  &.on {
    background: #7380ec;
  }
  .thumb {
    background: ${(props) => (props.on ? `#fff` : `#7380ec`)};
    width: 20px;
    height: 20px;
    position: absolute;
    top: 2px;
    left: ${(props) => (props.on ? `calc(100% - 20px - 2px)` : `2px`)};
    border-radius: 50%;
    transition: left 0.3s ease;
  }
`;

const ToggoleSwitch = ({ toggle }) => {
  const [switchOn, setSwitchOn] = useState(false);
  const dispatch = useDispatch();

  const switchToggleHandler = () => {
    setSwitchOn((p) => !p);
    dispatch(setTheme());
  };

  const switchOnClass = switchOn ? "on" : "";

  return (
    <Switch
      className={`${switchOnClass}`}
      on={`${switchOnClass}`}
      onClick={switchToggleHandler}
    >
      <div className="thumb" />
    </Switch>
  );
};

export default ToggoleSwitch;
