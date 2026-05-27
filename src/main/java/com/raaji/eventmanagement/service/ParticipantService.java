package com.raaji.eventmanagement.service;

import com.raaji.eventmanagement.dto.ParticipantRequestDTO;
import com.raaji.eventmanagement.dto.ParticipantResponseDTO;
import com.raaji.eventmanagement.entity.Event;
import com.raaji.eventmanagement.entity.Participant;
import com.raaji.eventmanagement.exception.EventNotFoundException;
import com.raaji.eventmanagement.repository.EventRepository;
import com.raaji.eventmanagement.repository.ParticipantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;
import com.raaji.eventmanagement.exception
        .EventCapacityFullException;
import com.raaji.eventmanagement.exception
        .DuplicateRegistrationException;

@Service
@RequiredArgsConstructor
public class ParticipantService {

    private final ParticipantRepository participantRepository;
    private final EventRepository eventRepository;
    private final EmailService emailService;

    public ParticipantResponseDTO registerParticipant(
            Long eventId,
            ParticipantRequestDTO dto) {

        Event event = eventRepository.findById(eventId)
                .orElseThrow(() ->
                        new EventNotFoundException(
                                "Event not found with id: " + eventId));
        long participantCount =
                participantRepository.countByEventId(eventId);

        if (participantCount >= event.getCapacity()) {

            throw new EventCapacityFullException(
                    "Event registration is full");
        }

        Participant participant = new Participant();

        participant.setName(dto.getName());
        participant.setEmail(dto.getEmail());
        participant.setPhoneNumber(dto.getPhoneNumber());

        participant.setEvent(event);

        boolean alreadyRegistered =
                participantRepository
                        .existsByEmailAndEventId(
                                dto.getEmail(),
                                eventId);

        if (alreadyRegistered) {

            throw new DuplicateRegistrationException(
                    "Participant already registered for this event");
        }

        Participant savedParticipant =
                participantRepository.save(participant);

        emailService.sendRegistrationEmail(
                savedParticipant.getEmail(),
                savedParticipant.getName(),
                savedParticipant.getEvent().getEventName()
        );

        ParticipantResponseDTO responseDTO =
                new ParticipantResponseDTO();

        responseDTO.setId(savedParticipant.getId());
        responseDTO.setName(savedParticipant.getName());
        responseDTO.setEmail(savedParticipant.getEmail());
        responseDTO.setPhoneNumber(savedParticipant.getPhoneNumber());
        responseDTO.setEventName(
                savedParticipant.getEvent().getEventName());

        return responseDTO;
    }

    public List<ParticipantResponseDTO>
    getParticipantsByEvent(Long eventId) {

        List<Participant> participants =
                participantRepository.findByEventId(eventId);

        return participants.stream().map(participant -> {

            ParticipantResponseDTO dto =
                    new ParticipantResponseDTO();

            dto.setId(participant.getId());
            dto.setName(participant.getName());
            dto.setEmail(participant.getEmail());
            dto.setPhoneNumber(
                    participant.getPhoneNumber());

            dto.setEventName(
                    participant.getEvent().getEventName());

            return dto;

        }).collect(Collectors.toList());
    }
}