package br.com.dio.parking.service;

import br.com.dio.parking.exception.ParkingException;
import br.com.dio.parking.model.Parking;
import br.com.dio.parking.repository.ParkingRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ParkingService {

    private ParkingRepository repository;

    public ParkingService(ParkingRepository repository) {
        this.repository = repository;
    }

    public Parking findById(String id){
        return repository.findById(id).orElseThrow(
                ()-> new ParkingException(id));
    }

    @Transactional
    public Parking create(Parking parking){
        String uuid = getUUID();
        while (this.verifyId(uuid)){
            uuid = getUUID();
        }
        parking.setId(uuid);
        return repository.save(parking);
    }

    @Transactional(readOnly = true)
    public List<Parking> index(){
        return repository.findAll();
    }

    private static String getUUID() {
        return UUID.randomUUID().toString();
    }

    private Boolean verifyId(String id){
        Optional<Parking> pk = repository.searchById(id);
        if(pk.isEmpty()){
            return false;
        }
        return true;
    }
}
