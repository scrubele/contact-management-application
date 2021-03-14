package fr.isen.java2.db.entities;

import java.time.LocalDate;

public class Person {

    private Integer idperson;
    private String lastName;
    private String firstName;
    private String nickname;
    private String phoneNumber;
    private String address;
    private String emailAddress;
    private String imagePath;
    private LocalDate birthDate;

    public Person() {

    }

    public Person(Integer idperson,
                  String lastName,
                  String firstName,
                  String nickname,
                  String phoneNumber,
                  String address,
                  String emailAddress,
                  LocalDate birthDate) {
        this.idperson = idperson;
        this.lastName = lastName;
        this.firstName = firstName;
        this.nickname = nickname;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.emailAddress = emailAddress;
        this.birthDate = birthDate;
    }

    public Person(Integer idperson,
                  String lastName,
                  String firstName,
                  String nickname,
                  String phoneNumber,
                  String address,
                  String emailAddress,
                  LocalDate birthDate,
                  String imagePath) {
        this.idperson = idperson;
        this.lastName = lastName;
        this.firstName = firstName;
        this.nickname = nickname;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.emailAddress = emailAddress;
        this.imagePath = imagePath;
        this.birthDate = birthDate;
    }

    public Integer getIdperson() {
        return idperson;
    }

    public void setIdperson(Integer idperson) {
        this.idperson = idperson;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    @Override
    public String toString() {
        return "Person{" +
                "idperson=" + idperson +
                ", lastName='" + lastName + '\'' +
                ", firstName='" + firstName + '\'' +
                ", nickname='" + nickname + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", address='" + address + '\'' +
                ", emailAddress='" + emailAddress + '\'' +
                ", imagePath='" + imagePath + '\'' +
                ", birthDate=" + birthDate +
                '}';
    }
}
