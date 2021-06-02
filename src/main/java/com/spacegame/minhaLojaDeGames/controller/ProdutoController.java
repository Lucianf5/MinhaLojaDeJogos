package com.spacegame.minhaLojaDeGames.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spacegame.minhaLojaDeGames.models.Produto;
import com.spacegame.minhaLojaDeGames.services.ProdutoServices;


@RestController
@RequestMapping("/produtos")
public class ProdutoController {
	
	@Autowired
	ProdutoServices service;

	@GetMapping("/exibir")
	public List<Produto> findAll() {
		return service.findAll();
	}

	@GetMapping("/id/{id}")
	public ResponseEntity<Produto> findById(@PathVariable Long id) {
		Optional<Produto> opt = service.findById(id);

		if (opt.isPresent())
			return ResponseEntity.status(HttpStatus.OK).body(opt.get());
		else
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
	}

	@GetMapping("/nome/{nome}")
	public ResponseEntity<List<Produto>> findByNome(@PathVariable String nome) {
		return ResponseEntity.status(HttpStatus.OK).body(service.findByNome(nome));
	}

	@PostMapping("/save")
	public ResponseEntity<Produto> save(@RequestBody Produto Produto) {
		return ResponseEntity.status(HttpStatus.CREATED).body(service.save(Produto));
	}

	@PutMapping("/update")
	public ResponseEntity<Produto> update(@RequestBody Produto Produto) {
		Produto j = service.update(Produto);
		if (j == null)
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();

		return ResponseEntity.status(HttpStatus.OK).body(j);
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<String> delete(@PathVariable Long id) {
		return ResponseEntity.status(HttpStatus.OK).body(service.delete(id));
	}

}
