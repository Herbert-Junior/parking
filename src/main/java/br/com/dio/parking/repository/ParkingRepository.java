package br.com.dio.parking.repository;

import br.com.dio.parking.model.Parking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ParkingRepository extends JpaRepository<Parking, String> {

    @Query("select p from Parking p where p.id = ?1")
    public Optional<Parking> searchById(String id);
}
