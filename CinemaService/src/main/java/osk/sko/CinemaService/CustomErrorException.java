package osk.sko.CinemaService;

public class CustomErrorException extends RuntimeException {

    public CustomErrorException(String error) {
        super(error);
    }
}
