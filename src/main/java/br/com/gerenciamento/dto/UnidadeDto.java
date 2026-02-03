package br.com.gerenciamento.dto;

import java.util.List;
import java.util.UUID;

public record UnidadeDto(UUID id, String nome, List<String> responsaveis) { }
