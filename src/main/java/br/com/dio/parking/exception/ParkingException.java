package br.com.dio.parking.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class ParkingException extends RuntimeException{

    public ParkingException(String id) {
        super("Parking not found with Id: " + id);
    }

}
