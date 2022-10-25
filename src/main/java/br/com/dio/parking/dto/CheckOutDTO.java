package br.com.dio.parking.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CheckOutDTO {

    private Double totalPrice;
    private LocalDateTime incoming;
    private LocalDateTime exit;
}
