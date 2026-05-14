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
    @Column(nullable = false)
    private String description;

    @Min(value = 0, message = "O preço não pode ser menor que 0")
    @Column(nullable = false)
    private double preco;

    @Min(value = 0, message = "A quantidade não pode ser menor que 0")
    @Column(nullable = false)
    private int quantidade;

    public Produto() {
    }

    public Produto(String nome, String description, double preco) {
        this.nome = nome;
        this.description = description;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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
