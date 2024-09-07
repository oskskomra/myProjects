package osk.sko.WebCalendar.event.service;

import org.springframework.stereotype.Service;
import osk.sko.WebCalendar.event.advice.NoEventExists;
import osk.sko.WebCalendar.event.advice.NoEventFoundException;
import osk.sko.WebCalendar.event.model.Event;
import osk.sko.WebCalendar.event.repository.EventRepository;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;


@Service
public class EventService {

    private final EventRepository eventRepository;

    public EventService(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    public Map<String, Object> addEvent(Event event) {
        String date = String.valueOf(event.getDate());
        Map<String, Object> response = new HashMap<>();


            if (event.getEvent() != null && date.matches("\\d{4}-\\d{2}-\\d{2}") && !event.getEvent().trim().isEmpty()) {
                eventRepository.save(event);
                response.put("message", "The event has been added!");
                response.put("event", event.getEvent());
                response.put("date", event.getDate());
                return response;
            } else {
            throw new RuntimeException();
        }
    }

    public List<Event> returnEvents() {
        if (eventRepository.findAll().isEmpty()) {
            throw new NoEventFoundException();
        } else {
            return eventRepository.findAll();
        }
    }

    public List<Event> returnTodaysEvent() {
       return eventRepository.findAllByDate(LocalDate.now());
    }

    public Event returnEventById(long id) {
        Optional<Event> event = eventRepository.findById(id);
        return event.orElseThrow(NoEventExists::new);
    }

    public Event deleteEventById(long id) {
        Optional<Event> event = eventRepository.findById(id);
        if(eventRepository.findById(id).isPresent()) {
            eventRepository.deleteById(id);
            return event.orElseThrow(NoEventExists::new);
        } else {
            throw new NoEventExists();
        }
    }

    public List<Event> returnEventsByDate(LocalDate start_time, LocalDate end_time) {
        List<Event> events = eventRepository.findAll();

        if(start_time != null && end_time != null) {
            events.removeIf(event -> event.getDate().isAfter(end_time) && event.getDate().isBefore(start_time));
        } else if(!events.isEmpty()) {
            return events;
        }
        throw new NoEventFoundException();
    }


}
