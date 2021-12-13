import React, { useState } from "react";
import {
  ContainerWrapper,
  Divider,
  Logo,
  LogoTitle,
  Menu,
  Sidebar,
  ThemeToggle,
} from "./SideBar.style";
import ManageAccountsIcon from "@mui/icons-material/ManageAccounts";
import SettingsIcon from "@mui/icons-material/Settings";
import EventNoteIcon from "@mui/icons-material/EventNote";
import EventIcon from "@mui/icons-material/Event";
import { Link } from "react-router-dom";
import ToggoleSwitch from "../../lib/ToggoleSwitch";
import { useSelector } from "react-redux";

const initialNavList = [
  {
    id: 0,
    text: "TEAM SCHEDULE",
    link: "/team",
    isActive: true,
    icon: (color) => <EventNoteIcon sx={{ fontSize: 24, fill: `${color}` }} />,
  },
  {
    id: 1,
    text: "MY SCHEDULE",
    link: "/my",
    isActive: false,
    icon: (color) => <EventIcon sx={{ fontSize: 24, fill: `${color}` }} />,
  },
  {
    id: 2,
    text: "MY PAGE",
    link: "/mypage",
    isActive: false,
    icon: (color) => (
      <ManageAccountsIcon sx={{ fontSize: 24, fill: `${color}` }} />
    ),
  },
  {
    id: 3,
    text: "SETTINGS",
    link: "/settings",
    isActive: false,
    icon: (color) => <SettingsIcon sx={{ fontSize: 24, fill: `${color}` }} />,
  },
];

const SideBar = () => {
  const [navList, setNavList] = useState(initialNavList);
  const { theme, themeText } = useSelector((state) => state.theme);
  const themeClass = theme === "light" ? "light" : "dark";
  const isActiveHandler = (id) => {
    const arr = [...navList];
    const idxOfActive = arr.find((item) => item.isActive === true).id;
    arr[idxOfActive].isActive = false;
    arr[id].isActive = true;
    setNavList(arr);
  };

  return (
    <ContainerWrapper className={themeClass}>
      <Sidebar>
        <Logo>
          <LogoTitle className={themeClass}>
            MEET<span>T</span>
          </LogoTitle>
        </Logo>
        <Menu>
          {initialNavList.map(({ id, text, link, isActive, icon }) => (
            <li key={id} className={isActive ? "isActive" : ""}>
              <Link
                className="link"
                to={link}
                onClick={() => isActiveHandler(id)}
              >
                <div className="icon">
                  {icon(
                    isActive
                      ? "#7380ec"
                      : theme === "light"
                      ? "#929292"
                      : "#fff"
                  )}
                </div>
                <span className={`text ${themeClass}`}>{text}</span>
              </Link>
            </li>
          ))}
        </Menu>
        <Divider className={themeClass} />
        <ThemeToggle>
          <ToggoleSwitch />
          <span className={`title ${themeClass}`}>{themeText}</span>
        </ThemeToggle>
      </Sidebar>
    </ContainerWrapper>
  );
};

export default SideBar;
