package com.spacegame.minhaLojaDeGames.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import com.spacegame.minhaLojaDeGames.models.Categoria;

@Repository
public interface CategoriaRepository extends JpaRepository<Categoria, Long> {
	
	public Optional<Categoria> findByNome(String nome);
	
	List<Categoria> findAllByNomeContaining(String nome);
	
	public Optional<Categoria> findByDescricaoCategoria(String descricaoCategoria);
}
