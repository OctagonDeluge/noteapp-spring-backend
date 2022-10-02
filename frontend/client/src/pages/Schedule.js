import React, {useState} from "react"
import ProfileSection from "../components/ProfileSection"
import "../assets/styles/Schedule.css"
import "../components/CalendarSection"
import CalendarSection from "../components/CalendarSection";
import Events from "../components/Events";

function Schedule({setAuth}) {
    const [events, setEvents] = useState([]);
    const [dateHeader, setDateHeader] = useState(new Date());
    const [value, setValue] = useState(new Date())
    const [schedules, setSchedules] = useState([]);
    return (
        <div className="main">
            <ProfileSection setAuth={setAuth}/>
            <Events events={events} setEvents={setEvents}
                    dateHeader={dateHeader} setDateHeader={setDateHeader}
                    value={value} setValue={setValue}
                    schedules={schedules} setSchedules={setSchedules}/>
            <CalendarSection events={events} setEvents={setEvents}
                             dateHeader={dateHeader} setDateHeader={setDateHeader}
                             value={value} setValue={setValue}
                             schedules={schedules} setSchedules={setSchedules}/>
        </div>
    )
}

export default Schedule;