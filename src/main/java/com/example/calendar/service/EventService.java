package com.example.calendar.service;

import com.example.calendar.dto.EventDataDto;
import com.example.calendar.models.Event;

public interface EventService {

    /**
     * Получает событие по идентификатору
     *
     * @param id идентификатор события
     * @return найденное событие
     */
    Event getEvent(long id);
    /**
     * Создаем новое событие
     *
     * @param event данные события
     * @return созданное событие
     */
    Event createEvent(EventDataDto event);

}
