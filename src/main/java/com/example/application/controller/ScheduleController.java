package com.example.application.controller;

import com.example.application.entity.Event;
import com.example.application.entity.Schedule;
import com.example.application.service.ScheduleService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/calendar")
public class ScheduleController {

    private final ScheduleService scheduleService;

    public ScheduleController(ScheduleService scheduleService) {
        this.scheduleService = scheduleService;
    }

    @GetMapping
    public List<Schedule> getSchedules(@RequestParam String beginOfMonth, @RequestParam String endOfMonth) {
        return scheduleService.getSchedulesForMonth(beginOfMonth, endOfMonth);
    }

    @GetMapping("/{currentDate}")
    public List<Event> getTodayEvents(@PathVariable String currentDate) {
        return scheduleService.getTodayEvent(currentDate);
    }

    @PostMapping("")
    public ResponseEntity test(@RequestParam String date, @RequestParam String content) {
        scheduleService.addEvent(date, content);
        return ResponseEntity.ok("dw");
    }
}

