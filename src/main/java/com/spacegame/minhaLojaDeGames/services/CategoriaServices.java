package com.spacegame.minhaLojaDeGames.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.spacegame.minhaLojaDeGames.models.Categoria;
import com.spacegame.minhaLojaDeGames.models.Produto;
import com.spacegame.minhaLojaDeGames.repositories.CategoriaRepository;
import com.spacegame.minhaLojaDeGames.repositories.ProdutoRepository.ProdutosRepository;


@Service
public class CategoriaServices {
	
	private @Autowired CategoriaRepository repositoryC;
	
	
	private @Autowired ProdutosRepository repositoryP;
	
	/**
	 * Método utilizado para cadastrar um novo usuário no sistema, validando sua existência.
	 * @param novoUsuario
	 * @since 1.0
	 * @return Optional vazio ou com entidade Usuario dentro
	 * @author Luciano
	 */
	
	public Optional<Object> cadastrarCategoria(Categoria novaCategoria){
		Optional<Object> categoriaExistente = Optional.ofNullable(repositoryC.findByNome(novaCategoria.getNomeCategoria())); 
		
		if(categoriaExistente.isPresent()) { 
			return Optional.empty(); 
		}else {
			return Optional.ofNullable(repositoryC.save(novaCategoria)); 
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
			Optional<Categoria> categoriaExistente = repositoryC.findById(idCategoria); 
			if(categoriaExistente.isPresent()) { 
				categoriaExistente.get().setNomeCategoria(atualizacaoCategoria.getNomeCategoria()); 
				categoriaExistente.get().setDescricaoCategoria(atualizacaoCategoria.getDescricaoCategoria()); 
				return Optional.ofNullable(repositoryC.save(categoriaExistente.get())); 
				
			} else {
				return Optional.empty();
			}
		}
			
			/**
			 * Método utilizado para cadastrar um novo produto e validar a existencia dele e da categoria.
			 * @param idCategoria
			 * @param novoProduto
			 * @author Luciano
			 * @since 1.0
			 * @return Optional com a entidade produto dentro ou vazio  
			 */
			
			public Optional<Object> cadastrarProduto(Long idCategoria, Produto novoProduto){
				Optional<Categoria> categoriaExistente = repositoryC.findById(idCategoria);
				Optional<Object> produtoExistente = repositoryP.findByNomeProduto(novoProduto.getNomeProduto());
				if(categoriaExistente.isPresent() && !produtoExistente.isPresent()) {
					novoProduto.setGerador(categoriaExistente.get());
					return Optional.ofNullable(repositoryP.save(novoProduto));
				} else {
					return Optional.empty();
				}
			}
			
			
			/**
			 * Método utilizado para atualizar os campos de nome do produto e da desenvolvedora do produto.
			 * @param idCategoria
			 * @param idProduto
			 * @param atualizacaoProduto
			 * @author Luciano
			 * @since 1.0 
			 * @return Retorna um Optional com entidade Produto atualizado ou um Optional vazio.
			 */
			public Optional<Object> atualizarProduto(Long idCategoria, Long idProduto, Produto atualizacaoProduto){
				Optional<Categoria> categoriaExistente = repositoryC.findById(idCategoria);
				Optional<Produto> produtoExistente = repositoryP.findById(idProduto);
				Optional<Object> nomeProdutoExistente = repositoryP.findByNomeProduto(atualizacaoProduto.getNomeProduto());
				
				
				if(categoriaExistente.isPresent() && produtoExistente.isPresent() && nomeProdutoExistente.isEmpty()) {
					produtoExistente.get().setNomeProduto(atualizacaoProduto.getNomeProduto());
					produtoExistente.get().setNomeDesenvolvedora(atualizacaoProduto.getNomeDesenvolvedora());
					return Optional.ofNullable(repositoryP.save(produtoExistente.get()));
				} else {
					return Optional.empty();
				}
	}

}
