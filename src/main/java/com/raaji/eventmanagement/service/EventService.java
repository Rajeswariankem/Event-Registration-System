package com.raaji.eventmanagement.service;

import com.raaji.eventmanagement.entity.Event;
import com.raaji.eventmanagement.exception.EventNotFoundException;
import com.raaji.eventmanagement.repository.EventRepository;
import org.springframework.stereotype.Service;
import com.raaji.eventmanagement.dto.EventRequestDTO;
import com.raaji.eventmanagement.dto.EventResponseDTO;
import java.util.List;

@Service
public class EventService {
    private final EventRepository eventRepository;
    public  EventService(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }
    public EventResponseDTO createEvent(EventRequestDTO dto) {
        Event event = new Event();
        event.setEventName(dto.getEventName());
        event.setLocation(dto.getLocation());
        event.setCapacity(dto.getCapacity());
        event.setEventDate(dto.getEventDate());
        Event savedEvent = eventRepository.save(event);
        EventResponseDTO eventResponseDTO = new EventResponseDTO();
        eventResponseDTO.setId(savedEvent.getId());
        eventResponseDTO.setEventName(savedEvent.getEventName());
        eventResponseDTO.setLocation(savedEvent.getLocation());
        eventResponseDTO.setCapacity(savedEvent.getCapacity());
        eventResponseDTO.setEventDate(savedEvent.getEventDate());
        eventResponseDTO.setCapacity(savedEvent.getCapacity());
        return eventResponseDTO;

    }
    public List<Event> getAllEvents(){
        return eventRepository.findAll();
    }
    public Event getEventById(int id){
        return eventRepository.findById(id).orElseThrow(()->new EventNotFoundException("Event not found with id "+id));
    }
    public Event updateEvent(int id,Event updtaedevent){
        Event existingEvent=eventRepository.findById(id).orElse(null);
        if(existingEvent!=null){
            existingEvent.setEventName(updtaedevent.getEventName());
            existingEvent.setLocation(updtaedevent.getLocation());
            existingEvent.setCapacity(updtaedevent.getCapacity());
            existingEvent.setEventDate(updtaedevent.getEventDate());

            return eventRepository.save(existingEvent);
        }
        return null;
    }

    public String deleteEventById(int id){
        Event existingEvent=eventRepository.findById(id).orElse(null);
        if(existingEvent!=null){
            eventRepository.delete(existingEvent);
            return "Event deleted successfully";
        }
        return "Event not found";
    }
}
