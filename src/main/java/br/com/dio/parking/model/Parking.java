package br.com.dio.parking.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Schema(accessMode = Schema.AccessMode.READ_ONLY)
public class Parking {

    @Id
    private String id;
    @NotNull
    private String name;
    @NotNull
    private Integer vacancyLimit;

}
