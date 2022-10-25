package br.com.dio.parking.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Schema(accessMode = Schema.AccessMode.READ_ONLY)
public class Vacancy {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String ticket;
    private LocalDateTime incoming;
    private LocalDateTime exit;
    private Double totalPrice;

    @OneToOne
    @JoinColumn(name = "car_id")
    private Car car;

    @ManyToOne
    @JoinColumn(name = "parking_id", nullable = false)
    private Parking parking;

}