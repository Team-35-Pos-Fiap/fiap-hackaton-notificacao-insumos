package br.com.gerenciamento.services;

import br.com.gerenciamento.dto.InsumoComRiscoDto;
import br.com.gerenciamento.dto.UnidadeDto;

public interface IDadosInsumoService {
    void verificarRisco(InsumoComRiscoDto insumo, UnidadeDto unidade);
}