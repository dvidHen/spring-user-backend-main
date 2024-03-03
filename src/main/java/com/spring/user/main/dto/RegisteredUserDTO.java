package com.spring.user.main.dto;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.util.Date;

/**
 * Clase que representa los datos de un usuario registrado.
 */
@JsonPropertyOrder({"id", "isactive", "created", "modified", "last_login", "token"})
public class RegisteredUserDTO {
    private String id;
    private int isactive;
    private String token;
    private Date last_login;
    private Date created;
    private Date modified;

    public RegisteredUserDTO(String id, int isactive, String token, Date last_login, Date created, Date modified) {
        this.id = id;
        this.isactive = isactive;
        this.token = token;
        this.last_login = last_login;
        this.created = created;
        this.modified = modified;
    }

    public RegisteredUserDTO() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getIsactive() {
        return isactive;
    }

    public void setIsactive(int isactive) {
        this.isactive = isactive;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Date getLast_login() {
        return last_login;
    }

    public void setLast_login(Date last_login) {
        this.last_login = last_login;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Date getModified() {
        return modified;
    }

    public void setModified(Date modified) {
        this.modified = modified;
    }
}
