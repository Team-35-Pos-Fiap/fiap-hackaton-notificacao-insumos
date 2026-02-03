package br.com.gerenciamento.services.impl;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.gerenciamento.dto.InsumoDto;
import br.com.gerenciamento.dto.MensagemDto;
import br.com.gerenciamento.dto.UnidadeDto;
import br.com.gerenciamento.services.IDadosInsumoService;
import br.com.gerenciamento.services.IMensagemService;

@Service
public class DadosInsumoService implements IDadosInsumoService {

	@Autowired
	private IMensagemService mensagemService;
	
	private String mensagemInsumoVencido = 
		"""
			O insumo %s - id: %s da unidade %s - id: %s venceu na data %s.
		""";
			
	private String mensagemInsumoAVencer = 
		"""
			O insumo %s - id: %s da unidade %s - id: %s está vencendo na data %s.
		""";
	

	private String mensagemInsumoComQuantidadeMinimaAtingida = 
		"""
			O insumo %s - id: %s da unidade %s - id: %s está com a quantidade mínima atingida.
		""";

	@Override
	public void verificarValidade(InsumoDto insumo, UnidadeDto unidade) {
		if(isInsumoVencido(insumo.dataVencimento())) {
			mensagemService.armazenar(montaMensagemInsumoVencido(insumo, unidade));
		} else if (isInsumoAVencer(insumo.dataVencimento())) {
			mensagemService.armazenar(montaMensagemInsumoAVencer(insumo, unidade));
		}
	}

	@Override
	public void verificarQuantidade(InsumoDto insumo, UnidadeDto unidade) {
		if(isQuantidadeMinimaAtingida(insumo)) {
			mensagemService.armazenar(montaMensagemInsumoComQuantidadeMinimaAtingida(insumo, unidade));
		}
	}
	
	private LocalDate recuperarDataAtual() {
		return LocalDate.now();
	}
	
	private boolean isInsumoVencido(LocalDate data) {
		return data.isAfter(recuperarDataAtual());
	}
	
	private boolean isInsumoAVencer(LocalDate data) {
		return data.isAfter(recuperarDataAtual().minusDays(30)) && data.isBefore(recuperarDataAtual());
	}
	
	private boolean isQuantidadeMinimaAtingida(InsumoDto insumo) {
		return insumo.quantidade() <= insumo.quantidadeMinima();
	}
	
	private MensagemDto montaMensagem(String mensagem, List<String> destinatarios) {
		return new MensagemDto(mensagem, destinatarios);
	}
	
	private MensagemDto montaMensagemInsumoVencido(InsumoDto insumo, UnidadeDto unidade) {
		return montaMensagem(atualizaMensagemValidadeInsumo(mensagemInsumoVencido, insumo, unidade), unidade.responsaveis());
	}
	
	private MensagemDto montaMensagemInsumoAVencer(InsumoDto insumo, UnidadeDto unidade) {
		return montaMensagem(atualizaMensagemValidadeInsumo(mensagemInsumoAVencer, insumo, unidade), unidade.responsaveis());
	}
	
	private MensagemDto montaMensagemInsumoComQuantidadeMinimaAtingida(InsumoDto insumo, UnidadeDto unidade) {
		return montaMensagem(atualizaMensagemQuantidadeInsumo(mensagemInsumoComQuantidadeMinimaAtingida, insumo, unidade), unidade.responsaveis());
	}
	
	private String atualizaMensagemValidadeInsumo(String mensagem, InsumoDto insumo, UnidadeDto unidade) {
		return String.format(mensagem, insumo.nome(), insumo.id(), unidade.nome(), unidade.id(), insumo.dataVencimento().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
	}
	
	private String atualizaMensagemQuantidadeInsumo(String mensagem, InsumoDto insumo, UnidadeDto unidade) {
		return String.format(mensagem, insumo.nome(), insumo.id(), unidade.nome(), unidade.id());
	}
}