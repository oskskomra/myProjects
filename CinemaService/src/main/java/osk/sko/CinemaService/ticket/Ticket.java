package osk.sko.CinemaService.ticket;



import osk.sko.CinemaService.seat.Seat;

import java.util.UUID;

public class Ticket {

    private UUID token;

    private Seat ticket;

    public Ticket() {

    }


    public UUID getToken() {
        return token;
    }

    public void setToken(UUID token) {
        this.token = token;
    }

    public Seat getTicket() {
        return ticket;
    }

    public void setTicket(Seat ticket) {
        this.ticket = ticket;
    }
}






