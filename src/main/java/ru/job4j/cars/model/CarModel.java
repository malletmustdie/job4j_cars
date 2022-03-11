package ru.job4j.cars.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "car_model")
public class CarModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "brand_id", foreignKey = @ForeignKey(name = "CAR_BRAND_ID_FK"))
    private CarBrand brand;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "body_id", foreignKey = @ForeignKey(name = "CAR_BODY_ID_FK"))
    private CarBody body;

    @OneToMany(mappedBy = "model")
    private List<Advertisement> advertisements = new ArrayList<>();

    public CarModel() {
    }

    public CarModel(String name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public CarBrand getBrand() {
        return brand;
    }

    public void setBrand(CarBrand brand) {
        this.brand = brand;
    }

    public CarBody getBody() {
        return body;
    }

    public void setBody(CarBody body) {
        this.body = body;
    }

    public List<Advertisement> getAdvertisements() {
        return advertisements;
    }

    public void addAdvertisement(Advertisement advertisement) {
        this.advertisements.add(advertisement);
    }

}
