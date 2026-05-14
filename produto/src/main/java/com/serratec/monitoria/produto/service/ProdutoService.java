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

    public Produto buscarPorNome(String nome) {
        Optional<Produto> produto = produtoRepository.findByNomeIgnoreCase(nome);
        return produto.get();
    }

    public Produto criar(Produto produto) {
        return produtoRepository.save(produto);
    }

    public Produto atualizar(Long id, Produto produtoAtualizado) {
        Produto produtoExistente = produtoRepository.findById(id).get();

        if(produtoAtualizado.getNome() != null)
            produtoExistente.setNome(produtoAtualizado.getNome());
        if(produtoAtualizado.getDescription() != null)
            produtoExistente.setDescription(produtoAtualizado.getDescription());
        if(produtoAtualizado.getPreco() > 0)
            produtoExistente.setPreco(produtoAtualizado.getPreco());

        return produtoRepository.save(produtoExistente);
    }

    public void deletar(Long id) {
        Produto produtoExistente = produtoRepository.findById(id).get();
        produtoRepository.delete(produtoExistente);
    }
}
