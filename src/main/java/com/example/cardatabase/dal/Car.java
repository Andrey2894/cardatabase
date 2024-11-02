package com.example.cardatabase.dal;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;


@Entity
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String brand, model, colour, registerNumber;
    private int years, price;
    //Рекомендуется использовать
    //FetchType.LAZY для всех ассоциаций. Для отношений toMany это
    //значение по умолчанию, но для отношений toOne его следует определить.
    //Значение может быть
    //EAGER или LAZY. В нашем случае ленивая стратегия означает, что при извлечении владельца
    //из базы данных все автомобили, связанные с владельцем, будут извлечены при необходимости.
    //Eager означает, что автомобили будут извлечены немедленно владельцем.

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "owner")//название столбца
    private Owner owner;

    @ManyToMany(mappedBy="cars")
    private Set<Owner> owners = new HashSet<Owner>();

    public Car() {}

    public Car(String brand, String model, String colour, String registerNumber, int years, int price, Owner owner) {
        super();//id автоматически выбирается поэтому он не нужен в конструкторе
        this.brand = brand;
        this.model = model;
        this.colour = colour;
        this.registerNumber = registerNumber;
        this.years = years;
        this.price = price;
        this.owner = owner;
    }

    public Set<Owner> getOwners() {
        return owners;
    }

    public void setOwners(Set<Owner> owners) {
        this.owners = owners;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getColour() {
        return colour;
    }

    public void setColour(String colour) {
        this.colour = colour;
    }

    public String getRegisterNumber() {
        return registerNumber;
    }

    public void setRegisterNumber(String registerNumber) {
        this.registerNumber = registerNumber;
    }

    public int getYears() {
        return years;
    }

    public void setYears(int years) {
        this.years = years;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public Owner getOwner() {
        return owner;
    }

    public void setOwner(Owner owner) {
        this.owner = owner;
    }
}
