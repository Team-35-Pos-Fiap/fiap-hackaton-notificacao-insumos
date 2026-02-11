package br.com.gerenciamento.services;

import java.util.List;
import java.util.UUID;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import br.com.gerenciamento.dto.InsumoDto;

@FeignClient(name = "inteligencia-service", url = "${servico.inteligencia}")
public interface IInteligenciaInsumoService {
	
	@GetMapping("/inteligencia/{id-unidade}")
    List<InsumoDto> buscarInsumosComRiscoPorUnidade(@PathVariable("id-unidade") UUID idUnidade);
}