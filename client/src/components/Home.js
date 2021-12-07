import React, { useRef, useEffect } from "react";
import top from "../assets/top.png";
import bottom from "../assets/bottom.png";
import calendar from "../assets/calendar.png";
import { Link } from "react-router-dom";
import Transition from "../lib/Transition";
import HomeNavbar from "./HomeNavbar";
import gsap from "gsap";
import {
  Container,
  HomeSection,
  Content,
  ImgWrapper,
  TextWrapper,
} from "./Home.style";

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
  }, [home]);

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
              <Link to="/register" className="cta">
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
