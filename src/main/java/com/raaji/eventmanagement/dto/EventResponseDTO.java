package com.raaji.eventmanagement.dto;

import lombok.Data;

@Data
public class EventResponseDTO {
    private Long id;
    private String eventName;
    private String location;
    private String eventDate;
    private int capacity;
}
