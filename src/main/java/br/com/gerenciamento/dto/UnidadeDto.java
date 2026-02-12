package br.com.gerenciamento.dto;

import java.util.UUID;

public record UnidadeDto(UUID id, String nome, UUID idResponsavel, String email) { }
