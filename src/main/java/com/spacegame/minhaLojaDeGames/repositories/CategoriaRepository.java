package com.spacegame.minhaLojaDeGames.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import com.spacegame.minhaLojaDeGames.models.Categoria;

	
	@Repository
	public interface CategoriaRepository extends JpaRepository<Categoria, Long>{
		List<Categoria> findByNomeContainingIgnoreCase(String nome);
	
}


