package osk.sko.CinemaService.ticket;


import osk.sko.CinemaService.seat.Seat;

public class ReturnTicketResponse {

    private Seat ticket;

    public ReturnTicketResponse(Seat ticket) {
        this.ticket = ticket;
    }

    public Seat getTicket() {
        return ticket;
    }

    public void setTicket(Seat ticket) {
        this.ticket = ticket;
    }
}
