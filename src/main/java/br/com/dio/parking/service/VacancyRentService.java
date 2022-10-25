package br.com.dio.parking.service;

import br.com.dio.parking.dto.CheckOutDTO;
import br.com.dio.parking.dto.RentDTO;
import br.com.dio.parking.dto.TicketDTO;
import br.com.dio.parking.dto.VacancyDTO;
import br.com.dio.parking.exception.VacancyException;
import br.com.dio.parking.mapper.ModelsToMapper;
import br.com.dio.parking.model.Car;
import br.com.dio.parking.model.Parking;
import br.com.dio.parking.model.Vacancy;
import br.com.dio.parking.repository.CarRepository;
import br.com.dio.parking.repository.VacancyRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class VacancyRentService {

    public static final int HOUR = 60;
    public static final int TWENTY_FOUR_HOUR = 24 * HOUR;
    public static final double HOUR_VALUE = 5.00;
    public static final double ADDITIONAL_HOUR_VALUE = 2.00;
    public static final double DAY_VALUE = 30.00;

    private VacancyRepository repository;
    private CarRepository carRepository;
    private ParkingService parkingService;
    private ModelsToMapper modeMapper;

    public VacancyRentService(
            VacancyRepository repository,
            CarRepository carRepository,
            ParkingService parkingService,
            ModelsToMapper modelMapper) {

        this.repository = repository;
        this.carRepository = carRepository;
        this.parkingService = parkingService;
        this.modeMapper = modelMapper;
    }

    public TicketDTO rent(RentDTO rent){
        //valitate if parking exist;
        var parking = this.parkingService.findById(rent.getParking());

        //registration the car
        Car car = this.modeMapper.toCar(rent);
        car = carRepository.save(car);

        //registration the vacancy
        Vacancy vacancy = new Vacancy();
        vacancy.setCar(car);
        vacancy.setIncoming(LocalDateTime.now());
        vacancy.setTicket(getUUID());
        vacancy.setParking(parking);
        vacancy = repository.save(vacancy);

        return this.modeMapper.toTicketDTO(vacancy);
    }

    public CheckOutDTO checkOut(String ticket){
        Vacancy vacancy = repository.findByTicket(ticket);

        long minutes = vacancy.getIncoming().until(LocalDateTime.now(), ChronoUnit.MINUTES);
        Double bill = 5.0;
        vacancy.setExit(LocalDateTime.now());
        if(minutes<= HOUR){
            vacancy.setTotalPrice(HOUR_VALUE);
            vacancy = repository.save(vacancy);
            return this.modeMapper.toCheckoutDTO(vacancy);
        }
        if(minutes <= TWENTY_FOUR_HOUR){
            int hours = (int) (minutes / HOUR);
            for (int i = 1; i < hours; i++) {
                bill += ADDITIONAL_HOUR_VALUE;
            }
            vacancy.setTotalPrice(bill);
            vacancy = repository.save(vacancy);
            return this.modeMapper.toCheckoutDTO(vacancy);
        }
        int days = (int) (minutes / TWENTY_FOUR_HOUR);
        for (int i = 0; i < days; i++) {
            bill += DAY_VALUE;
        }
        vacancy.setTotalPrice(bill);
        vacancy = repository.save(vacancy);
        return this.modeMapper.toCheckoutDTO(vacancy);
    }
    @Transactional(readOnly = true)
    public Vacancy findById(Long id){
        return repository.findById(id).orElseThrow(
                () -> new VacancyException(id)
        );
    }

    @Transactional(readOnly = true)
    public Optional<Vacancy> index(String id){
        Optional<Vacancy> all = repository.searchByParking(id);
        return all;
       // return modeMapper.toVacancyDTO(all);
    }

    private static String getUUID() {
        return UUID.randomUUID().toString();
    }
}
