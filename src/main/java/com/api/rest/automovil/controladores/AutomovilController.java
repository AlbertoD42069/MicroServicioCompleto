package com.api.rest.automovil.controladores;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.api.rest.automovil.entidades.Automovil;
import com.api.rest.automovil.repositorios.AutomovilRepository;

@RestController
@RequestMapping("/api/automovil")
public class AutomovilController {

	@Autowired
	private AutomovilRepository automovilRepository;
	/*
	@GetMapping
	public ResponseEntity<Page<Automovil>> listarBibliotecas(Pageable pageable){
		return ResponseEntity.ok(automovilRepository.findAll(pageable));
	}
	*/
	
	@GetMapping
	public ResponseEntity<List<Automovil>> listarMarca(){
		return ResponseEntity.ok(automovilRepository.findAll());
	}
	
	@PostMapping
	public ResponseEntity<Automovil> guardarMarca(@Valid @RequestBody Automovil automovil){
		Automovil bibliotecaGuardada = automovilRepository.save(automovil);
		URI ubicacion = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(bibliotecaGuardada.getId()).toUri();
		return ResponseEntity.created(ubicacion).body(bibliotecaGuardada);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Automovil> actualizarMarca(@PathVariable Integer id,@Valid @RequestBody Automovil automovil){
		Optional<Automovil> marcaOptional = automovilRepository.findById(id);
		
		if(!marcaOptional.isPresent()){
			return ResponseEntity.unprocessableEntity().build();
		}
		
		automovil.setId(marcaOptional.get().getId());
		automovilRepository.save(automovil);
		
		return ResponseEntity.noContent().build();
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Automovil> eliminarMarca(@PathVariable Integer id){
		Optional<Automovil> marcaOptional = automovilRepository.findById(id);
		
		if(!marcaOptional.isPresent()){
			return ResponseEntity.unprocessableEntity().build();
		}
		
		automovilRepository.delete(marcaOptional.get());
		return ResponseEntity.noContent().build();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Automovil> obtenerMarcaPorId(@PathVariable Integer id){
		Optional<Automovil> marcaOptional = automovilRepository.findById(id);
		
		if(!marcaOptional.isPresent()){
			return ResponseEntity.unprocessableEntity().build();
		}
		
		return ResponseEntity.ok(marcaOptional.get());
	}
}
