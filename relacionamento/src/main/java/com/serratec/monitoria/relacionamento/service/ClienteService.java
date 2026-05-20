package com.serratec.monitoria.relacionamento.service;

import com.serratec.monitoria.relacionamento.entity.Cliente;
import com.serratec.monitoria.relacionamento.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    public List<Cliente> listarTodos() {
        return clienteRepository.findAll();
    }

    public Cliente buscarPorId(Long id) {
        return clienteRepository.findById(id).orElseThrow(() -> new RuntimeException("Cliente não foi encontrado"));
    }

    public Cliente criar(Cliente cliente) {
        return clienteRepository.save(cliente);
    }

    public Cliente atualizar(Long id, Cliente clienteAtualizado) {
        Cliente cliente = buscarPorId(id);

        cliente.setNome(clienteAtualizado.getNome());

        if(clienteAtualizado.getEndereco() != null) {
            cliente.getEndereco().setBairro(clienteAtualizado.getEndereco().getBairro());
            cliente.getEndereco().setRua(clienteAtualizado.getEndereco().getRua());
        }

        return clienteRepository.save(cliente);
    }

    public void deletar(Long id) {
        Cliente cliente = buscarPorId(id);
        clienteRepository.delete(cliente);
    }
}
