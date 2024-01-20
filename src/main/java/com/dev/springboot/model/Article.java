package com.dev.springboot.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Article {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long num_article;

    private Double prix;
    private Integer stock;
    private String image;
    private Integer stock_minimal;

    public Long getNum_article() {
        return num_article;
    }

    public void setNum_article(Long num_article) {
        this.num_article = num_article;
    }

    public Double getPrix() {
        return prix;
    }

    public void setPrix(Double prix) {
        this.prix = prix;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Integer getStock_minimal() {
        return stock_minimal;
    }

    public void setStock_minimal(Integer stock_minimal) {
        this.stock_minimal = stock_minimal;
    }
}
