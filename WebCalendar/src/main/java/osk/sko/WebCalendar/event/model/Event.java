package osk.sko.WebCalendar.event.model;


import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String event;

    @Temporal(TemporalType.DATE)
    private LocalDate date;

    public Event(long id, String event, LocalDate date) {
        this.id = id;
        this.event = event;
        this.date = date;
    }

    public Event() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getEvent() {
        return event;
    }

    public void setEvent(String event) {
        this.event = event;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}
