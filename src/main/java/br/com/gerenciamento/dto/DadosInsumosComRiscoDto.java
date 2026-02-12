package br.com.gerenciamento.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public record DadosInsumosComRiscoDto(@JsonProperty(value = "insumosEmRisco") List<InsumoComRiscoDto> insumos) {

}
