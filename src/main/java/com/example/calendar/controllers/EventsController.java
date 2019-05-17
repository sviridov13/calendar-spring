package com.example.calendar.controllers;

import com.example.calendar.dto.EventDataDto;
import com.example.calendar.models.Event;
import com.example.calendar.service.EventService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@RestController
@Api(tags = "events")
@RequestMapping(path = "/events", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class EventsController {

    private final EventService eventService;

    @Autowired
    public EventsController(EventService eventService) {
        this.eventService = eventService;
    }

    @ApiOperation(
            value = "Получить событие по id",
            notes = "Получает событие по id")
    @RequestMapping(path = "/{id}", method = RequestMethod.GET)
    public Event getEvent(@PathVariable("id") long id) {
        return eventService.getEvent(id);
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<Event> getAllEvents() {
        return eventService.getAllEvents();
    }

    @RequestMapping(method = RequestMethod.POST)
    public Event createEvent(@RequestBody EventDataDto event) {
        return eventService.createEvent(event);
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.PUT)
    public Event updateEvent(@RequestBody EventDataDto event, @PathVariable("id") long id) {
        return eventService.updateEvent(event, id);
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.DELETE)
    public Event updateEvent(@PathVariable("id") long id) {
        return eventService.deleteEvent(id);
    }

    @RequestMapping(path = "/week", method = RequestMethod.GET)
    public List<Event> getEventsForNowWeek() {
        return eventService.getEventsForNowWeek();
    }

    @RequestMapping(path = "/day", method = RequestMethod.GET)
    public List<Event> getEventsForDay() {
        return eventService.getEventsForDay();
    }

    @RequestMapping(path = "/period", method = RequestMethod.GET)
    public List<Event> getEventsForPeriod(@RequestParam("start") String start, @RequestParam("end") String end) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
        LocalDateTime startDate = LocalDateTime.parse(start, formatter);
        LocalDateTime endDate = LocalDateTime.parse(end, formatter);
        return eventService.getEventsForPeriod(startDate, endDate);
    }
}
