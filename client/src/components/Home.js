import React, { useRef, useEffect } from "react";
import HomeNavbar from "./HomeNavbar";
import styled, { keyframes } from "styled-components";
import top from "../assets/top.png";
import bottom from "../assets/bottom.png";
import calendar from "../assets/calendar.png";
import { Link } from "react-router-dom";
import Transition from "../lib/Transition";
import gsap from "gsap";

const move = keyframes`
    0% { transform: translateY(-0.5rem)  }
    50% { transform: translateY(1rem) }
    100% { transform: translateY(-0.5rem) }
`;

const HomeSection = styled.section`
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
  &.top {
    top: 0rem;
    left: 0rem;
  }
  &.bottom {
    bottom: 0rem;
    right: -30rem;
  }
`;

const Container = styled.div`
  max-width: 140rem;
  margin: 0 auto;
  padding: 0 1.5rem;
`;

const Content = styled.div`
  width: 100%;
  display: flex;
  align-items: center;
  position: relative;
  z-index: 10;
  .calendar-wrapper {
    margin-left: 1rem;
    flex: 0 0 45%;
    img {
      animation: ${move} 2.5s ease infinite;
    }
  }
`;

const TextWrapper = styled.div`
  color: #363949;
  flex: 1;
  text-transform: uppercase;
  font-family: "Poppins", sans-serif;
  h1 {
    font-size: calc(0.8rem + 4vw);
    font-weight: bold;
    line-height: 1.2;
    span {
      color: #7380ec;
    }
  }
  h5 {
    font-size: 1.9rem;
    font-weight: 800;
    margin: 4rem 0 4rem 0;
  }
  p {
    text-transform: lowercase;
    font-size: 1.6rem;
    line-height: 1.5;
    margin: 2rem 0 2rem 0;
  }
  .cta {
    display: inline-block;
    font-size: 1.4rem;
    text-decoration: none;
    color: #fff;
    font-weight: 800;
    padding: 1rem;
    background-color: #7380ec;
    border-radius: 1.5rem;
    transition: all 0.5s;
    &:hover {
      transform: scale(1.048);
    }
  }
`;

const Home = () => {
  const home = gsap.timeline();
  const home_h1 = useRef(null);
  const home_img = useRef(null);
  const top_img = useRef(null);
  const bottom_img = useRef(null);

  useEffect(() => {
    home.from(
      top_img.current,
      {
        duration: 0.5,
        x: -100,
        opacity: 0,
      },
      "-=3.5"
    );
    home.from(
      bottom_img.current,
      {
        duration: 0.5,
        x: -100,
        opacity: 0,
      },
      "-=3.5"
    );
    home.from(
      home_h1.current,
      {
        duration: 0.6,
        skewX: 10,
        x: -100,
        opacity: 0,
      },
      "-=3"
    );
    home.from(
      home_img.current,
      {
        duration: 0.5,
        y: -200,
        opacity: 0,
      },
      "-=2.5"
    );
  });

  return (
    <>
      <HomeNavbar />
      <Transition timeline={home} />
      <HomeSection>
        <ImgWrapper className="top" ref={top_img}>
          <img src={top} alt="" width="680" height="314" />
        </ImgWrapper>
        <ImgWrapper className="bottom" ref={bottom_img}>
          <img src={bottom} alt="" width="1547" height="1060" />
        </ImgWrapper>
        <Container>
          <Content>
            <TextWrapper ref={home_h1}>
              <h1>
                SMART <br /> TEAM <span>Schedule</span>
              </h1>
              <h5>Lorem Ipsum is simply dummy</h5>
              <p>
                Lorem Ipsum is simply dummy text of the printing Lorem <br />
                Ipsum has been the industry's standard dummy
              </p>
              <Link to="/login" className="cta">
                Get Started
              </Link>
            </TextWrapper>
            <div className="calendar-wrapper" ref={home_img}>
              <img src={calendar} alt="" width="700" height="700" />
            </div>
          </Content>
        </Container>
      </HomeSection>
    </>
  );
};

export default Home;
