package com.generation.buytoplay.controller;

import com.generation.buytoplay.model.Categoria;
import com.generation.buytoplay.repository.CategoriaRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categorias")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class CategoriaController {

    @Autowired
    private CategoriaRepository categoriaRepository;

    // 1. Listar todas
    @GetMapping
    public ResponseEntity<List<Categoria>> findAll() {
        return ResponseEntity.ok(categoriaRepository.findAll());
    }

    // 2. Buscar por ID
    @GetMapping("/{id}")
    public ResponseEntity<Categoria> findById(@PathVariable Long id) {
        return categoriaRepository.findById(id)
                .map(categoria -> ResponseEntity.ok(categoria))
                .orElse(ResponseEntity.notFound().build());
    }

    // 3. Buscar por Gênero (O método que faltava)
    @GetMapping("/genero/{genero}")
    public ResponseEntity<List<Categoria>> findByGenero(@PathVariable String genero) {
        return ResponseEntity.ok(categoriaRepository.findAllByGeneroContainingIgnoreCase(genero));
    }

    // 4. Cadastrar
    @PostMapping
    public ResponseEntity<Categoria> post(@Valid @RequestBody Categoria categoria) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(categoriaRepository.save(categoria));
    }

    // 5. Atualizar
    @PutMapping
    public ResponseEntity<Categoria> put(@Valid @RequestBody Categoria categoria) {
        if (categoria.getId() == null) {
            return ResponseEntity.badRequest().build();
        }
        return categoriaRepository.findById(categoria.getId())
                .map(existingCategoria -> ResponseEntity.ok(categoriaRepository.save(categoria)))
                .orElse(ResponseEntity.notFound().build());
    }

    // 6. Deletar (Corrigido para @PathVariable)
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        return categoriaRepository.findById(id)
                .map(categoria -> {
                    categoriaRepository.deleteById(id);
                    return ResponseEntity.noContent().<Void>build();
                })
                .orElse(ResponseEntity.notFound().build());
    }
}