package com.spacegame.minhaLojaDeGames.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spacegame.minhaLojaDeGames.models.Produto;
import com.spacegame.minhaLojaDeGames.repositories.ProdutoRepository;

@Service
public class ProdutoServices {
	
	@Autowired
	private ProdutoRepository repository;

	public List<Produto> findAll() {
		return repository.findAll();
	}

	public Optional<Produto> findById(Long id) {
		return repository.findById(id);
	}

	public List<Produto> findByNome(String nome) {
		return repository.findByNomeContainingIgnoreCase(nome);
	}

	public Produto save(Produto produto) {
		return repository.save(produto);
	}

	public Produto update(Produto produto) {
		return repository.save(produto);
	}

	public String delete(Long id) {
		if (repository.existsById(id)) {
			repository.deleteById(id);
			return "Categoria Deletada";
		} else {
			return "Categoria Não Existente";
		}

	}
}


