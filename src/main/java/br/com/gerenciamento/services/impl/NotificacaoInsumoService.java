package br.com.gerenciamento.services.impl;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.gerenciamento.dto.InsumoDto;
import br.com.gerenciamento.dto.ItemEstoque;
import br.com.gerenciamento.dto.UnidadeDto;
import br.com.gerenciamento.dto.UsuarioDto;
import br.com.gerenciamento.services.IDadosInsumoService;
import br.com.gerenciamento.services.INotificacaoInsumoService;
import br.com.gerenciamento.services.IEstoqueService;
import br.com.gerenciamento.services.IInsumoService;
import br.com.gerenciamento.services.IInteligenciaInsumoService;
import br.com.gerenciamento.services.IUnidadeService;
import br.com.gerenciamento.services.IUsuarioService;

@Service
public class NotificacaoInsumoService implements INotificacaoInsumoService {

	@Autowired
	private IUnidadeService unidadeService;

	@Autowired
	private IDadosInsumoService dadosInsumosService;

	@Autowired
	private IEstoqueService estoqueService;

	@Autowired
	private IInteligenciaInsumoService inteligenciaInsumoService;

	@Autowired
	private IInsumoService insumoService;

	@Override
	public void gerenciarInsumos() {
		trataUnidades(buscarUnidades());
	}

	private void trataUnidades(List<UnidadeDto> unidades) {
		unidades.stream().forEach(u -> gerenciarInsumosPorUnidade(u));
	}

	private void gerenciarInsumosPorUnidade(UnidadeDto unidade) {
		// recebe a lista de insumos do estoque
		List<ItemEstoque> itens = buscarItensNoEstoque(unidade.id());

		itens.stream().forEach(i -> {
			// para cada item do estoque, recupera os dados do insumo
			InsumoDto insumo = buscarInsumoPorId(i.idInsumo());

			if (insumo != null) {
				dadosInsumosService.verificarValidade(insumo, unidade);
				dadosInsumosService.verificarQuantidade(insumo, unidade);
			}
		});

		// recuperar os insumos da unidade

		// trataInsumos(buscarInsumosPorUnidade(unidade.id()), unidade);
		// trataInsumosComRisco(unidade);
	}

	private List<ItemEstoque> buscarItensNoEstoque(UUID idUnidade) {
		return estoqueService.buscarInsumosPorUnidade(idUnidade).itens();
	}

	private List<InsumoDto> buscarInsumosPorUnidade(UUID idUnidade) {
		// return ins.buscarInsumosPorUnidade(idUnidade);
		return null;
	}

	private List<UnidadeDto> buscarUnidades() {
		return unidadeService.buscarUnidades().unidades();
	}

	private void trataInsumos(List<InsumoDto> insumos, UnidadeDto unidade) {
		insumos.stream().forEach(m -> {
			dadosInsumosService.verificarValidade(m, unidade);
			dadosInsumosService.verificarQuantidade(m, unidade);
		});
	}

	private void trataInsumosComRisco(UnidadeDto unidade) {
		List<InsumoDto> insumos = buscarInsumosComRiscoPorUnidade(unidade.id());

		insumos.stream().forEach(i -> {
			dadosInsumosService.verificarRisco(i, unidade);
		});
	}

	private List<InsumoDto> buscarInsumosComRiscoPorUnidade(UUID idUnidade) {
		return inteligenciaInsumoService.buscarInsumosComRiscoPorUnidade(idUnidade);
	}

	private InsumoDto buscarInsumoPorId(UUID idInsumo) {
		return insumoService.buscarInsumoPorId(idInsumo);
	}
}