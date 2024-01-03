package com.example.safetyalerts.controller;

import com.example.safetyalerts.model.Person;
import com.example.safetyalerts.repository.PersonRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DataInsertion implements CommandLineRunner {
    private final PersonRepository personRepository;

    public DataInsertion(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @Override
    public void run(String... args) {
        Person person1 = new Person("John", "Doe", 17, "123 Main St", "Halifax", 2231951, "john@gmail.com", 1, List.of("Zoloft 10mg", "Mirtazipine 5mg"), List.of("Nuts"));
        Person person2 = new Person("Jane", "Smith", 26, "123 Main St", "Halifax", 1011110, "jane@gmail.com", 1, List.of(""), List.of(""));
        Person person3 = new Person("Alice", "Williams", 31, "456 Oak St", "Springfield", 2022222, "alice@gmail.com", 2, List.of("Trintellix 10mg"), List.of("Fish"));
        Person person4 = new Person("Bob", "Williams", 30, "456 Oak St", "Springfield", 3033333, "bob@gmail.com", 2, List.of("Amoxicillin 10mg", "Aspirin 2mg"), List.of("Trees", "Pollen"));
        Person person5 = new Person("Charlie", "Williams", 7, "456 Oak St", "Springfield", 4044444, "charlie@gmail.com", 2, List.of("Metformin 1mg"), List.of(""));
        Person person6 = new Person("David", "Miller", 28, "202 Birch St", "Hilltop", 5055555, "david@gmail.com", 2, List.of(""), List.of("People", "Nuts"));
        Person person7 = new Person("Eva", "White", 32, "303 Maple St", "Valleytown", 6066666, "eva@gmail.com", 3, List.of("Metoprolol 5mg"), List.of("Fish"));
        Person person8 = new Person("Frank", "Anderson", 24, "404 Cedar St", "Lakeville", 7077777, "frank@gmail.com", 3, List.of("Escitalopram 10mg", "Albuterol 5mg"), List.of("Pollen", "Fish"));
        Person person9 = new Person("Grace", "Smith", 29, "505 Pine St", "Hillside", 8088888, "grace@gmail.com", 3, List.of("Amlodipine 5mg", "Albuterol 5mg", "Losartan 5mg"), List.of(""));
        Person person10 = new Person("Harry", "Brown", 26, "606 Elm St", "Sunset", 9099999, "harry@gmail.com", 4, List.of(""), List.of("Fish"));
        Person person11 = new Person("Ivy", "Taylor", 36, "707 Oak St", "Greenfield", 10101010, "ivy@gmail.com", 4, List.of("Gabapentin 10mg", "Atorvastatin 3mg"), List.of("Trees"));
        Person person12 = new Person("Jack", "Taylor", 11, "707 Oak St", "Greenfield", 11111111, "jack@gmail.com", 4, List.of(""), List.of("Nuts", "Fish"));
        personRepository.saveAll(List.of(person1, person2, person3, person4, person5, person6, person7, person8, person9, person10, person11, person12));
    }
}
