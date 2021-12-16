import React, { useEffect } from "react";
import { useSelector, useDispatch } from "react-redux";
import { TableWrapper } from "../commons/Table.style";
import { list } from "../../../actions/team";
import { useLocation } from "react-router-dom";
import qs from "qs";

const RoomItem = ({ team, index }) => {
  const { title, username } = team;
  return (
    <tr>
      <td>
        <span>{index + 1}</span>
      </td>
      <td>{title}</td>
      <td>{username}</td>
      <td>Button</td>
    </tr>
  );
};

const RoomList = () => {
  const { theme } = useSelector((state) => state.theme);
  const { teams, listDone, listError } = useSelector((state) => state.team);
  console.log(teams);

  const themeClass = theme === "light" ? "light" : "dark";

  const dispatch = useDispatch();
  const location = useLocation();

  console.log(location);

  useEffect(() => {
    const { title } = qs.parse(location.search, {
      ignoreQueryPrefix: true,
    });
    dispatch(list({ title }));
  }, [dispatch, location.search]);

  return (
    <TableWrapper className={themeClass} room>
      <thead>
        <tr className={`head ${themeClass}`}>
          <th>NO.</th>
          <th>TITLE</th>
          <th>MASTER</th>
          <th></th>
        </tr>
      </thead>
      {listDone && teams && (
        <tbody className={themeClass}>
          {teams.map((team, index) => (
            <RoomItem team={team} index={index} key={team.teamId} />
          ))}
        </tbody>
      )}
    </TableWrapper>
  );
};

export default RoomList;
