package com.generation.buytoplay.repository;

import com.generation.buytoplay.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigDecimal;
import java.util.List;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {

    List<Produto> findAllByNomeContainingIgnoreCase(String nome);

    // Preço MENOR ou IGUAL ao informado, ordenado do maior para o menor (Decrescente)
    public List<Produto> findAllByPrecoLessThanEqualOrderByPrecoDesc(BigDecimal preco);

    // Preço MAIOR ou IGUAL ao informado, ordenado do menor para o maior (Crescente)
    public List<Produto> findAllByPrecoGreaterThanEqualOrderByPrecoAsc(BigDecimal preco);
}
