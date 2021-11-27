import React, { useRef, useEffect } from "react";
import styled from "styled-components";
import { Power4 } from "gsap";

const TransitionWrapper = styled.div`
  position: absolute;
  z-index: 999;
  background-color: #363949;
  top: 0;
  width: 100%;
  height: 100vh;
`;

const Transition = ({ timeline }) => {
  const trans = useRef(null);
  useEffect(() => {
    timeline.to(trans.current, {
      duration: 4,
      x: 2500,
      ease: Power4.easeOut,
    });
  });

  return <TransitionWrapper ref={trans}></TransitionWrapper>;
};

export default Transition;
