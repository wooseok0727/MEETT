import styled, { css } from "styled-components";

const TableWrapper = styled.table`
  width: 100%;
  border-collapse: collapse;
  font-family: "Poppins", sans-serif;

  &.light {
    th,
    td {
      color: #252733;
    }
    td {
      border-bottom: 1px solid #dddfe5;
    }
  }
  &.dark {
    th,
    td {
      color: #fff;
    }
    td {
      border-bottom: 1px solid #3c4752;
    }
  }

  .head {
    border-radius: 12px;

    &.light {
      background: #fafafa;
    }
    &.dark {
      background: #26323f;
    }
  }
  thead tr {
    display: flex;
    text-align: left;
  }
  tbody {
    display: block;
    overflow: auto;
    height: ${(props) => (props.room ? "580px" : "280px")};
    ::-webkit-scrollbar {
      width: 8px;
    }
    .light::-webkit-scrollbar-track {
      background: #fff;
    }
    .dark::-webkit-scrollbar-track {
      background: #212b36;
    }
    ::-webkit-scrollbar-thumb {
      border-radius: 12px;
    }
    .light::-webkit-scrollbar-thumb {
      background: #dddfe5;
    }
    .dark::-webkit-scrollbar-thumb {
      background: #3c4752;
    }
  }
  tbody tr {
    display: flex;
    text-align: left;
  }
  th,
  td {
    ${(props) =>
      props.room
        ? css`
            text-align: center;
          `
        : css`
            width: 100%;
          `}
    text-align: left;
    font-size: 14px;
    font-weight: 700;
    letter-spacing: 0.4px;
    line-height: 16px;
    padding: 16px 24px;
  }
  ${(props) =>
    props.room &&
    css`
      td:nth-of-type(1),
      th:nth-of-type(1) {
        flex: 2;
      }
      td:nth-of-type(2),
      th:nth-of-type(2) {
        flex: 6;
      }
      td:nth-of-type(3),
      th:nth-of-type(3) {
        flex: 3;
      }
      td:nth-of-type(4),
      th:nth-of-type(4) {
        flex: 1;
      }
    `}

  th:last-child {
    margin-right: 15px;
  }
  td {
    font-weight: 400;
    white-space: nowrap;
  }
`;

export { TableWrapper };
