package com.generation.buytoplay.repository;

import com.generation.buytoplay.model.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {

    List<Categoria> findAllByGeneroContainingIgnoreCase(String genero);
}
