package com.spacegame.minhaLojaDeGames.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.spacegame.minhaLojaDeGames.models.Categoria;
import com.spacegame.minhaLojaDeGames.repositories.CategoriaRepository;


@Service
public class CategoriaServices {
	
	private @Autowired CategoriaRepository repository;
	
	/**
	 * Método utilizado para cadastrar um novo usuário no sistema, validando sua existência.
	 * @param novoUsuario
	 * @since 1.0
	 * @return Optional vazio ou com entidade Usuario dentro
	 * @author Luciano
	 */
	
	public Optional<Object> cadastrarCategoria(Categoria novaCategoria){
		Optional<Object> categoriaExistente = Optional.ofNullable(repository.findByNome(novaCategoria.getNomeCategoria())); 
		
		if(categoriaExistente.isPresent()) { 
			return Optional.empty(); 
		}else {
			return Optional.ofNullable(repository.save(novaCategoria)); 
		}
	}
		
		/**
		 * Utilizada para atualizar os campos de nome e descrição da categoria.
		 * @param idCategoria - Long idCategoria
		 * @param atualizacaoCategoria - Entidade Categoria
		 * @author Luciano
		 * @since 1.0
		 * @return Retorna um Optional com entidade Categoria, caso ele exista. Caso contrário, um Optional vazio.
		 */
		
		public Optional<Categoria> atualizarCategoria(Long idCategoria, Categoria atualizacaoCategoria){ 
			Optional<Categoria> categoriaExistente = repository.findById(idCategoria); 
			if(categoriaExistente.isPresent()) { 
				categoriaExistente.get().setNomeCategoria(atualizacaoCategoria.getNomeCategoria()); 
				categoriaExistente.get().setDescricaoCategoria(atualizacaoCategoria.getDescricaoCategoria()); 
				return Optional.ofNullable(repository.save(categoriaExistente.get())); 
				
			} else {
				return Optional.empty();
			}
	}

}
