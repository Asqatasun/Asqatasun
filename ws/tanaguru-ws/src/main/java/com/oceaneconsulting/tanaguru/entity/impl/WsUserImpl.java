package com.oceaneconsulting.tanaguru.entity.impl;

import java.io.Serializable;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import com.oceaneconsulting.tanaguru.entity.WsUser;

/**
 * Classe de l'utilsateur
 *
 * @author msobahi
 *
 */
@Entity
@Table(name = "WS_USER")
@XmlRootElement
public class WsUserImpl implements WsUser, Serializable {

    /**
     *
     */
    private static final long serialVersionUID = -5995686793369202749L;

    /**
     * Identifiant
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_USER")
    private Long id;

    /**
     * Email de l'utilisateur
     */
    @Column(name = "EMAIL")
    private String email;

    /**
     * Le mot de passe
     */
    @Column(name = "PASSWORD")
    private String password;

    /**
     * Nom de l'utilisateur
     */
    @Column(name = "NAME")
    private String name;

    /**
     * Prnom de l'utilisateur
     */
    @Column(name = "FIRST_NAME")
    private String firstName;

    /**
     * Flag d'activit (0 ou 1)
     */
    @Column(name = "ACTIVE")
    private Boolean active;

    /**
     * Le role de l'utilisateur
     */
    @ManyToOne
    @JoinColumn(name = "ID_ROLE", nullable = false)
    private WsRoleImpl role;

    @Override
    public Long getId() {
        return this.id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String getEmail() {
        return email;
    }

    @Override
    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getFirstName() {
        return this.firstName;
    }

    @Override
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Override
    public Boolean getActive() {
        return this.active;
    }

    @Override
    public void setActive(Boolean active) {
        this.active = active;
    }

    @Override
    public WsRoleImpl getRole() {
        return this.role;
    }

    @Override
    public void setRole(WsRoleImpl role) {
        this.role = role;
    }

}
