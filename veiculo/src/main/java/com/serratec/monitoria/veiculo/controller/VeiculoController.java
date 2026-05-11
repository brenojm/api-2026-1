package com.serratec.monitoria.veiculo.controller;

import com.serratec.monitoria.veiculo.model.Veiculo;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/veiculos")
public class VeiculoController {

    private List<Veiculo> veiculos = new ArrayList<>();

    @PostMapping
    public ResponseEntity<Veiculo> adicionar(@RequestBody Veiculo veiculo) {
        veiculos.add(veiculo);

        return ResponseEntity.status(HttpStatus.CREATED).body(veiculo);
    }

    @GetMapping
    public ResponseEntity<List<Veiculo>> listar() {
        return ResponseEntity.ok(veiculos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Veiculo> buscar(@PathVariable Long id) {
        Optional<Veiculo> veiculoEncontrado = veiculos.stream().filter(v -> v.getId().equals(id)).findFirst();

        //Mudei o retorno que vimos na monitoria, agora verifica se encontrou, senão retorna 404 not found
        return veiculoEncontrado
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Veiculo> atualizar(@PathVariable Long id, @RequestBody Veiculo veiculoAtualizado) {
        for (Veiculo veiculo : veiculos) {
            if (veiculo.getId().equals(id)) {
                veiculo.setMarca(veiculoAtualizado.getMarca());
                veiculo.setModelo(veiculoAtualizado.getModelo());
                return ResponseEntity.ok(veiculo);
            }
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> remover(@PathVariable Long id) {
        boolean removido = veiculos.removeIf(veiculo -> veiculo.getId().equals(id));

        if (removido) {
            // Nesse caso, podemos utilizar o retorno de no content, é muito utilizado no delete justamente por não ter conteudo no retorno mas ainda indicar sucesso
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
