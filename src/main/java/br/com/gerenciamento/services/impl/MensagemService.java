package br.com.gerenciamento.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import br.com.gerenciamento.dto.MensagemDto;
import br.com.gerenciamento.services.IMensagemService;

@Service
public class MensagemService implements IMensagemService {

	//@Autowired
	//private KafkaTemplate<String, MensagemDto> kafkaTemplate;
	
	@Override
	public void armazenar(MensagemDto mensagem) {
	//	kafkaTemplate.send("insumos", mensagem);
	}
}