import styled from "styled-components";

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

export { AuthSection, ImgWrapper, Container };
