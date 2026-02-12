package br.com.gerenciamento.services.impl;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.gerenciamento.dto.DadosInsumosComRiscoDto;
import br.com.gerenciamento.dto.InsumoComRiscoDto;
import br.com.gerenciamento.dto.UnidadeDto;
import br.com.gerenciamento.services.IDadosInsumoService;
import br.com.gerenciamento.services.IInteligenciaInsumoService;
import br.com.gerenciamento.services.INotificacaoInsumoService;
import br.com.gerenciamento.services.IUnidadeService;

@Service
public class NotificacaoInsumoService implements INotificacaoInsumoService {
	@Autowired
	private IUnidadeService unidadeService;

	@Autowired
	private IDadosInsumoService dadosInsumosService;

	@Autowired
	private IInteligenciaInsumoService inteligenciaInsumoService;

	@Override
	public void gerenciarInsumos() {
		trataUnidades(buscarUnidades());
	}

	private void trataUnidades(List<UnidadeDto> unidades) {
		unidades.stream().forEach(u -> gerenciarInsumosPorUnidade(u));
	}
	
	private void gerenciarInsumosPorUnidade(UnidadeDto unidade) {
		trataDadosInsumosComRisco(buscarInsumosComRiscoPorUnidade(unidade.id()), unidade);
	}

	private DadosInsumosComRiscoDto buscarInsumosComRiscoPorUnidade(UUID idUnidade) {
		return inteligenciaInsumoService.buscarInsumosComRiscoPorUnidade(idUnidade);
	}

	private void trataDadosInsumosComRisco(DadosInsumosComRiscoDto dados, UnidadeDto unidade) {
		dados.insumos().forEach(i -> trataInsumoComRisco(i, unidade));
	}
	
	private void trataInsumoComRisco(InsumoComRiscoDto insumo, UnidadeDto unidade) {
		dadosInsumosService.verificarRisco(insumo, unidade);
	}
	
	private List<UnidadeDto> buscarUnidades() {
		return unidadeService.buscarUnidades().unidades();
	}
}