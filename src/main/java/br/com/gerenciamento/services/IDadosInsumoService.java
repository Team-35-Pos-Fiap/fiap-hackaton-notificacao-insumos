package br.com.gerenciamento.services;

import br.com.gerenciamento.dto.InsumoDto;
import br.com.gerenciamento.dto.UnidadeDto;

public interface IDadosInsumoService {
	void verificarValidade(InsumoDto medicamento, UnidadeDto unidade);
	void verificarQuantidade(InsumoDto medicamento, UnidadeDto unidade);
}