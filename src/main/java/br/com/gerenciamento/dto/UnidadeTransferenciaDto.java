package br.com.gerenciamento.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record UnidadeTransferenciaDto(@JsonProperty(value = "dadosUnidade") UnidadeDto unidade, @JsonProperty(value = "quantidadeMaxima") Integer quantidade) {

}
