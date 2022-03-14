package ru.job4j.cars.repository.impl;

import ru.job4j.cars.model.Advertisement;
import ru.job4j.cars.repository.AbstractRepository;
import ru.job4j.cars.repository.AdRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class AdRepositoryImpl extends AbstractRepository implements AdRepository {

    @Override
    public List<Advertisement> findAdvertisementsForTheLastDay() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDate localDate = LocalDate.now();
        String startOfDay = formatter.format(LocalDateTime.of(localDate, LocalTime.MIDNIGHT));
        String endOfDay = formatter.format(LocalDateTime.of(localDate, LocalTime.MAX));
        return this.tx(
                session -> session.createQuery(
                                "from Advertisement ad "
                                        + "where ad.created between :from and :to")
                        .setParameter("from", startOfDay)
                        .setParameter("to", endOfDay)
                        .list()
        );
    }

    @Override
    public List<Advertisement> findAdvertisementsWithPhoto() {
        return this.tx(
                session -> session.createQuery("from Advertisement ad "
                                                       + "where ad.photoLink is not null")
                                  .list()
        );
    }

    @Override
    public List<Advertisement> findAdvertisementsByCarBrand(String brandName) {
        return this.tx(
                session -> session.createQuery("from Advertisement ad "
                                                       + "join fetch ad.model m "
                                                       + "join fetch m.brand b "
                                                       + "where b.name = :name")
                                  .setParameter("name", brandName)
                                  .list()
        );
    }

}
