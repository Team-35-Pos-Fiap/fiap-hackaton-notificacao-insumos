package br.com.gerenciamento.services;

import java.util.UUID;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import br.com.gerenciamento.dto.InsumoDto;

@FeignClient(name = "insumos-service")
public interface IInsumoService {

	@GetMapping("/insumos/{id}")
	InsumoDto buscarInsumoPorId(@PathVariable UUID id);
}