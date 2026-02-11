package br.com.gerenciamento;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class NotificacaoInsumosApplication {

	public static void main(String[] args) {
		SpringApplication.run(NotificacaoInsumosApplication.class, args);
	}
}