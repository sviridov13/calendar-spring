package com.example.calendar.utils;

import com.example.calendar.entities.EventEntity;
import com.example.calendar.models.Event;
import lombok.experimental.UtilityClass;

@UtilityClass
public class EntityModelMapper {

    public static Event toEvent(EventEntity eventEntity) {
        return Event.builder()
                .id(eventEntity.getId())
                .name(eventEntity.getName())
                .description(eventEntity.getDescription())
                .start(eventEntity.getStart())
                .end(eventEntity.getEnd())
                .build();
    }
}
