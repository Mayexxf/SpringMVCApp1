package ru.frizin.springcourse.config.models;

import jakarta.validation.constraints.*;

public class Person {
    private int id;


//    @NotEmpty(message = "Name should not be empty")
//    @Size(min = 2, max = 30, message = "Name should be between 2 and 30 characters")
    private String fio;

//    @Min( value = 0, message = "Age should be greater than 0")
    private int date;

//    @NotEmpty(message = "Name should not be empty")
//    @Email(message = "Email should be valid")
//    private String email;

    // Страна, Город, Индерс (6 цифр)
//    @Pattern(regexp = "[A-Z]\\w+, [A-Z]\\w+, \\d{6}", message = "Your address should be in this format: Country, City, Postal Code (6 digits)")
//    private String address;

    public Person(){}

    public Person(int id, String fio, int date) {
        this.id = id;
        this.fio = fio;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFio() {
        return fio;
    }

    public void setFio(String fio) {
        this.fio = fio;
    }

    public int getDate() {
        return date;
    }

    public void setDate(int date) {
        this.date = date;
    }
}
