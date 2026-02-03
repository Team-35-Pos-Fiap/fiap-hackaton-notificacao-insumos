package br.com.gerenciamento.services.impl;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.gerenciamento.dto.InsumoDto;
import br.com.gerenciamento.dto.UnidadeDto;
import br.com.gerenciamento.services.IDadosInsumoService;
import br.com.gerenciamento.services.IGerenciadorInsumoService;
import br.com.gerenciamento.services.IInsumoService;
import br.com.gerenciamento.services.IUnidadeService;

@Service
public class GerenciadorInsumoService implements IGerenciadorInsumoService{
	
	@Autowired
	private IUnidadeService unidadeService;
	
	@Autowired
	private IDadosInsumoService dadosInsumosService;

	@Autowired
	private IInsumoService insumosService;
	
	@Override
	public void gerenciarInsumos() {
		trataUnidades(buscarUnidades());
	}

	private void trataUnidades(List<UnidadeDto> unidades) {
		unidades.stream().forEach(u -> gerenciarInsumosPorUnidade(u));
	}
	
	private void gerenciarInsumosPorUnidade(UnidadeDto unidade) {
		trataInsumos(buscarInsumosPorUnidade(unidade.id()), unidade);
	}

	private List<InsumoDto> buscarInsumosPorUnidade(UUID idUnidade) {
		return insumosService.buscarInsumosPorUnidade(idUnidade);
	}
	
	private List<UnidadeDto> buscarUnidades() {
		return unidadeService.buscarUnidades();
	}
	
	private void trataInsumos(List<InsumoDto> insumos, UnidadeDto unidade) {
		insumos.stream().forEach(m -> {
			dadosInsumosService.verificarValidade(m, unidade);
			dadosInsumosService.verificarQuantidade(m, unidade);			
		});
	}
}