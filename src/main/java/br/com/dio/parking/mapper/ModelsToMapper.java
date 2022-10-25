package br.com.dio.parking.mapper;

import br.com.dio.parking.dto.CheckOutDTO;
import br.com.dio.parking.dto.RentDTO;
import br.com.dio.parking.dto.TicketDTO;
import br.com.dio.parking.dto.VacancyDTO;
import br.com.dio.parking.model.Car;
import br.com.dio.parking.model.Vacancy;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ModelsToMapper {

    private static final ModelMapper MODEL_MAPPER = new ModelMapper();

    public Car toCar(RentDTO rentdto){
        return MODEL_MAPPER.map(rentdto, Car.class);
    }

    public TicketDTO toTicketDTO(Vacancy vacancy){
        return MODEL_MAPPER.map(vacancy, TicketDTO.class);
    }

    public CheckOutDTO toCheckoutDTO(Vacancy vacancy){
        return MODEL_MAPPER.map(vacancy, CheckOutDTO.class);
    }

    public VacancyDTO toVacancyDTO(List<Vacancy> vacancy){
        return MODEL_MAPPER.map(vacancy, VacancyDTO.class);
    }


}
