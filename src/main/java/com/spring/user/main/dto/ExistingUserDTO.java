package com.spring.user.main.dto;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

/**
 * Clase que representa los datos de respuesta para un usuario existente.
 */
@JsonPropertyOrder({"status", "title", "message"})
public class ExistingUserDTO {

    private int status;
    private String title;
    private String message;
    public ExistingUserDTO(int status, String title, String message) {
        this.status = status;
        this.title = title;
        this.message = message;
    }

    public int getCodeStatus() {
        return status;
    }

    public void setCodeStatus(int status) {
        this.status = status;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
