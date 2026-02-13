package br.com.gerenciamento.services;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import br.com.gerenciamento.dto.UnidadeSaudeDto;

@FeignClient(name = "estabelecimento-saude")
public interface IUnidadeService {

	@GetMapping("/estabelecimento-saude/estabelecimentos")
	UnidadeSaudeDto buscarUnidades();
}