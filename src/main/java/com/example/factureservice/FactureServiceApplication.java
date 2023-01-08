package com.example.factureservice;

import com.example.factureservice.Service.ClientRestClient;
import com.example.factureservice.Service.ProduitRestClient;
import com.example.factureservice.entities.Facture;
import com.example.factureservice.entities.ProduitArticle;
import com.example.factureservice.models.Client;
import com.example.factureservice.models.Produit;
import com.example.factureservice.repos.FactureRepo;
import com.example.factureservice.repos.ProduitArticleRepo;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.hateoas.PagedModel;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

@SpringBootApplication
@EnableFeignClients
public class FactureServiceApplication implements CommandLineRunner {
    ClientRestClient clientRestClient;
    ProduitRestClient produitRestClient;
    FactureRepo factureRepo;
    ProduitArticleRepo produitArticleRepo;

    public FactureServiceApplication(ClientRestClient clientRestClient, ProduitRestClient produitRestClient,
                                     FactureRepo factureRepo, ProduitArticleRepo produitArticleRepo) {
        this.clientRestClient = clientRestClient;
        this.produitRestClient = produitRestClient;
        this.factureRepo = factureRepo;
        this.produitArticleRepo = produitArticleRepo;
    }

    public static void main(String[] args) {
        SpringApplication.run(FactureServiceApplication.class, args);
    }
    @Override
    public void run(String... args) throws Exception {
        Client client = clientRestClient.getClientById(1L);
        Facture f = factureRepo.save(new Facture(null, new Date(), null,
                client.getId(), client));
        List<Produit> produits = new ArrayList<>();
        PagedModel<Produit> listeProduitsDB = produitRestClient.pageProduit(0,3);
        listeProduitsDB.forEach(p -> {
            ProduitArticle produitArticle = new ProduitArticle();
            produitArticle.setReference(p.getId());
            produitArticle.setProduit(p);
            produitArticle.setQuantite(1 + new Random().nextInt(10));
            produitArticle.setFacture(f);
            produitArticle.setPrix(produitArticle.getQuantite() * p.getPrix());
            produitArticleRepo.save(produitArticle);
        });
    }
}

