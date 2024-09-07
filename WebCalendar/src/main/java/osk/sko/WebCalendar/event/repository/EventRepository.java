package osk.sko.WebCalendar.event.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import osk.sko.WebCalendar.event.model.Event;

import java.time.LocalDate;
import java.util.List;

public interface EventRepository extends JpaRepository<Event, Long> {
    List<Event> findAllByDate(LocalDate date);
}
