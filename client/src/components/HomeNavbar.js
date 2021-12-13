import React from "react";
import { Link } from "react-router-dom";
import {
  ContainerWrapper,
  HeaderWrapper,
  NavWrapper,
} from "./HomeNavbar.style";

const HomeNavbar = ({ auth }) => {
  return (
    <HeaderWrapper>
      <ContainerWrapper>
        <NavWrapper className="nav" auth={auth}>
          <Link to="/" className="nav-logo">
            MEET<span>T</span>
          </Link>
          <ul className="nav-list">
            <li className="nav-item">
              <Link to="/login" className="nav-link">
                JOIN NOW
              </Link>
            </li>
          </ul>
        </NavWrapper>
      </ContainerWrapper>
    </HeaderWrapper>
  );
};

export default HomeNavbar;
