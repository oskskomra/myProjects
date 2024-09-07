package osk.sko.CinemaService;


public class WrongPasswordException extends RuntimeException {

    public WrongPasswordException(String error) {
        super(error);
    }
}
