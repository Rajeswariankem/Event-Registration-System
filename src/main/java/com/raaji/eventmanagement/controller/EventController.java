package com.raaji.eventmanagement.controller;

import com.raaji.eventmanagement.dto.EventRequestDTO;
import com.raaji.eventmanagement.dto.EventResponseDTO;
import com.raaji.eventmanagement.service.EventService;
import com.raaji.eventmanagement.entity.Event;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/events")
public class EventController {
    private final EventService eventService;
    public EventController(EventService eventService) {
        this.eventService = eventService;
    }

    @PostMapping
    public EventResponseDTO createEvent(@Valid @RequestBody EventRequestDTO dto) {

        return eventService.createEvent(dto);
    }

    @GetMapping
    public List<Event> getAllEvents(){
        return eventService.getAllEvents();
    }

    @GetMapping("/{id}")
    public  Event getEventById(@PathVariable int id){
        return eventService.getEventById(id);
    }

    @PutMapping("/{id}")
    public Event updateEvent(@PathVariable int id,
                             @RequestBody Event event){
        return eventService.updateEvent(id, event);
    }

    @DeleteMapping("/{id}")
    public String deleteEventById(@PathVariable int id){
        return eventService.deleteEventById(id);
    }
}
