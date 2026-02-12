package br.com.gerenciamento.services;

import br.com.gerenciamento.dto.MensagemDto;

public interface IMensagemService {
	void armazenar(MensagemDto mensagem);
}