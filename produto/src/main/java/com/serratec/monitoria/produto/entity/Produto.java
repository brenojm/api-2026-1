package com.serratec.monitoria.produto.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "produto")
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "O nome não pode ser vazio ou nulo")
    @Size(max = 50, message = "O nome deve ter no máximo 50 caracteres")
    @Column(length = 50, nullable = false, unique = true)
    private String nome;

    @NotBlank(message = "A description não pode ser vazia ou nula")
    @Column(name = "description",nullable = false)
    private String descricao;

    @Min(value = 0, message = "O preço não pode ser menor que 0")
    @Column(nullable = false)
    private double preco;

    @Min(value = 0, message = "A quantidade não pode ser menor que 0")
    @Column(nullable = false)
    private int quantidade;

    public Produto() {
    }

    public Produto(String nome, String descricao, double preco) {
        this.nome = nome;
        this.descricao = descricao;
        this.preco = preco;
    }

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

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }
}
