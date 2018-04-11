package com.example.calendar.service.impl;

import com.example.calendar.dto.EventDataDto;
import com.example.calendar.entities.EventEntity;
import com.example.calendar.models.Event;
import com.example.calendar.repositories.EventRepository;
import com.example.calendar.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Clock;
import java.time.LocalDateTime;

@Service
public class EventServiceImpl implements EventService {

    @Autowired
    private EventRepository repository;

    @Override
    @Transactional(readOnly = true)
    public Event getEvent(long id) {
        EventEntity entity = repository.findOne(id);
        return Event.builder()
                .id(entity.getId())
                .name(entity.getName())
                .description(entity.getDescription())
                .start(entity.getStart())
                .end(entity.getEnd())
                .build();
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

        return Event.builder()
                .id(entity.getId())
                .name(entity.getName())
                .description(entity.getDescription())
                .start(entity.getStart())
                .end(entity.getEnd())
                .build();
    }
}
