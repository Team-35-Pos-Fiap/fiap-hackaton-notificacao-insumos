package br.com.gerenciamento.services;

import java.util.UUID;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import br.com.gerenciamento.dto.Item;

@FeignClient(name = "estoque-service")
public interface IEstoqueService {

	@GetMapping("/estoque/{id-unidade}/insumos")
	Item buscarInsumosPorUnidade(@PathVariable("id-unidade") UUID idUnidade);
}