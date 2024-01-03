package com.example.safetyalerts.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "person")
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "person_generator")
    private Long id;

    @Column
    private String firstName;
    @Column
    private String lastName;
    @Column
    private int age;
    @Column
    private String address;
    @Column
    private String city;
    @Column
    private int phoneNumber;
    @Column
    private String email;
    @Column
    private int stationNumber;
    @Column
    @ElementCollection
    private List<String> medications;
    @Column
    @ElementCollection
    private List<String> allergies;



    public Person() {
    }
    public Person(String firstName, String lastName, int age, String address, String city, int phoneNumber, String email, int stationNumber, List<String> medications, List<String> allergies) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.address = address;
        this.city = city;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.stationNumber = stationNumber;
        this.medications = medications;
        this.allergies = allergies;
    }



    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }



    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }



    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }



    public int getAge() {
        return age;
    }
    public void setAge(int age) {
        this.age = age;
    }



    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }



    public String getCity() {
        return city;
    }
    public void setCity(String city) {
        this.city = city;
    }



    public int getPhoneNumber() {
        return phoneNumber;
    }
    public void setPhoneNumber(int phoneNumber) {
        this.phoneNumber = phoneNumber;
    }



    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }



    public int getStationNumber() {
        return stationNumber;
    }
    public void setStationNumber(int stationNumber) {
        this.stationNumber = stationNumber;
    }



    public List<String> getMedications() {
        return medications;
    }
    public void setMedications(List<String> medications) {
        this.medications = medications;
    }



    public List<String> getAllergies() {
        return allergies;
    }
    public void setAllergies(List<String> allergies) {
        this.allergies = allergies;
    }
}
