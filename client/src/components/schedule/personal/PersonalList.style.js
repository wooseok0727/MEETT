import styled from "styled-components";

const TableWrapper = styled.table`
  width: 100%;
  border-collapse: collapse;

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
    height: 280px;
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
    width: 100%;
    font-size: 14px;
    font-weight: 700;
    letter-spacing: 0.4px;
    line-height: 16px;
    padding: 16px 24px;
  }
  th:last-child {
    margin-right: 15px;
  }
  td {
    font-weight: 400;
    white-space: nowrap;
  }
`;

export { TableWrapper };
