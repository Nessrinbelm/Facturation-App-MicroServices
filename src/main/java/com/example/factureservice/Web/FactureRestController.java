package com.example.factureservice.Web;

import com.example.factureservice.Service.ClientRestClient;
import com.example.factureservice.Service.ProduitRestClient;
import com.example.factureservice.entities.Facture;
import com.example.factureservice.models.Produit;
import com.example.factureservice.repos.FactureRepo;
import com.example.factureservice.repos.ProduitArticleRepo;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class FactureRestController {
    FactureRepo factureRepo;
    ClientRestClient clientRestClient;
    ProduitRestClient produitRestClient;
    ProduitArticleRepo produitArticleRepo;
    public FactureRestController(FactureRepo factureRepo, ClientRestClient clientRestClient,
                                 ProduitRestClient produitRestClient, ProduitArticleRepo produitArticleRepo) {
        this.factureRepo = factureRepo;
        this.clientRestClient = clientRestClient;
        this.produitRestClient = produitRestClient;
        this.produitArticleRepo = produitArticleRepo;
    }

    @GetMapping("/factures/{id}")
    public Facture getFacture(@PathVariable Long id){
        Facture facture = factureRepo.findById(id).get();
        facture.setClient(clientRestClient.getClientById(facture.getIdClient()));
        facture.getListeProduits().forEach(produitArticle -> {
            Produit p = produitRestClient.getProduitById(produitArticle.getReference());
            produitArticle.setProduit(p);
        });
        return facture;
    }
    @GetMapping("/factures")
    public List<Facture> getAll(){
        System.out.println("liste factures");
        return factureRepo.findAll();
    }
}
