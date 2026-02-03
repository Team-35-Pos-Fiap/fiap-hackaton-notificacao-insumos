package br.com.gerenciamento.services;

import java.util.List;
import java.util.UUID;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import br.com.gerenciamento.dto.InsumoDto;

@FeignClient(name = "insumos-service", url = "${host.servico.insumos}")
public interface IInsumoService {
	
	@GetMapping("/{id-unidade}")
	List<InsumoDto> buscarInsumosPorUnidade(@PathVariable("id-unidade") UUID idUnidade);
}