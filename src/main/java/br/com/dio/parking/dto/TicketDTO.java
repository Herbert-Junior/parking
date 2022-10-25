package br.com.dio.parking.dto;

import br.com.dio.parking.model.Car;
import br.com.dio.parking.model.Vacancy;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class TicketDTO {

    private String ticket;
    private String carBoard;
    private String carModel;
    private String parkingName;
}
