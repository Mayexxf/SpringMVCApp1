package ru.frizin.springcourse.config.models;

public class Book {
    private int id;
    private String name_book;
    private String autor;
    private int year_boor;


    public Book(){}
    public Book(int id, String name_book, String autor, int year_boor) {
        this.id = id;
        this.name_book = name_book;
        this.autor = autor;
        this.year_boor = year_boor;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName_book() {
        return name_book;
    }

    public void setName_book(String name_book) {
        this.name_book = name_book;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public int getYear_boor() {
        return year_boor;
    }

    public void setYear_boor(int year_boor) {
        this.year_boor = year_boor;
    }
}
