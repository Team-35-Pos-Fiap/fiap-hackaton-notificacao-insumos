package br.com.gerenciamento.configs;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.admin.AdminClientConfig;
import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaAdmin;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonSerializer;

import br.com.gerenciamento.dto.MensagemDto;

@Configuration
public class KafkaConfig {

	@Value(value = "")
	private String hostKafka;
	
	@Bean
	public KafkaAdmin kafkaAdmin() {
		Map<String, Object> propriedades = new HashMap<>();
		
		propriedades.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, hostKafka);
		
		return new KafkaAdmin(propriedades);
	}
	
	@Bean
	public NewTopic topicInsumos() {
		return new NewTopic("insumos", null);
	}
	
	@Bean
	public ProducerFactory<String, MensagemDto> insumosProducerFactory() {
		Map<String, Object> propriedades = new HashMap<>();
		
		propriedades.put(JsonSerializer.ADD_TYPE_INFO_HEADERS, false);
		propriedades.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, hostKafka);
		propriedades.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
		propriedades.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
		
		return new DefaultKafkaProducerFactory<>(propriedades);
	}
	
	@Bean
	public KafkaTemplate<String, MensagemDto> insumosTemplate() {
		return new KafkaTemplate<>(insumosProducerFactory());
	}
}