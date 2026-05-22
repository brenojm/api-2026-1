package com.serratec.monitoria.relacionamento.service;

import com.serratec.monitoria.relacionamento.dto.PedidoRequestDTO;
import com.serratec.monitoria.relacionamento.entity.Cliente;
import com.serratec.monitoria.relacionamento.entity.Pedido;
import com.serratec.monitoria.relacionamento.entity.Produto;
import com.serratec.monitoria.relacionamento.repository.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PedidoService {

    @Autowired
    PedidoRepository pedidoRepository;

    @Autowired
    ClienteService clienteService;

    @Autowired
    ProdutoService produtoService;

    public List<Pedido> listarTodos() {
        return pedidoRepository.findAll();
    }

    public Pedido buscarPorId(Long id) {
        return pedidoRepository.findById(id).orElseThrow(() -> new RuntimeException("Pedido não foi encontrado"));
    }

    public Pedido criar(PedidoRequestDTO pedidoDTO) {
        Pedido pedido = new Pedido();
        pedido.setDescricao(pedidoDTO.getDescricao());

        Cliente cliente = clienteService.buscarPorId(pedidoDTO.getClienteId());
        pedido.setCliente(cliente);

        List<Produto> produtos = new ArrayList<>();
        double valorTotal = 0;

        for(Long produtoId : pedidoDTO.getProdutosIds()) {
            Produto produto = produtoService.buscarPorId(produtoId);
            valorTotal += produto.getPreco();
            produtos.add(produto);
        }
        pedido.setValor(valorTotal);
        pedido.setProdutos(produtos);


        return pedidoRepository.save(pedido);
    }

    public void deletar(Long id) {
        Pedido pedido = buscarPorId(id);
        pedidoRepository.delete(pedido);
    }


}
