package com.example.demo.Lista;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ListaRepository extends JpaRepository<ListaJPA, Long>{
    Optional<ListaJPA> findByTitulo(String nome);
}
