package com.generation.buytoplay.controller;

import com.generation.buytoplay.model.Produto;
import com.generation.buytoplay.repository.CategoriaRepository;
import com.generation.buytoplay.repository.ProdutoRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/produtos")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ProdutoController {

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private CategoriaRepository categoriaRepository;

    // 1. Listar todos os produtos
    @GetMapping
    public ResponseEntity<List<Produto>> findAll() {
        return ResponseEntity.ok(produtoRepository.findAll());
    }

    // 2. Buscar produto por ID
    @GetMapping("/{id}")
    public ResponseEntity<Produto> findById(@PathVariable Long id) {
        return produtoRepository.findById(id)
                .map(produto -> ResponseEntity.ok(produto))
                .orElse(ResponseEntity.notFound().build());
    }

    // 3. Buscar produtos por Nome (Busca por termo)
    @GetMapping("/nome/{nome}")
    public ResponseEntity<List<Produto>> findByNome(@PathVariable String nome) {
        return ResponseEntity.ok(produtoRepository.findAllByNomeContainingIgnoreCase(nome));
    }

    // Buscar produtos com preço MENOR ou igual ao valor passado (Ordem Decrescente)
    @GetMapping("/preco_menor/{preco}")
    public ResponseEntity<List<Produto>> getByPrecoMenorQue(@PathVariable BigDecimal preco) {
        return ResponseEntity.ok(produtoRepository.findAllByPrecoLessThanEqualOrderByPrecoDesc(preco));
    }

    // Buscar produtos com preço MAIOR ou igual ao valor passado (Ordem Crescente)
    @GetMapping("/preco_maior/{preco}")
    public ResponseEntity<List<Produto>> getByPrecoMaiorQue(@PathVariable BigDecimal preco) {
        return ResponseEntity.ok(produtoRepository.findAllByPrecoGreaterThanEqualOrderByPrecoAsc(preco));
    }

    // 4. Cadastrar Produto (Valida se a Categoria vinculada existe no banco)
    @PostMapping
    public ResponseEntity<Produto> post(@Valid @RequestBody Produto produto) {
        if (produto.getCategoria() == null || produto.getCategoria().getId() == null) {
            return ResponseEntity.badRequest().build();
        }

        if (categoriaRepository.existsById(produto.getCategoria().getId())) {
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(produtoRepository.save(produto));
        }

        return ResponseEntity.badRequest().build();
    }

    // 5. Atualizar Produto (Valida ID do produto e ID da categoria)
    @PutMapping
    public ResponseEntity<Produto> put(@Valid @RequestBody Produto produto) {
        if (produto.getId() == null || produto.getCategoria() == null || produto.getCategoria().getId() == null) {
            return ResponseEntity.badRequest().build();
        }

        if (produtoRepository.existsById(produto.getId())) {
            if (categoriaRepository.existsById(produto.getCategoria().getId())) {
                return ResponseEntity.ok(produtoRepository.save(produto));
            }
            return ResponseEntity.badRequest().build(); // Categoria informada não existe
        }

        return ResponseEntity.notFound().build(); // Produto não existe
    }

    // 6. Deletar Produto por ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        return produtoRepository.findById(id)
                .map(produto -> {
                    produtoRepository.deleteById(id);
                    return ResponseEntity.noContent().<Void>build();
                })
                .orElse(ResponseEntity.notFound().build());
    }
}