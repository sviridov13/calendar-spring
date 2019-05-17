package com.example.calendar.service;

import com.example.calendar.dto.EventDataDto;
import com.example.calendar.models.Event;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface EventService {

    /**
     * Получает событие по идентификатору
     *
     * @param id идентификатор события
     * @return найденное событие
     */
    Event getEvent(long id);

    /**
     * Получает все события
     *
     * @return найденные события
     */
    List<Event> getAllEvents();

    /**
     * Создаем новое событие
     *
     * @param event данные события
     * @return созданное событие
     */
    Event createEvent(EventDataDto event);

    /**
     * Обновляем существующее событие
     *
     * @param event данные событие
     * @param id    ид события
     * @return измененое событие
     */
    Event updateEvent(EventDataDto event, long id);

    /**
     * Удаляем существующее событие
     *
     * @param id ид события
     * @return удаленное событие
     */
    Event deleteEvent(long id);

    /**
     * Получаем список событий на неделю
     *
     * @return список событий
     */
    List<Event> getEventsForNowWeek();

    /**
     * Получаем список событий на текущий день
     *
     * @return список событий
     */
    List<Event> getEventsForDay();

    /**
     * Получаем список событий в зада
     *
     * @return список событий
     */
    List<Event> getEventsForPeriod(LocalDateTime dateTime1, LocalDateTime dateTime2);

}
