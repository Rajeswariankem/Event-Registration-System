package com.raaji.eventmanagement.controller;

import com.raaji.eventmanagement.dto.ParticipantRequestDTO;
import com.raaji.eventmanagement.dto.ParticipantResponseDTO;
import com.raaji.eventmanagement.service.ParticipantService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class ParticipantController {

    private final ParticipantService participantService;

    @PostMapping("/events/{eventId}/participants")
    public ParticipantResponseDTO registerParticipant(
            @PathVariable Long eventId,
            @Valid @RequestBody ParticipantRequestDTO dto) {

        return participantService.registerParticipant(eventId, dto);
    }

    @GetMapping("/events/{eventId}/participants")
    public List<ParticipantResponseDTO>
    getParticipantsByEvent(
            @PathVariable Long eventId) {

        return participantService
                .getParticipantsByEvent(eventId);
    }
}