package com.spacegame.minhaLojaDeGames.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.spacegame.minhaLojaDeGames.models.Produto;

public interface ProdutoRepository {
	
	@Repository
	public interface ProdutosRepository extends JpaRepository<Produto, Long> {
		

		List<Produto> findByNomeContainingIgnoreCase(String nome);

	}

	public boolean existsById(Long id);

	public void deleteById(Long id);

	public Produto save(Produto produto);

	public List<Produto> findByNomeContainingIgnoreCase(String nome);

	public Optional<Produto> findById(Long id);

	public List<Produto> findAll();
}
