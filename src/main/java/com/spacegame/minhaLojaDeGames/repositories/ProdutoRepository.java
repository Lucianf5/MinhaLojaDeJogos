package com.spacegame.minhaLojaDeGames.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.spacegame.minhaLojaDeGames.models.Produto;

public interface ProdutoRepository {
	
	@Repository
	public interface ProdutosRepository extends JpaRepository<Produto, Long> {
		
		public Optional<Object> findByNomeProduto(String nomeProduto);
		
		public List<Produto> findAllByNomeProdutoContaining(String nomeProduto);

	}
}
