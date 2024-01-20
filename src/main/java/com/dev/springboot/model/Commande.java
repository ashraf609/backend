package com.dev.springboot.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;  // Importez la classe Date pour gérer les dates

@Entity
public class Commande {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long num_commande;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date dateCommande;  // Date de la commande
    private Long num_client;    // Numéro du client
    private Double montant;     // Montant total de la commande
    private String statut;      // Statut de la commande (ex: en attente, livrée, etc.)
    private String nomLivreur;  // Nom du livreur

    // Getters et Setters pour chaque attribut

    public Long getNum_commande() {
        return num_commande;
    }

    public void setNum_commande(Long num_commande) {
        this.num_commande = num_commande;
    }

    public Date getDateCommande() {
        return dateCommande;
    }

    public void setDateCommande(Date dateCommande) {
        this.dateCommande = dateCommande;
    }

    public Long getNum_client() {
        return num_client;
    }

    public void setNum_client(Long num_client) {
        this.num_client = num_client;
    }

    public Double getMontant() {
        return montant;
    }

    public void setMontant(Double montant) {
        this.montant = montant;
    }

    public String getStatut() {
        return statut;
    }

    public void setStatut(String statut) {
        this.statut = statut;
    }

    public String getNomLivreur() {
        return nomLivreur;
    }

    public void setNomLivreur(String nomLivreur) {
        this.nomLivreur = nomLivreur;
    }
}
