package com.example.calendar.service.impl;

import com.example.calendar.dto.EventDataDto;
import com.example.calendar.entities.EventEntity;
import com.example.calendar.models.Event;
import com.example.calendar.repositories.EventRepository;
import com.example.calendar.service.EventService;
import com.example.calendar.utils.EntityModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Clock;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.TemporalAdjusters;
import java.time.temporal.WeekFields;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

@Service
public class EventServiceImpl implements EventService {

    private static final String DATE_FORMATTER = "yyyy-MM-dd";
    private static final String DATE_FORMATTER2 = "yyyy-MM-dd HH:mm";

    @Autowired
    private EventRepository repository;

    @Override
    @Transactional(readOnly = true)
    public Event getEvent(long id) {
        EventEntity entity = repository.findOne(id);
        return EntityModelMapper.toEvent(entity);
    }

    @Override
    @Transactional
    public Event createEvent(EventDataDto event) {
        EventEntity entity = new EventEntity();
        entity.setCreated(LocalDateTime.now(Clock.systemUTC()));
        entity.setName(event.getName());
        entity.setDescription(event.getDescription());
        entity.setStart(event.getStart());
        entity.setEnd(event.getEnd());
        repository.save(entity);

        return EntityModelMapper.toEvent(entity);
    }

    @Override
    @Transactional
    public List<Event> getAllEvents() {
        List<EventEntity> events = repository.findAll();
        List<Event> elements = events.stream().map(EntityModelMapper::toEvent).collect(Collectors.toList());
        return elements;
    }

    @Override
    @Transactional
    public Event updateEvent(EventDataDto eventDataDto, long id) {
        EventEntity event = repository.findOne(id);
        event.setCreated(LocalDateTime.now(Clock.systemUTC()));
        event.setDescription(eventDataDto.getDescription());
        event.setEnd(eventDataDto.getEnd());
        event.setName(eventDataDto.getName());
        event.setStart(eventDataDto.getStart());
        repository.save(event);
        return EntityModelMapper.toEvent(event);
    }

    @Override
    @Transactional
    public Event deleteEvent(long id) {
        EventEntity event = repository.findOne(id);
        repository.delete(id);
        return EntityModelMapper.toEvent(event);
    }

    @Override
    @Transactional
    public List<Event> getEventsForNowWeek() {
        LocalDateTime now = LocalDateTime.now();

        DayOfWeek firstDayOfWeek = WeekFields.of(Locale.getDefault()).getFirstDayOfWeek();

        DayOfWeek lastDayOfWeek = firstDayOfWeek.plus(6);
        LocalDateTime endOfWeek = now.with(TemporalAdjusters.nextOrSame(lastDayOfWeek));

        List<EventEntity> events = repository.findByStartBetweenOrEndBetween(LocalDateTime.now(), endOfWeek, LocalDateTime.now(), endOfWeek);

        List<Event> elements = events.stream().map(EntityModelMapper::toEvent).collect(Collectors.toList());

        return elements;
    }

    @Override
    @Transactional
    public List<Event> getEventsForDay() {
        LocalDate localDateTime = LocalDate.now();

        System.out.println(localDateTime.atStartOfDay());
        System.out.println( localDateTime.atStartOfDay().plusHours(23).plusMinutes(59).plusSeconds(59));
        List<EventEntity> events = repository.findByStartBetweenOrEndBetween(localDateTime.atStartOfDay(),
                localDateTime.atStartOfDay().plusHours(23).plusMinutes(59).plusSeconds(59), localDateTime.atStartOfDay(),
                localDateTime.atStartOfDay().plusHours(23).plusMinutes(59).plusSeconds(59));
        List<Event> elements = events.stream().map(EntityModelMapper::toEvent).collect(Collectors.toList());

        return elements;
    }

    @Override
    @Transactional
    public List<Event> getEventsForPeriod(LocalDateTime dateTime1, LocalDateTime dateTime2) {
        List<EventEntity> events = repository.findByStartBetweenOrEndBetween(dateTime1, dateTime2, dateTime1, dateTime2);
        List<Event> elements = events.stream().map(EntityModelMapper::toEvent).collect(Collectors.toList());
        return elements;
    }
}