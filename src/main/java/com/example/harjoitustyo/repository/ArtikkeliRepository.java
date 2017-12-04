package com.example.harjoitustyo.repository;


import com.example.harjoitustyo.domain.Artikkeli;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArtikkeliRepository extends JpaRepository<Artikkeli, Long> {

}
