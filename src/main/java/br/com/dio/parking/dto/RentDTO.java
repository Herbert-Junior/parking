package br.com.dio.parking.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class RentDTO {

    private String parking;
    private String board;
    private String model;
    private String color;

}
