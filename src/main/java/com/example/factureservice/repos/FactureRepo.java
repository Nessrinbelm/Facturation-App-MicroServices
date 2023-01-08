package com.example.factureservice.repos;

import com.example.factureservice.entities.Facture;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface FactureRepo extends JpaRepository<Facture,Long> {
}