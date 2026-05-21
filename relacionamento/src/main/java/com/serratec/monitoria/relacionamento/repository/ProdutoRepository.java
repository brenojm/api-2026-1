package com.serratec.monitoria.relacionamento.repository;

import com.serratec.monitoria.relacionamento.entity.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long> {
}
