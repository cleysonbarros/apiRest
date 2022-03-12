package com.apiRest.controller;

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

import com.apiRest.model.Usuario;
import com.apiRest.repository.UsuarioRepository;

@RestController
@RequestMapping(value="/usuario")
public class IndexController {
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@GetMapping(value ="/{id}", produces = "application/json")
	public ResponseEntity<Usuario> init(@PathVariable Long id) {
	 Optional<Usuario> usuario = usuarioRepository.findById(id);   	
     return new ResponseEntity<>(usuario.get(), HttpStatus.OK);
	}

	@GetMapping(value = "/", produces = "application/json")
	public ResponseEntity<List<Usuario>> usuario () throws InterruptedException{
		
		List<Usuario> list = (List<Usuario>) usuarioRepository.findAll();
		
		return new ResponseEntity<List<Usuario>>(list, HttpStatus.OK);
	}
	
	@PostMapping(value ="/cadastrar" , produces = "application/json")
	public ResponseEntity<Usuario> cadastrar(@RequestBody Usuario usuario){
		for (int pos = 0; pos < usuario.getTelefone().size(); pos++) {
			usuario.getTelefone().get(pos).setUsuario(usuario);
		}
	
		Usuario usuarioSalvo = usuarioRepository.save(usuario);
		return new ResponseEntity<Usuario>(usuarioSalvo, HttpStatus.OK);
		
	}
	@PutMapping(value="/{idUser}/idVenda/{idVenda}", produces = "application/json")
	public ResponseEntity<Usuario>cadastraVendas(@RequestBody Usuario usuario){
		for (int pos = 0; pos < usuario.getTelefone().size(); pos++) {
			usuario.getTelefone().get(pos).setUsuario(usuario);
		}
		Usuario usuarioSalvo = usuarioRepository.save(usuario);
		return new ResponseEntity<Usuario>(usuarioSalvo, HttpStatus.OK);
	}
	
	@DeleteMapping(value ="/{id}")
	public void deletar(@PathVariable Long id) {
		usuarioRepository.deleteById(id);
	}

}
