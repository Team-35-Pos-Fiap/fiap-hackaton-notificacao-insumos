package br.com.gerenciamento.services;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import br.com.gerenciamento.dto.UnidadeDto;

@FeignClient(name = "unidades-service", url = "${host.servico.unidades}")
public interface IUnidadeService {
	
	@GetMapping("")
	List<UnidadeDto> buscarUnidades();
}