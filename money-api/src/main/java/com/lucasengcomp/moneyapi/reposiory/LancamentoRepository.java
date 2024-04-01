package com.lucasengcomp.moneyapi.reposiory;

import com.lucasengcomp.moneyapi.model.Lancamento;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LancamentoRepository extends JpaRepository<Lancamento, Long> {
}
