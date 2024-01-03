package com.example.safetyalerts.controller;

import com.example.safetyalerts.model.Person;
import com.example.safetyalerts.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/apiPerson")
public class PersonController {

    @Autowired
    PersonRepository personRepository;

    // Get all details of every person.
    // localhost:8080/apiPerson/person
    @GetMapping("/person")
    public ResponseEntity<List<Person>> getAllPersons() {

        List<Person> person = new ArrayList<Person>();

        personRepository.findAll().forEach(person::add);
        return new ResponseEntity<>(person, HttpStatus.OK);
    }

    // Get the email of everyone within a specific city.
    // localhost:8080/apiPerson/communityEmail/city/{city}
    @GetMapping("/communityEmail/city/{city}")
    public ResponseEntity<List<String>> getEmailsByCity(@PathVariable(value = "city") String city) {

        List<String> emailsInCity = personRepository.findEmailsByCity(city);

        return new ResponseEntity<>(emailsInCity, HttpStatus.OK);
    }

    // Return person information based on given first and last names.
    // http://localhost:8080/apiPerson/personInfo?firstName=John&lastName=Doe
    @GetMapping("/personInfo")
    public ResponseEntity<Person> getPersonDetails(
            @RequestParam(value = "firstName") String firstName,
            @RequestParam(value = "lastName") String lastName) {

        Optional<Person> personOptional = personRepository.findByFirstNameAndLastName(firstName, lastName);

        if (personOptional.isPresent()) {
            Person person = personOptional.get();
            return new ResponseEntity<>(person, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Return person information grouped by address, based on station number.
    // http://localhost:8080/apiPerson/firestation?stationNumber=1
    @GetMapping("/firestation")
    public ResponseEntity<Map<String, List<Map<String, Object>>>> getHouseholdsByStation(
            @RequestParam(value = "stationNumber") int stationNumber) {

        List<Person> personsInStation = personRepository.findByStationNumber(stationNumber);

        Map<String, List<Map<String, Object>>> households = personsInStation.stream()
                .collect(Collectors.groupingBy(
                        Person::getAddress,
                        Collectors.mapping(this::mapPersonToDetails, Collectors.toList())
                ));

        return new ResponseEntity<>(households, HttpStatus.OK);
    }
    private Map<String, Object> mapPersonToDetails(Person person) {
        Map<String, Object> details = new HashMap<>();
        details.put("name", person.getFirstName() + " " + person.getLastName());
        details.put("age", person.getAge());
        details.put("phoneNumber", person.getPhoneNumber());
        details.put("medications", person.getMedications());
        details.put("allergies", person.getAllergies());
        return details;
    }

    // Return station number and person information, grouped by address.
    // http://localhost:8080/apiPerson/fire?address=123 Main St
    @GetMapping("/fire")
    public ResponseEntity<Map<String, Object>> getAddressDetails(
            @RequestParam(value = "address") String address) {

        List<Person> personsAtAddress = personRepository.findByAddress(address);

        if (!personsAtAddress.isEmpty()) {
            Person firstPerson = personsAtAddress.get(0); // Assuming the address is associated with the same fire station for all persons

            Map<String, Object> addressDetails = new HashMap<>();
            addressDetails.put("stationNumber", firstPerson.getStationNumber());
            addressDetails.put("persons", personsAtAddress.stream()
                    .map(this::mapPersonToDetails)
                    .collect(Collectors.toList()));

            return new ResponseEntity<>(addressDetails, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Get all phone numbers belonging to a station.
    // http://localhost:8080/apiPerson/phoneAlert?stationNumber=1
    @GetMapping("/phoneAlert")
    public ResponseEntity<List<String>> getPhoneNumbersByStation(
            @RequestParam(value = "stationNumber") int stationNumber) {

        List<Person> personsInStation = personRepository.findByStationNumber(stationNumber);

        if (!personsInStation.isEmpty()) {
            List<String> phoneNumbers = personsInStation.stream()
                    .map(person -> String.valueOf(person.getPhoneNumber()))
                    .collect(Collectors.toList());

            return new ResponseEntity<>(phoneNumbers, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Return persons under the age of 18 at a specific address.
    // http://localhost:8080/apiPerson/childAlert?address=123 Main St
    @GetMapping("/childAlert")
    public ResponseEntity<List<Person>> getPersonsUnder18AtAddress(
            @RequestParam(value = "address") String address) {

        List<Person> personsUnder18 = personRepository.findByAddress(address).stream()
                .filter(person -> person.getAge() < 18)
                .collect(Collectors.toList());

        if (!personsUnder18.isEmpty()) {
            return new ResponseEntity<>(personsUnder18, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // List all people, grouped by fire station.
    // http://localhost:8080/apiPerson/flood
    @GetMapping("/flood")
    public ResponseEntity<Map<Integer, List<Person>>> getAllPeopleByStation() {

        List<Person> allPeople = personRepository.findAll();

        Map<Integer, List<Person>> peopleByStation = allPeople.stream()
                .collect(Collectors.groupingBy(Person::getStationNumber));

        if (!peopleByStation.isEmpty()) {
            return new ResponseEntity<>(peopleByStation, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }



//    // Post new details about a person.
//    @PostMapping("/person")
//    public ResponseEntity<Person> creatPerson(@RequestBody Person person) {
//        Person newPerson = personRepository.save(new Person(person.getFirstName(), person.getLastName(), person.getAge(), person.getAddress(), person.getCity(), person.getPhoneNumber(), person.getEmail(), person.getMedications(), person.getAllergies()));
//        return new ResponseEntity<>(newPerson, HttpStatus.CREATED);
//    }


}
