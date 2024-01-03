package com.example.safetyalerts.repository;

import com.example.safetyalerts.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface PersonRepository extends JpaRepository<Person, Long> {

    @Query("SELECT person.email FROM Person person WHERE person.city = :city")
    List<String> findEmailsByCity(@Param("city") String city);

    Optional<Person> findByFirstNameAndLastName(String firstName, String lastName);

    List<Person> findByStationNumber(int stationNumber);

    List<Person> findByAddress(String address);
}
