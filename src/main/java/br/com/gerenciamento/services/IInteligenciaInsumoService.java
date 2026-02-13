package br.com.gerenciamento.services;

import java.util.UUID;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import br.com.gerenciamento.dto.DadosInsumosComRiscoDto;

@FeignClient(name = "inteligencia-service")
public interface IInteligenciaInsumoService {
	
	@GetMapping("/inteligencia/unidade/{id-unidade}")
    DadosInsumosComRiscoDto buscarInsumosComRiscoPorUnidade(@PathVariable("id-unidade") UUID idUnidade);
}