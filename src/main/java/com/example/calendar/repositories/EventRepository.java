package com.example.calendar.repositories;

import com.example.calendar.entities.EventEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface EventRepository extends JpaRepository<EventEntity, Long> {
    List<EventEntity> findByStartBetweenOrEndBetween(LocalDateTime date, LocalDateTime date2, LocalDateTime date3, LocalDateTime date4);
}
