package com.example.calendar.controllers;

import com.example.calendar.dto.EventDataDto;
import com.example.calendar.models.Event;
import com.example.calendar.service.EventService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@Api(tags = "events")
@RequestMapping(path = "/events", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class EventsController {

    private final EventService eventService;

    @Autowired
    public EventsController(EventService eventService) {
        this.eventService = eventService;
    }

    @RequestMapping(path="/{id}", method = RequestMethod.GET)
    public Event getEvent(@PathVariable("id") long id) {
        return eventService.getEvent(id);
    }

    @RequestMapping(method = RequestMethod.POST)
    public Event createEvent(@RequestBody EventDataDto event) {
        return eventService.createEvent(event);
    }

}
