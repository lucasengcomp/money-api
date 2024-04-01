package com.lucasengcomp.moneyapi.reposiory;

import com.lucasengcomp.moneyapi.model.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {
}
