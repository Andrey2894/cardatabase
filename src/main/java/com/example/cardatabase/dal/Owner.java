package com.example.cardatabase.dal;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
//Сначала сериализуется car,
//и он содержит владельца, который затем сериализуется, а тот, в свою очередь, содержит cars,
// которые затем сериализуются и так далее. Чтобы избежать этого
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Owner {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long ownerId;
    private String firstName, lastName;

    //Атрибут cascade определяет, как каскадирование влияет на сущности в случае удалений или обновлений.
    //Настройка атрибута ALL означает, что все операции каскадируются.
    //Например, если владелец удален, автомобили, связанные с этим владельцем, также удаляются.
    //Настройка атрибута mappedBy="owner" сообщает нам, что класс Car имеет поле owner, которое является внешним ключом для этой связи.

    @JsonIgnore
    @OneToMany(cascade=CascadeType.ALL, mappedBy="owner")//название поля
    private List<Car> car;

    @ManyToMany(cascade=CascadeType.PERSIST)//set используется
    @JoinTable(name="car_owner",
            joinColumns = { @JoinColumn(name="ownerId") },
            inverseJoinColumns = { @JoinColumn(name="id") })
    private Set<Car> cars = new HashSet<Car>();

    public Owner() {

    }

    public Owner(String firstName, String lastName) {
        super();
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public List<Car> getCar() {
        return car;
    }

    public void setCar(List<Car> car) {
        this.car = car;
    }

    public Set<Car> getCars() {
        return cars;
    }

    public void setCars(Set<Car> cars) {
        this.cars = cars;
    }

    public long getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(long ownerId) {
        this.ownerId = ownerId;
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
}
