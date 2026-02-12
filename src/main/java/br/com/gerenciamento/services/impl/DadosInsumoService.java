package br.com.gerenciamento.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.gerenciamento.dto.InsumoComRiscoDto;
import br.com.gerenciamento.dto.MensagemDto;
import br.com.gerenciamento.dto.UnidadeDto;
import br.com.gerenciamento.dto.UnidadeTransferenciaDto;
import br.com.gerenciamento.services.IDadosInsumoService;
import br.com.gerenciamento.services.IEmailService;

@Service
public class DadosInsumoService implements IDadosInsumoService {
	//@Autowired
	//private IMensagemService mensagemService;

	@Autowired
	private IEmailService emailService;
	
	private static final String TITULO_MENSAGEM_INSUMO_RISCO_MEDIO = "Insumo com risco médio.";

	private static final String TITULO_MENSAGEM_INSUMO_RISCO_ALTO = "Insumo com risco alto.";
	
	private String mensagemInsumoComRiscoMedio = """		
		<html>
			<body>
				Foi identificado que o insumo %s - %s da unidade %s está com risco médio. <br/>
				
				Quantidade necessária: %d. <br/>
				
				O insumo pode ser obtido das seguintes unidades: <br/>
				
				%s
			</body>
		</html>
		""";
	
	private String mensagemInsumoComRiscoAlto = """		
			<html>
				<body>
					Foi identificado que o insumo %s - %s da unidade %s está com risco alto, faltando %d dias para o vencimento.
				</body>
			</html>
			""";

	@Override
	public void verificarRisco(InsumoComRiscoDto insumo, UnidadeDto unidade) {
		if(insumo.risco().equals("MEDIO")) {
			trataRiscoMedio(insumo, unidade);
		} else {
			trataRiscoAlto(insumo, unidade);
		}
	}

	private void trataRiscoAlto(InsumoComRiscoDto insumo, UnidadeDto unidade) {
		//salvaMensagem(montaMensagemRiscoAlto(insumo), TITULO_MENSAGEM_INSUMO_RISCO_ALTO, unidade.email());
		
		enviarMensagem(montaMensagemRiscoAlto(insumo, unidade), TITULO_MENSAGEM_INSUMO_RISCO_ALTO, unidade.email());
	}

	private void trataRiscoMedio(InsumoComRiscoDto insumo, UnidadeDto unidade) {
		//salvaMensagem(montaMensagemRiscoMedio(insumo), TITULO_MENSAGEM_INSUMO_RISCO_MEDIO, unidade.email());
		
		enviarMensagem(montaMensagemRiscoMedio(insumo, unidade), TITULO_MENSAGEM_INSUMO_RISCO_MEDIO, unidade.email());
	}
	
	/*private void salvaMensagem(String mensagem, String titulo, String destinatario) {
		mensagemService.armazenar(montaMensagem(mensagem, titulo, destinatario));
	}*/
	
	private void enviarMensagem(String mensagem, String titulo, String destinatario) {
		emailService.enviarEmail(montaMensagem(mensagem, destinatario, titulo));
	}

	private String montaMensagemUnidadesParaTransferencia(List<UnidadeTransferenciaDto> dadosUnidades) {
		String mensagem = "Unidade: %s - Quantidade: %d.";
		
		return dadosUnidades.stream()
							.map(d -> "  <li>" + String.format(mensagem, d.unidade().nome(), d.quantidade()) + "  </li>")
							.collect(Collectors.joining("\n", "<ul>\n", "\n</ul>"));		
	}

	private String montaMensagemRiscoMedio(InsumoComRiscoDto insumo, UnidadeDto unidade) {
		return String.format(mensagemInsumoComRiscoMedio, insumo.dados().id(), insumo.dados().nome(), unidade.nome(),
							 insumo.quantidadeNecessaria(), montaMensagemUnidadesParaTransferencia(insumo.unidadesTransferencia()));
	}
	
	private String montaMensagemRiscoAlto(InsumoComRiscoDto insumo, UnidadeDto unidade) {
		return String.format(mensagemInsumoComRiscoAlto, insumo.dados().id(), insumo.dados().nome(), unidade.nome(), insumo.dias());
	}
	
	private MensagemDto montaMensagem(String mensagem, String destinatario, String titulo) {
		return new MensagemDto(mensagem, destinatario, titulo);
	}
}