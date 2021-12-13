import styled, { keyframes } from "styled-components";

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

const ContainerWrapper = styled.div`
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

export { HomeSection, ImgWrapper, ContainerWrapper, Content, TextWrapper };
