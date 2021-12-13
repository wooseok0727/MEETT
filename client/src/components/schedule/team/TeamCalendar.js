import React, { useRef } from "react";
import { enableRipple, extend } from "@syncfusion/ej2-base";
import {
  ScheduleComponent,
  ViewsDirective,
  ViewDirective,
  Month,
  Inject,
  Resize,
  DragAndDrop,
} from "@syncfusion/ej2-react-schedule";
import * as dataSource from "../../../lib/dataSource.json";
import { applyCategoryColor } from "../Helper";
enableRipple(true);

const PersonalCalendar = () => {
  const data = extend([], dataSource.zooEventsData, null, true);

  const scheduleRef = useRef();

  const onEventRendered = (args) => {
    applyCategoryColor(args, scheduleRef.currentView);
  };
  return (
    <div className="schedule-control-section">
      <div className="control-section">
        <div className="control-wrapper">
          <ScheduleComponent
            width="100%"
            height="650px"
            style={{ borderRadius: 12 }}
            ref={scheduleRef}
            selectedDate={new Date(2021, 1, 15)}
            eventSettings={{ dataSource: data }}
            eventRendered={onEventRendered}
          >
            <ViewsDirective>
              <ViewDirective option="Month" />
            </ViewsDirective>
            <Inject services={[Month, Resize, DragAndDrop]} />
          </ScheduleComponent>
        </div>
      </div>
    </div>
  );
};

export default PersonalCalendar;
