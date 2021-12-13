import styled from "styled-components";

const ContainerWrapper = styled.div`
  width: 100%;
  min-width: 900px;
  border-radius: 12px;
  padding: 32px;
  margin-bottom: 32px;

  &.light {
    background: #fff;
    border: 1px solid #dddfe5;
  }
  &.dark {
    background: #212b36;
    border: 1px solid #3c4752;
  }
`;

const HeaderWrapper = styled.div`
  display: flex;
  margin-bottom: 24px;
  .title {
    font-size: 20px;
    font-weight: 700;
    letter-spacing: 0.4px;
    line-height: 25px;
    margin: 0;

    &.light {
      color: #252733;
    }
    &.dark {
      color: #fff;
    }
  }
`;

export { ContainerWrapper, HeaderWrapper };
