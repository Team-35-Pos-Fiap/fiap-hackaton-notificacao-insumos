package br.com.gerenciamento.services;

import br.com.gerenciamento.dto.InsumoDto;
import br.com.gerenciamento.dto.UnidadeDto;

public interface IDadosInsumoService {
	void verificarValidade(InsumoDto insumo, UnidadeDto unidade);
	void verificarQuantidade(InsumoDto insumo, UnidadeDto unidade);
    void verificarRisco(InsumoDto insumo, UnidadeDto unidade);
}