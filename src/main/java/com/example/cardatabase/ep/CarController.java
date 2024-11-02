package com.example.cardatabase.ep;

import com.example.cardatabase.dal.Car;
import com.example.cardatabase.dal.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//@RestController определяет, что этот класс будет контроллером для
//веб-службы RESTful
@RestController
public class CarController {

    @Autowired
    private CarRepository repository;
    //Благодаря аннотации @RestController данные
    //теперь сериализуются в формат JSON в ответе
    @RequestMapping("/cars")
    public Iterable<Car> getCars() {
        return repository.findAll();
    }
}
