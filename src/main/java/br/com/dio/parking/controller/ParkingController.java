package br.com.dio.parking.controller;

import br.com.dio.parking.model.Parking;
import br.com.dio.parking.service.ParkingService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/parking")
@Tag(name = "Parking Controller",description = "Create or getting Parking")
public class ParkingController {

    private ParkingService service;

    public ParkingController(ParkingService service) {
        this.service = service;
    }

    @Operation(summary = "Create a new Parking")
    @PostMapping
    public ResponseEntity<Parking> index(@RequestBody @Valid Parking parking){
        Parking pk = service.create(parking);
        if(pk.getId() != null) {
            return new ResponseEntity<Parking>(pk, HttpStatus.CREATED);
        }
        return ResponseEntity.notFound().build();
    }

    @Operation(summary = "Get All Parking")
    @GetMapping
    public ResponseEntity<List<Parking>> index(){
        return new ResponseEntity<List<Parking>>(service.index(), HttpStatus.OK);
    }
}
