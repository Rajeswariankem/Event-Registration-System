package com.raaji.eventmanagement.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class EventRequestDTO {
    @NotBlank(message = "Event name is required")
    private String eventName;

    @NotBlank(message = "Location is required")
    private String location;

    @NotBlank(message = "Event date is required")
    private String eventDate;

    @Min(value = 1, message = "capacity must be greater than 0")
    private int capacity;
}
