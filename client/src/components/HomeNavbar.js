import React from "react";
import { Link } from "react-router-dom";
import { Container, Header, Nav } from "./HomeNavbar.style";

const HomeNavbar = ({ auth }) => {
  return (
    <Header>
      <Container>
        <Nav className="nav" auth={auth}>
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
        </Nav>
      </Container>
    </Header>
  );
};

export default HomeNavbar;
