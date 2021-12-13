import React, { useEffect, useState } from "react";
import { useDispatch, useSelector } from "react-redux";
import {
  HeaderWrapper,
  Title,
  Content,
  Divider,
  Profile,
  CustomizedAvatar,
} from "./Header.style";
import ProfileIMG from "../../assets/profile.svg";
import SearchIcon from "@mui/icons-material/Search";
import AddCircleIcon from "@mui/icons-material/AddCircle";
import { AvatarGroup, Menu, MenuItem } from "@mui/material";
import { useNavigate } from "react-router-dom";
import { logOut } from "../../actions/user";

const Header = ({ title }) => {
  const { theme } = useSelector((state) => state.theme);
  const iconFill = theme === "light" ? "#929292" : "#fff";
  const themeClass = theme === "light" ? "light" : "dark";

  const [anchorEle, setAnchorEle] = useState(null);
  const { logoutError, logoutDone, user } = useSelector((state) => state.user);
  const dispatch = useDispatch();
  const navigate = useNavigate();

  const handleProfClick = (e) => {
    setAnchorEle(e.currentTarget);
  };

  const handleProfClose = () => {
    setAnchorEle(null);
  };

  const handleLogout = () => {
    dispatch(logOut());
    navigate("/", { replace: true });
  };

  return (
    <HeaderWrapper>
      <Title className={themeClass}>{title}</Title>
      <Content>
        <AvatarGroup max={4}>
          <CustomizedAvatar alt="" src={ProfileIMG} />
          <CustomizedAvatar alt="" src={ProfileIMG} />
          <CustomizedAvatar alt="" src={ProfileIMG} />
          <CustomizedAvatar alt="" src={ProfileIMG} />
        </AvatarGroup>
        <Divider className={themeClass} />
        <div className="search">
          <form className="search_form">
            <input
              className={`search_bar ${themeClass}`}
              placeholder="Search.."
            />
            <div className="search_btn">
              <button>
                <SearchIcon sx={{ fontSize: 24, fill: `${iconFill}` }} />
              </button>
            </div>
          </form>
        </div>

        <div className="links">
          <div className="link_icon">
            <AddCircleIcon sx={{ fontSize: 24, fill: `${iconFill}` }} />
          </div>
        </div>
        <Profile>
          <span className={`profile_name ${themeClass}`}>
            {user.nickname || "KOREA"}
          </span>
          <div className="profile_img" onClick={handleProfClick}>
            <CustomizedAvatar alt="" src={ProfileIMG} />
          </div>
        </Profile>
        <Menu
          style={{ marginLeft: "20px" }}
          anchorEl={anchorEle}
          keepMounted
          open={Boolean(anchorEle)}
          onClose={handleProfClose}
        >
          <MenuItem
            style={{ fontSize: 15, fontWeight: "bold" }}
            onClick={handleLogout}
          >
            Logout
          </MenuItem>
        </Menu>
      </Content>
    </HeaderWrapper>
  );
};

export default Header;
