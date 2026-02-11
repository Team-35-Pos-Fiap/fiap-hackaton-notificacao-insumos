package br.com.gerenciamento.dto;

import java.util.UUID;

public record UsuarioDto(UUID id, String nome, String email) {
}
