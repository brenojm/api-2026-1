package com.serratec.monitoria.produto.service;

import com.serratec.monitoria.produto.entity.Produto;
import com.serratec.monitoria.produto.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;

    public List<Produto> listarTodos() {
        return produtoRepository.findAll();
    }

    public Produto criar(Produto produto) {
        return produtoRepository.save(produto);
    }

    public Produto atualizar(Long id, Produto produtoAtualizado) {
        Produto produtoExistente = produtoRepository.findById(id).get();

        produtoExistente.setNome(produtoAtualizado.getNome());
        produtoExistente.setDescription(produtoAtualizado.getDescription());
        produtoExistente.setPreco(produtoAtualizado.getPreco());

        return produtoRepository.save(produtoExistente);
    }

    public void deletar(Long id) {
        Produto produtoExistente = produtoRepository.findById(id).get();
        produtoRepository.delete(produtoExistente);
    }
}
