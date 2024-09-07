package osk.sko.CinemaService.cinema;

import osk.sko.CinemaService.ticket.ReturnTicketResponse;
import osk.sko.CinemaService.seat.Seat;
import osk.sko.CinemaService.ticket.Ticket;
import osk.sko.CinemaService.ticket.TokenRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;



@RestController
public class CinemaController {

    private final CinemaService cinemaService;

    public CinemaController(CinemaService cinemaService) {
        this.cinemaService = cinemaService;
    }

    @GetMapping("/seats")
    public ResponseEntity<Cinema> getSeats() {
        return ResponseEntity.ok(cinemaService.getAllSeats());
    }

    @PostMapping("/purchase")
    public ResponseEntity<Ticket> purchaseTicket(@RequestBody Seat seat) {
        return ResponseEntity.ok(cinemaService.purchaseTicket(seat));
    }

    @PostMapping("/return")
    public ResponseEntity<ReturnTicketResponse> returnTicket(@RequestBody TokenRequest tokenRequest) {
        return ResponseEntity.ok(cinemaService.returnTicket(tokenRequest.getToken()));
    }

    @GetMapping("/stats")
    public ResponseEntity<CinemaStatistics> getCinemaStatistics(@RequestParam(required = false) String password) {
        return ResponseEntity.ok(cinemaService.getCinemaStatistics(password));
    }

}
