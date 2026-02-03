package br.com.gerenciamento.dto;

import java.time.LocalDate;
import java.util.UUID;

public record InsumoDto(UUID id, String nome, LocalDate dataVencimento, Integer quantidade, Integer quantidadeMinima) {}