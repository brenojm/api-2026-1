package com.serratec.monitoria.relacionamento.controller;

import com.serratec.monitoria.relacionamento.entity.Pedido;
import com.serratec.monitoria.relacionamento.service.PedidoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {

    @Autowired
    private PedidoService pedidoService;

    @GetMapping
    public ResponseEntity<List<Pedido>> listarTodos() {
        return ResponseEntity.ok(pedidoService.listarTodos());
    }
    @GetMapping("/{id}")
    public ResponseEntity<Pedido> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(pedidoService.buscarPorId(id));
    }
    @PostMapping("/{id}")
    public ResponseEntity<Pedido> criar(@RequestBody Pedido pedido,@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.CREATED).body(pedidoService.criar(pedido, id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        pedidoService.deletar(id);
        return ResponseEntity.noContent().build();
    }


}
