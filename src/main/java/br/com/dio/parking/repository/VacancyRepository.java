package br.com.dio.parking.repository;

import br.com.dio.parking.model.Vacancy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface VacancyRepository extends JpaRepository<Vacancy, Long> {

    @Query("select v from Vacancy v where parking_id = ?1")
    public Optional<Vacancy> searchByParking(String id);

    public Vacancy findByTicket(String ticket);
}
