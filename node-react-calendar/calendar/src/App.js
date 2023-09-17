import React, { Component } from "react";
import FullCalendar from "@fullcalendar/react";
import dddayGridPlugin from "@fullcalendar/daygrid";
import timeGridPlugin from "@fullcalendar/timegrid";
import interactionPlugin from "@fullcalendar/interaction";
import "bootstrap/dist/css/bootstrap.css";
import "@fortawesome/fontawesome-free/css/all.css"; // needs additional webpack config!
import "bootstrap/dist/css/bootstrap.min.css";
import Modal from "react-bootstrap/Modal";
import axios from "axios";
import "./App.css";
import "bootstrap/dist/css/bootstrap.min.css";

class App extends Component {
  state = {
    showModal: false, // 2. Modal 표시 상태를 관리하는 state 변수
    selectedEvent: null, // 클릭한 이벤트 데이터를 저장하기 위한 변수
  };

  dateClick = (info) => {
    console.log(info.dateStr);
  };

  eventClick = (eventInfo) => {
    const extendedProps = eventInfo.event.extendedProps.todo;
    // 빈 객체를 만듭니다.
    const keyValueObject = {};
    const customKey = "작성자";
    keyValueObject[customKey] = extendedProps[0];
    const customKey2 = "일정";
    keyValueObject[customKey2] = extendedProps[1];
    const customKey3 = "장소";
    keyValueObject[customKey3] = extendedProps[2];
    const customKey4 = "시작일";
    keyValueObject[customKey4] = extendedProps[3];
    const customKey5 = "종료일";
    keyValueObject[customKey5] = extendedProps[4];
    ////////////////////////////////////////////////////////////////////////
    // keyValueObject를 문자열로 변환하여 `\n`으로 구분한 후 selectedEvent
    const info = Object.entries(keyValueObject) // keyValueObject 객체를 배열 형태로 변환
      .map(([key, value]) => `${key}: ${value}`) // 배열의 각 요소를 key-value 형태의 문자열로 변환
      .join("\n"); // 배열의 요소들을 `\n`으로 구분

    // Modal 표시 상태를 true로 설정하고, 클릭한 이벤트에 대한 정보를 selectedEvent에 넣습니다.
    this.setState({
      showModal: true, // Modal을 표시하기 위해 showModal을 true로 설정합니다.
      selectedEvent: info, // 클릭한 이벤트의 상세 정보를 selectedEvent에 할당합니다.
    });
  };

  closeModal = () => {
    this.setState({
      showModal: false, // Modal 숨김
      selectedEvent: null, // 클릭한 이벤트 데이터 초기화
    });
  };

  // 아래의 모든 과정은 componentDidMount에 의해, 컴포넌트가 만들어지고 첫 렌더링을 모두 끝낸 후 실행됨
  componentDidMount() {
    this._getEvents();
  }

  // _axiosEvents이 응답을 받을때까지 기다리고, 응답을 받는다면 setState 메소드를 호출하여 state 값에 events라는 데이터를 넣어줌
  _getEvents = async () => {
    const events = await this._axiosEvents();
    this.setState({
      events,
    });
    console.log("events >>>> ", events);
  };

  _axiosEvents = async () => {
    return axios
      .post("http://localhost:3002/calendarload")
      .then((res) => res.data);
  };

  render() {
    let { events } = this.state;

    return (
      <div className="App">
        {events ? (
          <FullCalendar
            initialView="dayGridMonth"
            plugins={[
              dddayGridPlugin,
              timeGridPlugin,
              interactionPlugin,
              //bootstrapPlugin,
            ]}
            select={this.handleDateSelect}
            events={this.state.events}
            themeSystem="bootstrap"
            editable="true"
            eventClick={this.eventClick}
            dateClick={this.dateClick}
            headerToolbar={{
              start: "today,dayGridMonth,dayGridWeek,dayGridDay",
              center: "title",
              end: "prev,next",
            }}
          />
        ) : (
          "loading ... "
        )}

        {/* 이벤트 정보를 표시하는 Modal */}
        <Modal show={this.state.showModal} onHide={this.closeModal}>
          <Modal.Header closeButton>
            <Modal.Title>상세일정</Modal.Title>
          </Modal.Header>
          <Modal.Body>
            {this.state.selectedEvent &&
              this.state.selectedEvent.split("\n").map((item, index) => {
                const keyValuePairs = item.split(": ");
                const key = keyValuePairs[0];
                const value = keyValuePairs[1];

                return (
                  <div key={index} className="event-details">
                    <div className="event-detail-item">
                      <strong>{key}</strong>
                    </div>
                    <div className="event-detail-item event-detail-value">
                      {value}
                    </div>
                  </div>
                );
              })}
          </Modal.Body>
        </Modal>
      </div>
    );
  }
}

export default App;

/*
리액트 노트 오라클 

 npm install oracledb --save -> 오라클디비 노드 연결하기 위해 설치
*/
