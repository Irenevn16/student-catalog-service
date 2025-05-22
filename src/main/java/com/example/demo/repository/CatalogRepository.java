package com.example.demo.repository;

import com.example.demo.DTO.CatalogDTO;
import com.example.demo.model.Catalog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CatalogRepository extends JpaRepository<Catalog, Long> {
}
