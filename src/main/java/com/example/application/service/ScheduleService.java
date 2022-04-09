package com.example.application.service;

import com.example.application.entity.Event;
import com.example.application.entity.Schedule;
import com.example.application.repository.ScheduleRepository;
import com.example.application.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.net.URISyntaxException;
import java.sql.Date;
import java.util.ArrayList;
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

    public List<Long> getSchedulesForMonth(String beginOfMonth, String endOfMonth) {
        List<Schedule> schedules = scheduleRepository.findAllByMonth(
                new Date(Long.parseLong(beginOfMonth)),
                new Date(Long.parseLong(endOfMonth))
        );

        List<Long> timestamps = new ArrayList<>();
        for (Schedule schedule:
             schedules) {
            timestamps.add(schedule.getEvent_date().getTime());
        }

        return timestamps;
    }

    public List<Event> getTodayEvent(String currentDate) {
        Schedule schedule =
                scheduleRepository.findByDate(new Date(Long.parseLong(currentDate)));
        return eventRepository.findByScheduleId(schedule.getId());
    }

    public ResponseEntity<Event> addEvent(String date, String content) throws URISyntaxException {
        Schedule schedule =
                scheduleRepository.findByDate(new Date(Long.parseLong(date)));
        if(schedule != null) {
            Event event = new Event();
            event.setSchedule(schedule);
            event.setContent(content);
            eventRepository.save(event);
            return ResponseEntity.created(new URI("/calendar/"+date)).body(event);
        } else {
            schedule = new Schedule();
            schedule.setEvent_date(new Date(Long.parseLong(date)));
            scheduleRepository.save(schedule);
            Event event = new Event();
            event.setSchedule(schedule);
            event.setContent(content);
            eventRepository.save(event);
            return ResponseEntity.created(new URI("/calendar/"+date)).body(event);
        }
    }

    public void updateEvent(Long id, String content) {
        Event event = eventRepository.findById(id).get();
        event.setContent(content);
        eventRepository.save(event);
    }

    public void deleteSchedule(String date) {
        Schedule schedule = scheduleRepository.findByDate(new Date(Long.parseLong(date)));
        scheduleRepository.delete(schedule);
    }
}
