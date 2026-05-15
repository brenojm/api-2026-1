package com.serratec.monitoria.produto.service;

import com.serratec.monitoria.produto.entity.Produto;
import com.serratec.monitoria.produto.exception.ProdutoNaoEncontradoException;
import com.serratec.monitoria.produto.exception.RegraNegocioException;
import com.serratec.monitoria.produto.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;

    public Produto buscarPorId(Long id) {
        return produtoRepository.findById(id).orElseThrow(() -> new ProdutoNaoEncontradoException("Produto com id " + id + " não foi encontrado"));
    }


    public List<Produto> listarTodos() {
        return produtoRepository.findAll();
    }

    public Produto buscarPorNome(String nome) {
        Produto produto = produtoRepository.findByNomeIgnoreCase(nome).orElseThrow(() -> new ProdutoNaoEncontradoException("Produto com nome " + nome + " não foi encontrado"));
        return produto;
    }

    public Produto criar(Produto produto) {
        if(produtoRepository.existsByNomeIgnoreCase(produto.getNome())) {
            throw new RegraNegocioException("Já existe um produto com o nome " + produto.getNome());
        }
        return produtoRepository.save(produto);
    }

    public Produto atualizar(Long id, Produto produtoAtualizado) {
        Produto produtoExistente = buscarPorId(id);
        if(produtoRepository.existsByNomeIgnoreCase(produtoAtualizado.getNome())) {
            throw new RegraNegocioException("Já existe um produto com o nome " + produtoAtualizado.getNome());
        }
        if(produtoAtualizado.getNome() != null)
            produtoExistente.setNome(produtoAtualizado.getNome());
        if(produtoAtualizado.getDescricao() != null)
            produtoExistente.setDescricao(produtoAtualizado.getDescricao());
        if(produtoAtualizado.getPreco() > 0)
            produtoExistente.setPreco(produtoAtualizado.getPreco());

        return produtoRepository.save(produtoExistente);
    }

    public void deletar(Long id) {
        Produto produtoExistente = buscarPorId(id);
        produtoRepository.delete(produtoExistente);
    }

    public Produto adicionarEstoque(Long id, int quantidade) {
        if(quantidade < 0) {
            throw new RegraNegocioException("A quantidade deve ser maior que zero");
        }
        Produto produto = buscarPorId(id);
        produto.setQuantidade(produto.getQuantidade() + quantidade);
        return produtoRepository.save(produto);
    }

    public Produto venderProduto(Long id, int quantidade) {
        Produto produto = buscarPorId(id);
        if(produto.getQuantidade() < quantidade) {
            throw new RegraNegocioException("Estoque insuficiente para venda. Estoque: " + produto.getQuantidade());
        }
        produto.setQuantidade(produto.getQuantidade() - quantidade);
        return produtoRepository.save(produto);
    }
}
