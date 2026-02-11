package br.com.gerenciamento.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public record Item(@JsonProperty("insumos") List<ItemEstoque> itens) {

}
