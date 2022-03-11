package ru.job4j.cars.repository;

import java.util.List;

import ru.job4j.cars.model.Advertisement;

public interface AdRepository {

    List<Advertisement> findAdvertisementsForTheLastDay();

    List<Advertisement> findAdvertisementsWithPhoto();

    List<Advertisement> findAdvertisementsByCarBrand(String brandName);

}
