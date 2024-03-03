package com.spring.user.main.dto;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

/**
 * Clase que representa un teléfono asociado a un usuario.
 */
@JsonPropertyOrder({"status", "title", "data"})
public class ResponseDTO {
    private int status;
    private String title;
    private RegisteredUserDTO data;

    public ResponseDTO(int status, String title, RegisteredUserDTO data) {
        this.status = status;
        this.title = title;
        this.data = data;
    }
    public ResponseDTO() {
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public RegisteredUserDTO getData() {
        return data;
    }

    public void setData(RegisteredUserDTO data) {
        this.data = data;
    }
}
