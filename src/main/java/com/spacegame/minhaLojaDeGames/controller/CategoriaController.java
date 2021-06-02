package com.spacegame.minhaLojaDeGames.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.spacegame.minhaLojaDeGames.models.Categoria;
import com.spacegame.minhaLojaDeGames.repositories.CategoriaRepository;
import com.spacegame.minhaLojaDeGames.repositories.ProdutoRepository.ProdutosRepository;
import com.spacegame.minhaLojaDeGames.services.CategoriaServices;

@RestController
@RequestMapping("/api/categorias")
public class CategoriaController {
	
	
	private @Autowired CategoriaRepository repositoryC;
	
	private @Autowired CategoriaServices services;
	
	private @Autowired ProdutosRepository repositoryP;
	
	@GetMapping
	public ResponseEntity<List<Categoria>> pegarTodas(){
		return ResponseEntity.status(202).body(repository.findAll());
	}
	

	@GetMapping("/id/{id_categoria}") 
	public ResponseEntity<Categoria> buscarCategoriaPorId(@PathVariable(value = "id_categoria") Long idCategoria) { 
		return repository.findById(idCategoria)
				.map(idExistente -> ResponseEntity.status(200).body(idExistente))
				.orElse(ResponseEntity.status(204).build()); 
	}	
	
	@GetMapping("/descricao") 
	public ResponseEntity<Categoria> buscarCategoriaPorDescricao(@PathVariable(value = "descricaoCategoria") String descricaoCategoria) { 
		return repository.findByDescricaoCategoria(descricaoCategoria)
				.map(descricaoExistente -> ResponseEntity.status(200).body(descricaoExistente))
				.orElse(ResponseEntity.status(204).build()); 
	}	
	
	
	
	@PostMapping("/salvar") 
	public Optional<Object> salvarCategoria(@Valid @RequestBody Categoria novaCategoria) {
		return Optional.ofNullable(services.cadastrarCategoria(novaCategoria)
				.map(categoriaExistente -> ResponseEntity.status(201).body(categoriaExistente))
				.orElse(ResponseEntity.status(400).body("Categoria já existe.")));
	}
	
	@PutMapping("/atualizar/{id_usuario}")
	public ResponseEntity<Categoria> atualizarCategoria(@PathVariable(value = "id_Categoria") Long idCategoria, @Valid @RequestBody Categoria atualizacaoCategoria) { 
		return services.atualizarCategoria(idCategoria, atualizacaoCategoria)
				.map(categoriaAtualizado -> ResponseEntity.status(201).body(categoriaAtualizado))
				.orElse(ResponseEntity.status(304).build()); 
		
	}
	
	@DeleteMapping
	public void deleteCategoria(@RequestParam long id) { 
		repository.deleteById(id);
	}
	
	@GetMapping("/produtos")
	public ResponseEntity<List<Produtos>> pegarTodos(){
		List<Produtos> listaDeProdutos = repositoryP.findAll();
		
		if(listaDeProdutos.isEmpty()) {
			return ResponseEntity.status(204).build();
		} else {
			return ResponseEntity.status(200).body(listaDeProdutos);
		}
	}
	
	@GetMapping("/produtos/id/{id_produto}")
	public ResponseEntity<Optional<Produtos>> buscarProdutoPorId(@PathVariable(value = "id_produto") Long idProduto) {
		Optional<Produtos> idDoProduto = repositoryP.findById(idProduto);
		
		if(idDoProduto.isEmpty()) {
			return ResponseEntity.status(204).build();
		} else {
			return ResponseEntity.status(200).body(idDoProduto);
		}
	}
	
	@GetMapping("/produtos/buscar")
	public ResponseEntity<Object> buscarProdutoPorNome(@RequestParam(defaultValue = "") String nomeProduto) {
		List<Produtos> listaDeProdutos = repositoryP.findAllByNomeProdutoContaining(nomeProduto);
		
		if(listaDeProdutos.isEmpty()) {
			return ResponseEntity.status(204).build();
		} else {
			return ResponseEntity.status(200).body(listaDeProdutos);
		}
	}
	
	@DeleteMapping("/produtos/{id_produto}")
	public void deletarProduto(@PathVariable(value = "id_produto") long idProduto) {
		repositoryP.deleteById(idProduto);
	}
}
