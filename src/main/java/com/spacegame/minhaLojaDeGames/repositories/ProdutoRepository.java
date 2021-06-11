package com.spacegame.minhaLojaDeGames.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.spacegame.minhaLojaDeGames.models.Produto;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long> {
	
		

		List<Produto> findByNomeContainingIgnoreCase(String nome);

	}

