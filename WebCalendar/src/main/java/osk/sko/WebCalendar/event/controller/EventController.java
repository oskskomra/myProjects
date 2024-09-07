package osk.sko.WebCalendar.event.controller;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import osk.sko.WebCalendar.event.model.Event;
import osk.sko.WebCalendar.event.service.EventService;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@RestController
public class EventController {

    private final EventService eventService;

    public EventController(EventService calendarService) {
        this.eventService = calendarService;
    }

    @PostMapping("/event")
    public ResponseEntity<Map<String, Object>> addEvent(@RequestBody @Valid Event calendar) {
        return ResponseEntity.ok(eventService.addEvent(calendar));
    }

    @GetMapping("/event")
    public ResponseEntity<List<Event>> returnEvents() {
        return ResponseEntity.ok(eventService.returnEvents());
    }

    @GetMapping("/event/today")
    public ResponseEntity<List<Event>> returnTodaysEvent() {
        return ResponseEntity.ok(eventService.returnTodaysEvent());
    }

    @GetMapping("/event/{id}")
    public ResponseEntity<Event> returnEventById(@PathVariable long id) {
        return ResponseEntity.ok(eventService.returnEventById(id));
    }

    @DeleteMapping("/event/{id}")
    public ResponseEntity<Event> deleteEventById(@PathVariable long id) {
        return ResponseEntity.ok(eventService.deleteEventById(id));
    }

    @GetMapping(value = "/event", params = {"start_time", "end_time"})
    public ResponseEntity<List<Event>> returnEventsByDate(@RequestParam(required = false) LocalDate start_time, LocalDate end_time) {
        return ResponseEntity.ok(eventService.returnEventsByDate(start_time, end_time));
    }
}
