package com.spring.user.main.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

/**
 * Clase que representa un teléfono asociado a un usuario en el sistema.
 */
@Entity
@Table(name = "phones", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"number", "user_customer_id"}, name = "uk_phones")
})
public class Phones {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "number")
    private String number;
    @Column(name = "citycode")
    private String citycode;
    @Column(name = "countrycode")
    private String countrycode;
    @ManyToOne
    @JoinColumn(name = "user_customer_id")
    @JsonBackReference
    private UserCustomer userCustomer;
    public Phones() {
    }

    public Phones(String number, String citycode, String countrycode, UserCustomer userCustomer) {
        this.number = number;
        this.citycode = citycode;
        this.countrycode = countrycode;
        this.userCustomer = userCustomer;
    }
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getCitycode() {
        return citycode;
    }

    public void setCitycode(String citycode) {
        this.citycode = citycode;
    }

    public String getCountrycode() {
        return countrycode;
    }

    public void setCountrycode(String countrycode) {
        this.countrycode = countrycode;
    }

    public UserCustomer getUserCustomer() {
        return userCustomer;
    }

    public void setUserCustomerId(UserCustomer userCustomer) {
        this.userCustomer = userCustomer;
    }
}
