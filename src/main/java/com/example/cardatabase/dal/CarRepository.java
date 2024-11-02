package com.example.cardatabase.dal;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;
import java.util.Optional;

//Если вы хотите использовать другое именование путей, вы можете использовать аннотацию @RepositoryRestResouce
//@RepositoryRestResource(path="vehicles")
@RepositoryRestResource
public interface CarRepository extends CrudRepository<Car, Long> { //car - то что в репозитории, лонг это тип id

    List<Car> findByColour(String colour);
    List<Car> findByYears(int years);
    List<Car> findByBrandAndModel(String brand, String model);//and
    List<Car> findByBrandOrderByYearsAsc(String brand);
    @Query("select c from Car c where c.brand = ?1")//Вы также можете создавать запросы, используя операторы SQL через аннотацию @Query.
    List<Car> findByBrand(String brand);
}
