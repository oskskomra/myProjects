package osk.sko.CinemaService.cinema;

import osk.sko.CinemaService.CustomErrorException;
import osk.sko.CinemaService.WrongPasswordException;
import osk.sko.CinemaService.ticket.ReturnTicketResponse;
import osk.sko.CinemaService.seat.Seat;
import osk.sko.CinemaService.ticket.Ticket;
import org.springframework.stereotype.Service;

import java.util.*;


@Service
public class CinemaService {

    private ArrayList<Seat> seats;

    private ArrayList<Ticket> purchasedTickets;

    public CinemaService(ArrayList<Seat> seats, ArrayList<Ticket> purchasedTickets) {
        this.seats = seats;
        this.purchasedTickets = purchasedTickets;
        createCinema();
    }

    public void createCinema() {

        int price = 0;
        boolean isAvailable = true;
        for (int rows = 1; rows < 10; rows++) {
            for (int cols = 1; cols < 10; cols++) {
                if (rows <= 4) {
                    price = 10;
                } else {
                    price = 8;
                }
                seats.add(new Seat(rows, cols, price, isAvailable));
            }
        }
    }

    public Cinema getAllSeats() {
        return new Cinema(seats);
    }

    public Ticket purchaseTicket(Seat seat) {
        if (seat.getColumn() < 1 || seat.getColumn() > 9 || seat.getRow() < 1 || seat.getRow() > 9) {
            throw new CustomErrorException("The number of a row or a column is out of bounds!");
        } else {
            for (Seat bookedSeat : seats) {
                if (bookedSeat.getRow() == seat.getRow() && bookedSeat.getColumn() == seat.getColumn() && bookedSeat.isAvailable()) {
                    bookedSeat.setAvailable(false);
                    UUID uuid = UUID.randomUUID();
                    Ticket ticket = new Ticket();
                    ticket.setToken(uuid);
                    ticket.setTicket(bookedSeat);
                    purchasedTickets.add(ticket);
                    return ticket;
                }
            }
        }
        throw new CustomErrorException("The ticket has been already purchased!");
    }

    public ReturnTicketResponse returnTicket(UUID token) {


        for (Ticket returnedTicket : purchasedTickets) {
            if (returnedTicket.getToken().equals(token)) {
                for (Seat returnedSeat : seats) {
                    if (returnedTicket.getTicket().getRow() == returnedSeat.getRow() && returnedTicket.getTicket().getColumn() == returnedSeat.getColumn()) {
                        returnedSeat.setAvailable(true);
                    }
                    purchasedTickets.remove(returnedTicket);
                }
                return new ReturnTicketResponse(returnedTicket.getTicket());
            }
        }
        throw new CustomErrorException("Wrong token!");
    }


    public CinemaStatistics getCinemaStatistics(String password) {
        CinemaStatistics cinemaStatistics = new CinemaStatistics();
        cinemaStatistics.setAvailable(81);
        if (password == null || !password.equals("super_secret")) {
            throw new WrongPasswordException("The password is wrong!");
        } else {
            for (Ticket purchasedTicket : purchasedTickets) {
                cinemaStatistics.setIncome(cinemaStatistics.getIncome() + purchasedTicket.getTicket().getPrice());
                cinemaStatistics.setAvailable(cinemaStatistics.getAvailable() - 1);
                cinemaStatistics.setPurchased(cinemaStatistics.getPurchased() + 1);
            }
            return cinemaStatistics;
        }
    }
}
