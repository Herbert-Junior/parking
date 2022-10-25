package br.com.dio.parking.dto;

import br.com.dio.parking.model.Car;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor @NoArgsConstructor @Data
public class VacancyDTO {

    private Long id;
    private String ticket;
    private LocalDateTime incoming;
    private LocalDateTime exit;
    private Double totalPrice;
    private Car car;
}
