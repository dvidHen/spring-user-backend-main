package com.spring.user.main.dto;

import jakarta.validation.constraints.NotBlank;
import org.springframework.beans.factory.annotation.Value;

/**
 * Clase que representa la información de un teléfono en la solicitud de registro de usuario.
 */
public class PhoneDTO {
    private String number;
    private String citycode;
    private String countrycode;

    public PhoneDTO() {
    }

    public PhoneDTO(String number, String citycode, String countrycode) {
        this.number = number;
        this.citycode = citycode;
        this.countrycode = countrycode;
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
    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }
}
