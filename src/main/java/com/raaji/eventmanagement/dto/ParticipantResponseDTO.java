package com.raaji.eventmanagement.dto;

import lombok.Data;

@Data
public class ParticipantResponseDTO {

    private Long id;

    private String name;

    private String email;

    private String phoneNumber;

    private String eventName;
}