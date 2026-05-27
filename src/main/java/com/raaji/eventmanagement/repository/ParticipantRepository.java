package com.raaji.eventmanagement.repository;

import com.raaji.eventmanagement.entity.Participant;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ParticipantRepository
        extends JpaRepository<Participant, Long> {
    List<Participant> findByEventId(Long eventId);
    long countByEventId(Long eventId);
    boolean existsByEmailAndEventId(
            String email,
            Long eventId);
}