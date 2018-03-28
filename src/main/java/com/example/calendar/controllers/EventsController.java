package com.example.calendar.controllers;

import com.example.calendar.models.Event;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.time.Clock;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

@RestController
@RequestMapping(path = "/events", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class EventsController {

    @RequestMapping(method = RequestMethod.GET)
    public Event getEvent() {
        return new Event("Событие", "Описание события",
                LocalDateTime.now(Clock.systemUTC()), LocalDateTime.now(Clock.systemUTC()).plus(1, ChronoUnit.HOURS));
    }

}
