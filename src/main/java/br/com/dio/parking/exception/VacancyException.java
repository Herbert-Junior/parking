package br.com.dio.parking.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class VacancyException extends RuntimeException {

    public VacancyException(Long id){
        super("Alocamento not found with id: "+ id);
    }
}
