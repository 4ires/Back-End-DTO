package com.projetoDTO.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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

import com.projetoDTO.DTO.UsuarioDTO;
import com.projetoDTO.entities.Usuario;
import com.projetoDTO.service.UsuarioService;

import jakarta.validation.Valid;


@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/usuario")
public class UsuarioController {
		
		private final UsuarioService usuarioService;

		@Autowired
		public UsuarioController(UsuarioService usuarioService) {
			this.usuarioService = usuarioService;
		}

		@GetMapping ("/{id}")

		public ResponseEntity<Usuario> buscaUsuarioIdControlId (@ PathVariable Long id) {
			Usuario usuario = usuarioService.buscaPorId(id);
			if (usuario != null) {
				return ResponseEntity.ok(usuario);
			}
			else {
				return ResponseEntity.notFound().build();
			}
		}

		@GetMapping("/")
		public ResponseEntity<List<Usuario>> buscaTodosUsuarioControl(){
			List<Usuario> usuario = usuarioService.buscaTodos();
			return ResponseEntity.ok(usuario);
		}
		@PostMapping("/")
		public ResponseEntity<UsuarioDTO> salvaUsuarioControl(@RequestBody  @Valid UsuarioDTO usuarioDTO){
			UsuarioDTO salvaUsuarios= usuarioService.salvar(usuarioDTO);
			return ResponseEntity.status(HttpStatus.CREATED).body(salvaUsuarios);
		}
		@PutMapping("/{id}")
		public ResponseEntity<UsuarioDTO> alterarUsuarioControl(@PathVariable Long id, @RequestBody @Valid UsuarioDTO usuarioDTO){
			UsuarioDTO alterarUsuario = usuarioService.atualizar(id, usuarioDTO);
			if(alterarUsuario != null) {
				return ResponseEntity.ok(alterarUsuario);
			}
			else {
				return ResponseEntity.notFound().build();
			}
		}
		@DeleteMapping("/{id}")
		public ResponseEntity<Usuario> apagaUsuarioControl(@PathVariable Long id){
			boolean usuario = usuarioService.deletar(id);
			if (usuario) {
				return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
			}
			else {
				return ResponseEntity.notFound().build();
			}
		}
}
