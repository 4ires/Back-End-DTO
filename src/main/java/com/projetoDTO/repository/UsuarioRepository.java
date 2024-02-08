package com.projetoDTO.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.projetoDTO.entities.Usuario;

public interface UsuarioRepository  extends JpaRepository<Usuario, Long>{

}
