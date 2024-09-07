package osk.sko.CinemaService.seat;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class Seat {
    private int row;
    private int column;

    private int price;

    private boolean isAvailable;

    public Seat(int row, int column, int price, boolean isAvailable) {
        this.row = row;
        this.column = column;
        this.price = price;
        this.isAvailable = isAvailable;
    }

    public Seat() {
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getColumn() {
        return column;
    }

    public void setColumn(int column) {
        this.column = column;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    @JsonIgnore
    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }
}
