import React, {useState} from "react";
import {Center, Container, ScrollArea, Text} from "@mantine/core";
import axios from "axios";
import {showNotification} from "@mantine/notifications";

const months = {
    0: 'Январь',
    1: 'Февраль',
    2: 'Март',
    3: 'Апрель',
    4: 'Май',
    5: 'Июнь',
    6: 'Июль',
    7: 'Август',
    8: 'Сентябрь',
    9: 'Октябрь',
    10: 'Ноябрь',
    11: 'Декабрь'
}

const days = [
    'Воскресенье',
    'Понедельник',
    'Вторник',
    'Среда',
    'Четверг',
    'Пятница',
    'Суббота'
]

function Events({events, dateHeader, value, schedules, setEvents, setDateHeader, setValue, setSchedules}) {
    const [event, setEvent] = useState("");

    const clearEvents = () => {
        axios.delete('/calendar/' + Date.parse(value.toString()))
            .then(res => {
                console.log(schedules);
                let temp = [...schedules];
                const index = temp.indexOf(Date.parse(value.toString()));
                temp.splice(index, 1);
                console.log(temp);
                setSchedules(temp);
                setEvents([]);
            })
    }

    const addEvent = () => {
        axios.post('/calendar', {
            headers: {'Content-Type': 'application/json'}
        }, {
            params: {
                date: Date.parse(value.toString()),
                content: event.toString()
            }
        })
            .then(res => {
                setEvents([...events, res.data]);
                if (!schedules.includes(Date.parse(value.toString()))) {
                    setSchedules([...schedules, Date.parse(value.toString())]);
                }
                setEvent("");
            })
            .catch(res => {
                showNotification({
                    title: "Failed to save event",
                    message: "Can't save empty event",
                    color: "red"
                })
            })
    }

    return (
        <div className="events">
            <Container className="dateContainer">
                <Text className="dateHeader">
                    {days[dateHeader.getDay()]}
                </Text>
                <Text className="dateHeader">
                    {months[dateHeader.getMonth()] + ' ' + dateHeader.getDate()}
                </Text>
            </Container>
            <input type="text" value={event} onChange={(e) => setEvent(e.target.value)}/>
            <button className="add" onClick={addEvent}>+</button>
            <ScrollArea style={{height: '50%', marginTop: 20}}
                        offsetScrollbars scrollbarSize={8}>
                {events.map((event) => (
                    <div key={event.id} className="eventCard">
                        <Text>
                            {event.content}
                        </Text>
                    </div>
                ))}
            </ScrollArea>
            <Center>
                <button className="delete" onClick={clearEvents}>Очистить дату</button>
            </Center>
        </div>
    )
}

export default Events;
