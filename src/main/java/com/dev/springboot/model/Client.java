package com.dev.springboot.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Entity
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long num_client; // Numéro du client

    private String nom; // Nom du client
    private String email; // Email du client
    private String image; // Image du client (URL or path)
    private String adresse; // Adresse du client
    private Double solde; // Solde du client
    private boolean statut; // Statut du client (actif/inactif)

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date dateInscription; // Date d'inscription du client

    // Constructeur par défaut
    public Client() {
    }

    // Getters et Setters

    public Long getNum_client() {
        return num_client;
    }

    public void setNum_client(Long num_client) {
        this.num_client = num_client;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public Double getSolde() {
        return solde;
    }

    public void setSolde(Double solde) {
        this.solde = solde;
    }

    public boolean isStatut() {
        return statut;
    }

    public void setStatut(boolean statut) {
        this.statut = statut;
    }

    public Date getDateInscription() {
        return dateInscription;
    }

    public void setDateInscription(Date dateInscription) {
        this.dateInscription = dateInscription;
    }
}
