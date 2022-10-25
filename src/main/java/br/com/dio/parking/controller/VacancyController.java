package br.com.dio.parking.controller;

import br.com.dio.parking.dto.CheckOutDTO;
import br.com.dio.parking.dto.RentDTO;
import br.com.dio.parking.dto.TicketDTO;
import br.com.dio.parking.model.Vacancy;
import br.com.dio.parking.service.VacancyRentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Optional;

@RestController
@RequestMapping("/vacancy")
@Tag(name = "Vacancy Controller",description = "Create | getting or chechOut Vacancy ")
public class VacancyController {

    public VacancyRentService service;

    public VacancyController(VacancyRentService service) {
        this.service = service;
    }

    @Operation(summary = "Rent a one vacancy")
    @PostMapping
    public ResponseEntity<TicketDTO> rent(@RequestBody RentDTO rent){
        return new ResponseEntity<TicketDTO>(service.rent(rent), HttpStatus.OK);
    }

    @Operation(summary = "Checkout a one vacancy")
    @GetMapping("/{id}")
    public ResponseEntity<CheckOutDTO> checkOut(String id){
        return new ResponseEntity<CheckOutDTO>(service.checkOut(id), HttpStatus.OK);
    }

    @Operation(summary = "Extract rent data by parking ")
    @GetMapping("/relatorio/{id}")
    public ResponseEntity<Optional<Vacancy>> index(String id)
    {
        return new ResponseEntity<Optional<Vacancy>>(service.index(id), HttpStatus.OK);
    }
}
