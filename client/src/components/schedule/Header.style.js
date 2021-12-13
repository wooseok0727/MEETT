import styled from "styled-components";
import { styled as muiStyled } from "@mui/material/styles";
import { Avatar } from "@mui/material";

const HeaderWrapper = styled.div`
  display: flex;
  justify-content: space-between;
  align-items: center;
  height: 42px;
  min-width: 900px;
`;

const Title = styled.h2`
  font-weight: 800;
  font-size: 2.1rem;
  letter-spacing: 0.4px;
  line-height: 30px;
  font-family: "Poppins", sans-serif;
  &.light {
    color: #252733;
  }
  &.dark {
    color: #fff;
  }
`;

const Content = styled.div`
  display: flex;
  align-items: center;
  height: 100%;

  .search {
    margin-right: 1rem;
    .search_form {
      display: flex;
      align-items: center;
      .search_bar {
        position: relative;
        background: transparent;
        border: none;
        width: 200px;
        padding-left: 30px;
        font-size: 1.7rem;
        height: 40px;
        font-family: "Poppins", sans-serif;
        &:focus {
          outline: none;
          transition: border 0.2s ease;
        }
        &.light {
          color: #252733;
        }
        &.dark {
          color: #fff;
        }
      }
      .search_btn {
        display: flex;
        align-items: center;
        height: 52px;
        width: 40px;
        background: transparent;
        border-radius: 0 50px 50px 0;
      }
    }
    button {
      border: none;
      outline: none;
      background: transparent;
      font-size: 1.5rem;
      cursor: pointer;
    }
  }
  .links {
    display: flex;
    align-items: center;
    height: 24px;

    .link_icon {
      cursor: pointer;
      display: flex;
      align-items: center;
      justify-content: center;
      width: 24px;
      height: 24px;
      margin-right: 24px;
    }
  }
`;

const Divider = styled.div`
  height: 100%;
  width: 1px;
  margin: 0 0 0 32px;
  &.light {
    background: #dddfe5;
  }
  &.dark {
    background: #3c4752;
  }
`;

const Profile = styled.div`
  display: flex;
  align-items: center;

  .profile_name {
    cursor: pointer;
    font-size: 14px;
    font-weight: 600;
    margin-right: 24px;
    letter-spacing: 0.4px;
    font-family: "Poppins", sans-serif;

    &.light {
      color: #252733;
    }
    &.dark {
      color: #fff;
    }
    &:hover {
      text-decoration: underline;
    }
  }
  .profile_img {
    cursor: pointer;
    width: 40px;
    height: 40px;
    border-radius: 50%;
    box-shadow: 0 0 3px rgba(#161c24, 0.3);
    .img {
      border-radius: 50%;
      width: 100%;
      height: auto;
    }
  }
`;

const CustomizedAvatar = muiStyled(Avatar)`
  cursor: pointer;
`;

export { HeaderWrapper, Title, Content, Divider, Profile, CustomizedAvatar };
