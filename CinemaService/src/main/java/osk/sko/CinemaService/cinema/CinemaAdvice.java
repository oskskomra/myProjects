package osk.sko.CinemaService.cinema;

import osk.sko.CinemaService.CustomErrorException;
import osk.sko.CinemaService.CustomErrorMessage;
import osk.sko.CinemaService.WrongPasswordException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


@ControllerAdvice
public class CinemaAdvice{

    @ExceptionHandler(CustomErrorException.class)
    public ResponseEntity<CustomErrorMessage> handlePurchasedTicket(CustomErrorException e) {
        CustomErrorMessage body = new CustomErrorMessage(e.getMessage());
        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(WrongPasswordException.class)
    public ResponseEntity<CustomErrorMessage> handleWrongPassword(WrongPasswordException e) {
        CustomErrorMessage body = new CustomErrorMessage(e.getMessage());
        return new ResponseEntity<>(body, HttpStatus.UNAUTHORIZED);
    }

}
