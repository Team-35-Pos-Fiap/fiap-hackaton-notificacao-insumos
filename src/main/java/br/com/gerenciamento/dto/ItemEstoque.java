package br.com.gerenciamento.dto;

import java.util.UUID;

public record ItemEstoque(UUID idUnidade, UUID idInsumo, Integer quantidade) {

}