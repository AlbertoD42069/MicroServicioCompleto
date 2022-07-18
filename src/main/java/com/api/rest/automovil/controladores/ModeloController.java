package com.api.rest.automovil.controladores;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
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
import com.api.rest.automovil.entidades.Modelo;
import com.api.rest.automovil.repositorios.AutomovilRepository;
import com.api.rest.automovil.repositorios.ModeloRepository;


@CrossOrigin(value = "http://localhost:3000")
@RestController
@RequestMapping("/api/modelos")
public class ModeloController {

	@Autowired
	private ModeloRepository modeloRepository;
	
	@Autowired
	private AutomovilRepository automovilRepository;
	/*
	@GetMapping
	public ResponseEntity<Page<Modelo>> listarLibros(Pageable pageable){
		return ResponseEntity.ok(modeloRepository.findAll(pageable));
	}
	*/
	@GetMapping
	public ResponseEntity<List<Modelo>> listarModelo(){
		return ResponseEntity.ok(modeloRepository.findAll());
	}
	
	@PostMapping
	public ResponseEntity<Modelo> guardarModelo(@Valid @RequestBody Modelo modelo){
		Optional<Automovil> automovilOptional = automovilRepository.findById(modelo.getAutomovil().getId());
		
		if(!automovilOptional.isPresent()){
			return ResponseEntity.unprocessableEntity().build();
		}
		
		modelo.setAutomovil(automovilOptional.get());
		Modelo modeloGuardado = modeloRepository.save(modelo);
		URI ubicacion = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(modeloGuardado.getId()).toUri();
		
		return ResponseEntity.created(ubicacion).body(modeloGuardado);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Modelo> actualizarModelo(@Valid @RequestBody Modelo modelo,@PathVariable Integer id){
		Optional<Automovil> marcaOptional = automovilRepository.findById(modelo.getAutomovil().getId());
		
		if(!marcaOptional.isPresent()){
			return ResponseEntity.unprocessableEntity().build();
		}
		
		Optional<Modelo> modeloOptional = modeloRepository.findById(id);
		if(!modeloOptional.isPresent()){
			return ResponseEntity.unprocessableEntity().build();
		}
		
		modelo.setAutomovil(marcaOptional.get());
		modelo.setId(modeloOptional.get().getId());
		modeloRepository.save(modelo);
		
		return ResponseEntity.noContent().build();
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Modelo> eliminarModelo(@PathVariable Integer id){
		Optional<Modelo> modeloOptional = modeloRepository.findById(id);
		
		if(!modeloOptional.isPresent()){
			return ResponseEntity.unprocessableEntity().build();
		}
		
		modeloRepository.delete(modeloOptional.get());
		return ResponseEntity.noContent().build();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Modelo> obtenerModeloPorId(@PathVariable Integer id){
		Optional<Modelo> modeloOptional = modeloRepository.findById(id);
		
		if(!modeloOptional.isPresent()){
			return ResponseEntity.unprocessableEntity().build();
		}
		
		return ResponseEntity.ok(modeloOptional.get());
	}
}
