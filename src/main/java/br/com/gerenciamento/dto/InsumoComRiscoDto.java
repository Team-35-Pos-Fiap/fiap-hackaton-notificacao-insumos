package br.com.gerenciamento.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public record InsumoComRiscoDto(@JsonProperty("dadosInsumo") DadosInsumoDto dados, String risco, Integer quantidadeNecessaria, 
								@JsonProperty("diasAteEsgotar") Integer dias,
								@JsonProperty("sugestoesTransferencia") List<UnidadeTransferenciaDto> unidadesTransferencia,
								@JsonProperty("sugestaoCompra") SugestaoCompraDto dadosSugestaoCompra) {

}