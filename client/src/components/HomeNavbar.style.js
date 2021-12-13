import styled from "styled-components";

const HeaderWrapper = styled.header`
  width: 100%;
  position: absolute;
  top: 0;
  left: 0;
  padding: 2rem 0;
  z-index: 888;
`;

const ContainerWrapper = styled.div`
  max-width: 140rem;
  margin: 0 auto;
  padding: 0 1.5rem;
`;

const NavWrapper = styled.nav`
  width: 100%;
  display: flex;
  justify-content: space-between;
  align-items: center;
  .nav-logo {
    font-family: "Poppins", sans-serif;
    font-size: 3rem;
    font-weight: bold;
    text-decoration: none;
    color: #363949;
    span {
      color: #ff7782;
    }
  }
  .nav-list {
    display: flex;
    list-style: none;
  }
  .nav-item {
    margin: 0 2.5rem;
    visibility: ${(props) => (props.auth ? "hidden" : "visible")};
  }
  .nav-link {
    display: inline-block;
    font-family: "Poppins", sans-serif;
    text-decoration: none;
    font-size: 1.4rem;
    font-weight: bold;
    letter-spacing: 1px;
    color: #fff;
    transition: all 0.3s;
    background-color: #7380ec;
    border-radius: 1.5rem;
    padding: 1rem;
    &:hover {
      transform: scale(1.048);
    }
  }
`;

export { HeaderWrapper, ContainerWrapper, NavWrapper };
