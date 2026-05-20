package com.serratec.monitoria.relacionamento.service;

import com.serratec.monitoria.relacionamento.entity.Cliente;
import com.serratec.monitoria.relacionamento.entity.Pedido;
import com.serratec.monitoria.relacionamento.repository.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PedidoService {

    @Autowired
    PedidoRepository pedidoRepository;

    @Autowired
    ClienteService clienteService;

    public List<Pedido> listarTodos() {
        return pedidoRepository.findAll();
    }

    public Pedido buscarPorId(Long id) {
        return pedidoRepository.findById(id).orElseThrow(() -> new RuntimeException("Pedido não foi encontrado"));
    }

    public Pedido criar(Pedido pedido, Long clienteId) {

        Cliente cliente = clienteService.buscarPorId(clienteId);

        pedido.setCliente(cliente);

        return pedidoRepository.save(pedido);
    }

    public void deletar(Long id) {
        Pedido pedido = buscarPorId(id);
        pedidoRepository.delete(pedido);
    }


}
