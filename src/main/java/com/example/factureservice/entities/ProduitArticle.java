package com.example.factureservice.entities;

import com.example.factureservice.models.Produit;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProduitArticle {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private long reference;
    private int quantite;
    private double prix;
    @ManyToOne
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Facture facture;
    @Transient
    private Produit produit;
}
