package com.spring.user.main.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;
import java.util.List;

/**
 * Clase que representa a un usuario cliente en el sistema.
 */
@Entity
@Table(name = "USER_CUSTOMER", schema = "PUBLIC",
        indexes = {@Index(name = "idx_email", columnList = "email")},
        uniqueConstraints = {@UniqueConstraint(columnNames = {"email"}, name = "uk_email")})
public class UserCustomer {
    @Id
    private String id;
    @Column(name = "name")
    private String name;
    @Column(name = "email")
    private String email;
    @Column(name = "password")
    private String password;
    @Column(name = "isactive")
    private int isactive;
    @Column(name = "token")
    private String token;
    @Column(name = "last_login")
    private Date last_login;
    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "CREATED", updatable = false)
    private Date created;
    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "MODIFIED")
    private Date modified;
    @OneToMany(mappedBy = "userCustomer", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<Phones> phones;

    public UserCustomer() {
    }
    public UserCustomer(String id, String name, String email, String password, int isactive, String token, Date last_login, List<Phones> phones) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.isactive = isactive;
        this.token = token;
        this.last_login = last_login;
        this.phones = phones;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public List<Phones> getPhones() {
        return phones;
    }

    public void setPhones(List<Phones> phones) {
        this.phones = phones;
    }

    /**
     * Convierte el objeto UserCustomer a formato JSON.
     *
     * @return Representación JSON del objeto.
     * @throws JsonProcessingException Si ocurre un error al procesar el JSON.
     */
    public String toJSON() throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(this);
    }
}
