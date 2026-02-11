package br.com.gerenciamento.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public record UnidadeSaudeDto(@JsonProperty("estabelecimentos") List<UnidadeDto> unidades) {

}
