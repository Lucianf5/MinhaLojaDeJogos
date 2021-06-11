package com.spacegame.minhaLojaDeGames.services;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;


import com.spacegame.minhaLojaDeGames.models.Categoria;

import com.spacegame.minhaLojaDeGames.repositories.CategoriaRepository;



@Service
public class CategoriaServices {
	
	@Autowired
	CategoriaRepository clienteRepository;

	public Optional<Categoria> findById(Long id) {
		return clienteRepository.findById(id);
	}

	public List<Categoria> findByNome(String nome) {
		return clienteRepository.findByNomeContainingIgnoreCase(nome);
	}

	public Categoria save(Categoria categoria) {
		return clienteRepository.save(categoria);
	}

	public Categoria update(Categoria categoria) {
		if (categoria.getId() == null) {
			return null;
		}
		return clienteRepository.save(categoria);
	}

	public String delete(Long id) {
		if (clienteRepository.existsById(id)) {
			clienteRepository.deleteById(id);
			return "Categoria Deletada";
		} else {
			return "Categoria Não Existente";
		}

	}

	public List<Categoria> findAll() {
		return clienteRepository.findAll();
	}

}
