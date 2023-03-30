package com.demo.project;

import java.util.Map;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;

import com.demo.project.deserializer.CustomAvroDeserializer;
import com.demo.project.listener.SampleObject;

@SpringBootApplication
@EnableKafka
public class DemoApplication implements CommandLineRunner {

	@Autowired
	KafkaProperties kafkaProperties;
	
	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Thread.currentThread().join();
	}

	@Bean
	public ConsumerFactory<String, SampleObject> consumerFactory() {
		Map<String, Object> consumerProperties = kafkaProperties.buildConsumerProperties();
		consumerProperties.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
		consumerProperties.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, CustomAvroDeserializer.class);
		return new DefaultKafkaConsumerFactory<>(consumerProperties, new StringDeserializer(),
				new CustomAvroDeserializer<>(SampleObject.class));
	}

	// This bean is needed because otherwise it cannot parse String to SampleObject - it doesn't know which deserializer to use
	@Bean
	public ConcurrentKafkaListenerContainerFactory<String, SampleObject> kafkaListenerContainerFactory() {
		ConcurrentKafkaListenerContainerFactory<String, SampleObject> factory = new ConcurrentKafkaListenerContainerFactory<>();
		factory.setConsumerFactory(consumerFactory());
		return factory;
	}

}
