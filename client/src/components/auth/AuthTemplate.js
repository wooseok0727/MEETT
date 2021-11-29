import React, { useEffect, useRef } from "react";
import styled from "styled-components";
import bottom2 from "../../assets/bottom2.png";
import HomeNavbar from "../HomeNavbar";
import Transition from "../../lib/Transition";
import gsap from "gsap";

const AuthSection = styled.section`
  width: 100%;
  height: 100vh;
  background-color: #fff;
  overflow: hidden;
  position: relative;
  display: flex;
  align-items: center;
`;

const ImgWrapper = styled.div`
  position: absolute;
  &.bottom {
    bottom: 0rem;
    left: -30rem;
  }
`;

const Container = styled.div`
  max-width: 140rem;
  margin: 0 auto;
  padding: 0 1.5rem;
`;

const AuthTemplate = ({ children }) => {
  const auth = gsap.timeline();
  const bottom_img = useRef(null);

  useEffect(() => {
    auth.from(
      bottom_img.current,
      {
        duration: 0.5,
        x: -100,
        opacity: 0,
      },
      "-=3.5"
    );
  });
  return (
    <>
      <HomeNavbar auth />
      <Transition timeline={auth} />
      <AuthSection>
        <ImgWrapper className="bottom" ref={bottom_img}>
          <img src={bottom2} alt="" width="1547" height="1060" />
        </ImgWrapper>
        <Container>
          {React.cloneElement(children, { timeline: auth })}
        </Container>
      </AuthSection>
    </>
  );
};

export default AuthTemplate;
