package br.com.gerenciamento.dto;

import java.util.UUID;

public record DadosInsumoDto(UUID id, String nome, String descricao) {
}