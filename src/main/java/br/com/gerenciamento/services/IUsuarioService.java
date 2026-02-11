package br.com.gerenciamento.services;

import java.util.UUID;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import br.com.gerenciamento.dto.UsuarioDto;

@FeignClient(name = "usuarios-service", url = "${servico.usuarios}")
public interface IUsuarioService {

	@GetMapping("/usuarios/{id}")
	UsuarioDto buscarUsuario(@PathVariable UUID id);
}