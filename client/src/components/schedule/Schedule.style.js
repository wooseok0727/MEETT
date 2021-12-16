import styled from "styled-components";

const ScrollWrapper = styled.div`
  position: relative;
  height: 100vh;
  overflow: auto;
  z-index: 0;
  overflow-x: hidden;
  width: 100%;
  &.light {
    background: #fafafa;
  }

  &.dark {
    background: #161c24;
  }

  &::-webkit-scrollbar {
    width: 8px;
  }
  &.light::-webkit-scrollbar-track {
    background: #fff;
  }
  &.dark::-webkit-scrollbar-track {
    background: #212b36;
  }
  &::-webkit-scrollbar-thumb {
    border-radius: 12px;
  }
  &.light::-webkit-scrollbar-thumb {
    background: #dddfe5;
  }
  &.dark::-webkit-scrollbar-thumb {
    background: #3c4752;
  }

  ::-webkit-scrollbar {
    width: 8px;
  }
  ::-webkit-scrollbar-thumb {
    border-radius: 12px;
  }

  .light {
    ::-webkit-scrollbar-track {
      background: #fff;
    }
    ::-webkit-scrollbar-thumb {
      background: #dddfe5;
    }
  }
  .dark {
    ::-webkit-scrollbar-track {
      background: #212b36;
    }
    ::-webkit-scrollbar-thumb {
      background: #3c4752;
    }
  }
`;

const ContainerWrapper = styled.div`
  margin-left: 22.225rem;
  width: calc(100% - 22.225rem);
  height: 100%;
  position: absolute;
  top: 0;
  left: 0;
  padding: 48px 120px;

  &.light {
    background: #fafafa;
  }

  &.dark {
    background: #161c24;
  }
`;

const SchedulerWrapper = styled.div`
  width: 100%;
  height: 100%;
  display: flex;
  flex-direction: column;

  .content {
    display: flex;
    flex-direction: column;
    align-items: center;
    margin-top: 40px;
  }
`;

export { ScrollWrapper, ContainerWrapper, SchedulerWrapper };
