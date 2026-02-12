package br.com.gerenciamento.services;

import br.com.gerenciamento.dto.MensagemDto;

public interface IEmailService {
	void enviarEmail(MensagemDto mensagem);
}
