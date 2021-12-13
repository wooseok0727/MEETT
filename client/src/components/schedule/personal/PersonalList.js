import React from "react";
import { useSelector } from "react-redux";
import { TableWrapper } from "./PersonalList.style";

const scheduleData = [
  {
    id: 1,
    title: "스케쥴1",
    detail: " 디테일입니다1",
    start: "2021-01-01",
    end: "2021-12-31",
    role: "개인",
  },
  {
    id: 2,
    title: "스케쥴2",
    detail: " 디테일입니다2",
    start: "2021-01-01",
    end: "2021-12-31",
    role: "개인",
  },
  {
    id: 3,
    title: "스케쥴3",
    detail: " 디테일입니다3",
    start: "2021-01-01",
    end: "2021-12-31",
    role: "팀",
  },
  {
    id: 4,
    title: "스케쥴4",
    detail: " 디테일입니다4",
    start: "2021-01-01",
    end: "2021-12-31",
    role: "개인",
  },
  {
    id: 5,
    title: "스케쥴5",
    detail: " 디테일입니다5",
    start: "2021-01-01",
    end: "2021-12-31",
    role: "개인",
  },
  {
    id: 6,
    title: "스케쥴6",
    detail: " 디테일입니다6",
    start: "2021-01-01",
    end: "2021-12-31",
    role: "개인",
  },
  {
    id: 7,
    title: "스케쥴7",
    detail: " 디테일입니다7",
    start: "2021-01-01",
    end: "2021-12-31",
    role: "팀",
  },
  {
    id: 8,
    title: "스케쥴8",
    detail: " 디테일입니다8",
    start: "2021-01-01",
    end: "2021-12-31",
    role: "개인",
  },
];

const PersonalList = () => {
  const { theme } = useSelector((state) => state.theme);
  const themeClass = theme === "light" ? "light" : "dark";
  return (
    <TableWrapper className={themeClass}>
      <thead>
        <tr className={`head ${themeClass}`}>
          <th>TITLE</th>
          <th>DETAIL</th>
          <th>START</th>
          <th>END</th>
          <th>ROLE</th>
        </tr>
      </thead>
      <tbody className={themeClass}>
        {scheduleData.map(({ id, title, detail, start, end, role }) => (
          <tr key={id}>
            <td>
              <span>{title}</span>
            </td>
            <td>{detail}</td>
            <td>{start}</td>
            <td>{end}</td>
            <td>{role}</td>
          </tr>
        ))}
      </tbody>
    </TableWrapper>
  );
};

export default PersonalList;
