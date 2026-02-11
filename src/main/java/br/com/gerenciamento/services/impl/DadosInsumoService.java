package br.com.gerenciamento.services.impl;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.gerenciamento.dto.InsumoDto;
import br.com.gerenciamento.dto.MensagemDto;
import br.com.gerenciamento.dto.UnidadeDto;
import br.com.gerenciamento.dto.UsuarioDto;
import br.com.gerenciamento.services.IDadosInsumoService;
import br.com.gerenciamento.services.IMensagemService;
import br.com.gerenciamento.services.IUsuarioService;

@Service
public class DadosInsumoService implements IDadosInsumoService {

	@Autowired
	private IMensagemService mensagemService;

	@Autowired
	private IUsuarioService usuarioService;

	private String mensagemInsumoVencido = """
				O insumo %s - id: %s da unidade %s - id: %s venceu na data %s.
			""";

	private String mensagemInsumoAVencer = """
				O insumo %s - id: %s da unidade %s - id: %s está vencendo na data %s.
			""";

	private String mensagemInsumoComQuantidadeMinimaAtingida = """
				O insumo %s - id: %s da unidade %s - id: %s está com a quantidade mínima atingida.
			""";

	private String tituloMensagemInsumoVencido = "Insumo Vencido";
	private String tituloMensagemInsumoAVencer = "Insumo a Vencer";
	private String tituloMensagemInsumoComQuantidadeMinimaAtingida = "Insumo com Quantidade Mínima Atingida";
	private String tituloMensagemInsumoEmRisco = "Insumo em Risco";

	@Override
	public void verificarValidade(InsumoDto insumo, UnidadeDto unidade) {
		if (isInsumoVencido(insumo.dataVencimento())) {
			mensagemService.armazenar(montaMensagemInsumoVencido(insumo, unidade));
		} else if (isInsumoAVencer(insumo.dataVencimento())) {
			mensagemService.armazenar(montaMensagemInsumoAVencer(insumo, unidade));
		}
	}

	@Override
	public void verificarQuantidade(InsumoDto insumo, UnidadeDto unidade) {
		if (isQuantidadeMinimaAtingida(insumo)) {
			mensagemService.armazenar(montaMensagemInsumoComQuantidadeMinimaAtingida(insumo, unidade));
		}
	}

	private LocalDate recuperarDataAtual() {
		return LocalDate.now();
	}

	private boolean isInsumoVencido(LocalDate data) {
		return data.isBefore(recuperarDataAtual());
	}

	private boolean isInsumoAVencer(LocalDate data) {
		return data.isAfter(recuperarDataAtual().minusDays(30)) && data.isBefore(recuperarDataAtual());
	}

	private boolean isQuantidadeMinimaAtingida(InsumoDto insumo) {
		return false;// return insumo.quantidade() <= insumo.quantidadeMinima();
	}

	private MensagemDto montaMensagem(String mensagem, String destinatario, String titulo) {
		return new MensagemDto(mensagem, destinatario, titulo);
	}

	private MensagemDto montaMensagemInsumoVencido(InsumoDto insumo, UnidadeDto unidade) {
		return montaMensagem(atualizaMensagemValidadeInsumo(mensagemInsumoVencido, insumo, unidade),
				buscarUsuario(unidade.idResponsavel()).email(), tituloMensagemInsumoVencido);
	}

	private MensagemDto montaMensagemInsumoAVencer(InsumoDto insumo, UnidadeDto unidade) {
		return montaMensagem(atualizaMensagemValidadeInsumo(mensagemInsumoAVencer, insumo, unidade),
				buscarUsuario(unidade.idResponsavel()).email(), tituloMensagemInsumoAVencer);
	}

	private MensagemDto montaMensagemInsumoComQuantidadeMinimaAtingida(InsumoDto insumo, UnidadeDto unidade) {
		return montaMensagem(
				atualizaMensagemQuantidadeInsumo(mensagemInsumoComQuantidadeMinimaAtingida, insumo, unidade),
				buscarUsuario(unidade.idResponsavel()).email(), tituloMensagemInsumoComQuantidadeMinimaAtingida);
	}

	private String atualizaMensagemValidadeInsumo(String mensagem, InsumoDto insumo, UnidadeDto unidade) {
		return String.format(mensagem, insumo.nome(), insumo.id(), unidade.nome(), unidade.id(),
				insumo.dataVencimento().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
	}

	private String atualizaMensagemQuantidadeInsumo(String mensagem, InsumoDto insumo, UnidadeDto unidade) {
		return String.format(mensagem, insumo.nome(), insumo.id(), unidade.nome(), unidade.id());
	}

	@Override
	public void verificarRisco(InsumoDto insumo, UnidadeDto unidade) {
		throw new UnsupportedOperationException("Unimplemented method 'verificarRisco'");
	}

	private UsuarioDto buscarUsuario(UUID id) {
		return usuarioService.buscarUsuario(id);
	}
}