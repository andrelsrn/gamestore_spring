package com.generation.buytoplay.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;
import java.util.Objects;

@Entity
@Table(name = "tb_produto")
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "O atributo nome é obrigatório!")
    private String nome;

    @NotBlank(message = "O atributo console é obrigatório!")
    private String console;

    @NotNull(message = "O atributo preco é obrigatório!")
    @Positive(message = "O atributo preco deve ser maior que zero!")
    private BigDecimal preco;

    @NotBlank(message = "O atributo quantidadeEstoque é obrigatório!")
    private Integer quantidadeEstoque;

    @Size(max = 5000, message = "O link da foto não pode ter mais de 5000 caracteres")
    private String imagem;

    @ManyToOne
    @JoinColumn(name = "categoria_id")
    private Categoria categoria;

    public Produto(Long id, String nome, String console, BigDecimal preco, Integer quantidadeEstoque, String imagem, Categoria categoria) {
        this.id = id;
        this.nome = nome;
        this.console = console;
        this.preco = preco;
        this.quantidadeEstoque = quantidadeEstoque;
        this.imagem = imagem;
        this.categoria = categoria;
    }

    public Produto() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getConsole() {
        return console;
    }

    public void setConsole(String console) {
        this.console = console;
    }

    public BigDecimal getPreco() {
        return preco;
    }

    public void setPreco(BigDecimal preco) {
        this.preco = preco;
    }

    public Integer getQuantidadeEstoque() {
        return quantidadeEstoque;
    }

    public void setQuantidadeEstoque(Integer quantidadeEstoque) {
        this.quantidadeEstoque = quantidadeEstoque;
    }

    public String getImagem() {
        return imagem;
    }

    public void setImagem(String imagem) {}

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Produto produto = (Produto) o;
        return id == produto.id;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
