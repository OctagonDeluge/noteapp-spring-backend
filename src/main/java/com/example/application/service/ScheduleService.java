package com.example.application.service;

import com.example.application.entity.Event;
import com.example.application.entity.Schedule;
import com.example.application.repository.ScheduleRepository;
import com.example.application.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;
/*
* delete
* update
* */
@Service
public class ScheduleService {

    private final ScheduleRepository scheduleRepository;
    private final EventRepository eventRepository;

    @Autowired
    public ScheduleService(ScheduleRepository scheduleRepository, EventRepository eventRepository) {
        this.scheduleRepository = scheduleRepository;
        this.eventRepository = eventRepository;
    }

    public List<Schedule> getSchedulesForMonth(String beginOfMonth, String endOfMonth) {
        return scheduleRepository.findAllByMonth(
                new Date(Long.parseLong(beginOfMonth)),
                new Date(Long.parseLong(endOfMonth))
        );
    }

    public List<Event> getTodayEvent(String currentDate) {
        Schedule schedule =
                scheduleRepository.findByDate(new Date(Long.parseLong(currentDate)));
        return eventRepository.findByScheduleId(schedule.getId());
    }

    public void addEvent(String date, String content) {
        Schedule schedule =
                scheduleRepository.findByDate(new Date(Long.parseLong(date)));
        if(schedule != null) {
            Event event = new Event();
            event.setSchedule(schedule);
            event.setContent(content);
            eventRepository.save(event);
        } else {
            schedule = new Schedule();
            schedule.setEvent_date(new Date(Long.parseLong(date)));
            scheduleRepository.save(schedule);
            Event event = new Event();
            event.setSchedule(schedule);
            event.setContent(content);
            eventRepository.save(event);
        }
    }

    public void updateEvent(Long id, String content) {
        Event event = eventRepository.findById(id).get();
        event.setContent(content);
        eventRepository.save(event);
    }

    public void deleteEvent(Long id) {
        Event event = eventRepository.findById(id).get();
        eventRepository.delete(event);
    }
}
