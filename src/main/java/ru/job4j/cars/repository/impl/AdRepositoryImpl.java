package ru.job4j.cars.repository.impl;

import java.util.List;

import ru.job4j.cars.model.Advertisement;
import ru.job4j.cars.repository.AbstractRepository;
import ru.job4j.cars.repository.AdRepository;

public class AdRepositoryImpl extends AbstractRepository implements AdRepository {

    @Override
    public List<Advertisement> findAdvertisementsForTheLastDay() {
        return this.tx(
                session -> session.createQuery(
                        "from Advertisement ad "
                                + "where date_trunc('day', ad.created) "
                                + "<= date_trunc('day', current_date)"
                                  )
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
