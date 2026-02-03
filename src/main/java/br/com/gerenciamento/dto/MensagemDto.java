package br.com.gerenciamento.dto;

import java.util.List;

public record MensagemDto(String email, List<String> destinatarios) {}