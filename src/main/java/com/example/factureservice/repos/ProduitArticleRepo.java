package com.example.factureservice.repos;

import com.example.factureservice.entities.ProduitArticle;
import com.example.factureservice.models.Produit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource
public interface ProduitArticleRepo  extends JpaRepository<ProduitArticle, Long> {
    List<Produit> findByFactureId(Long id);

}