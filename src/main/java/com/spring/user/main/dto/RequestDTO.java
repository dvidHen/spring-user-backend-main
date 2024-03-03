package com.spring.user.main.dto;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;


import java.util.List;


/**
 * Clase que representa la solicitud de registro de usuario.
 */
public class RequestDTO {
    @NotBlank(message = "[ERROR] Campo nombre no puede ser vacío")
    private String name;
    @NotBlank(message = "[ERROR] Campo email no puede ser vacío")
    @Pattern(regexp = "^[\\w\\.-]+@[a-zA-Z\\d\\.-]+\\.[a-zA-Z]{2,}$", message = "[ERROR] Debe ser un correo electrónico válido")
    private String email;

    @NotBlank(message = "[ERROR] Campo password no puede ser vacío")
    @Pattern(regexp = "^(?=.*[A-Z])(?=.*\\d)(?=.*[!@#$%^&*()-_+=])[A-Za-z\\d!@#$%^&*()-_+=]{6,}$", message = "[ERROR] La contraseña debe contener al menos 8 caracteres, al menos una mayúscula, al menos una minúsucla, al menos un número y un carácter especial @#$%^&+=")
    private String password;

    private List<PhoneDTO> phones;

    public RequestDTO(String name, String email, String password, @Valid List<PhoneDTO> phones) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.phones = phones;
    }

    public RequestDTO() {
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<PhoneDTO> getPhones() {
        return phones;
    }

    public void setPhones(List<PhoneDTO> phones) {
        this.phones = phones;
    }

    public String toJSON() throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(this);
    }
}


