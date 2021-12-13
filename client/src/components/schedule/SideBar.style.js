import styled from "styled-components";

const ContainerWrapper = styled.div`
  width: 22.225rem;
  height: 100%;
  position: fixed;
  z-index: 980;
  &.light {
    border-right: 1px solid #dddfe5;
    background: #fff;
  }
  &.dark {
    border-right: 1px solid #3c4752;
    background: #161c24;
  }
`;

const Sidebar = styled.div`
  display: flex;
  flex-direction: column;
  align-items: center;
  padding: 40px 0;
`;

const Logo = styled.div`
  display: flex;
  height: 4.2rem;
  margin-bottom: 4rem;
`;

const LogoTitle = styled.h1`
  font-family: "Poppins", sans-serif;
  font-size: 3rem;
  font-weight: bold;
  text-decoration: none;
  &.light {
    color: #363949;
  }
  &.dark {
    color: #fff;
  }
  span {
    color: #ff7782;
  }
`;

const Menu = styled.ul`
  width: 100%;
  display: flex;
  flex-direction: column;
  align-items: flex-start;
  list-style: none;
  font-family: "Poppins", sans-serif;
  li {
    &:not(:last-child) {
      margin-bottom: 12px;
    }
    border-left: 4px solid transparent;
    width: 100%;
    &.isActive {
      border-left-color: #7380ec;
    }
    .link {
      text-decoration: none;
      display: flex;
      align-items: center;
      padding: 16px 16px 16px 19px;

      .icon {
        width: 24px;
        height: 24px;
        margin: 0 12px 0 0;
        display: flex;
        align-items: center;
      }
      .text {
        font-size: 1.4rem;
        font-weight: 600;
        line-height: 20px;

        &.light {
          color: #929292;
        }
        &.dark {
          color: #fff;
        }
      }
    }
    &.isActive .link {
      .text {
        color: #7380ec;
      }
    }
  }
`;

const Divider = styled.div`
  margin: 40px 0;
  width: 100%;
  height: 1px;
  &.light {
    background: #dddfe5;
  }
  &.dark {
    background: #3c4752;
  }
`;

const ThemeToggle = styled.div`
  display: flex;
  align-items: center;
  width: 100%;
  padding: 16px 16px 16px 24px;

  .title {
    margin-left: 12px;
    line-height: 20px;
    font-size: 1.4rem;
    font-weight: bold;
    text-transform: uppercase;
    font-family: "Poppins", sans-serif;
    &.light {
      color: #929292;
    }
    &.dark {
      color: #fff;
    }
  }
`;

export {
  ContainerWrapper,
  Sidebar,
  Logo,
  LogoTitle,
  Menu,
  Divider,
  ThemeToggle,
};
