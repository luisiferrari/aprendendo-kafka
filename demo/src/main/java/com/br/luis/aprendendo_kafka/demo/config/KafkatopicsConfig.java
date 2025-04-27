package com.br.luis.aprendendo_kafka.demo.config;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.admin.AdminClientConfig;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.KafkaAdmin;

@Configuration
public class KafkatopicsConfig {

    //Aqui eu pego a configuração do application.properties
    // e coloco na variavel bootstrapAddress
    // o valor dela é o endereço do kafka, no caso localhost:9092
    // o @Value é uma anotação do spring que injeta o valor da propriedade
    //Kafka admin abaixo está configurando e conectando o servidor kafka
    @Value(value = "${spring.kafka.bootstrap-servers}")
    private String bootstrapAddress;

    @Bean
    public KafkaAdmin kafkaAdmin() {
        Map<String, Object> configs = new HashMap<>();
        configs.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapAddress);
        return new KafkaAdmin(configs);

    }


    //Aqui eu estou criando o topico luis-order-processed
    //short é a quantidade de replicações do topico, no caso 1
    //e o 1 é a quantidade de partições do topico, no caso 1
    @Bean
    public NewTopic topicOrderProcessed(){
        return new NewTopic("luis-order-processed", 1, (short) 1);
    }
    
}

