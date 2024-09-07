package osk.sko.CinemaService.cinema;



import osk.sko.CinemaService.seat.Seat;

import java.util.List;



public class Cinema {

    private final int rows = 9;
    private final int columns = 9;
    private List<Seat> seats;

    public Cinema(List<Seat> seats) {
        this.seats = seats;
    }

    public Cinema() {
    }

    public int getRows() {
        return rows;
    }

    public int getColumns() {
        return columns;
    }

    public List<Seat> getSeats() {
        return seats;
    }
}
