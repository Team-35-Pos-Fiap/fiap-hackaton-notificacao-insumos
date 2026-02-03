package br.com.gerenciamento.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.gerenciamento.services.IGerenciadorInsumoService;

@RestController
@RequestMapping("/insumos")
public class GerenciadorInsumosResource {

	@Autowired
	private IGerenciadorInsumoService gerenciadorInsumoService;
	
	@PostMapping
	public void gerenciarInsumos() {
		gerenciadorInsumoService.gerenciarInsumos();
	}
}