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

    public Cliente criar(Cliente cliente) {
        return clienteRepository.save(cliente);
    }
}
