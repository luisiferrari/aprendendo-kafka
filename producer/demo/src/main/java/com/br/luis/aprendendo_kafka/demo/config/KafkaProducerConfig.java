package com.br.luis.aprendendo_kafka.demo.config;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.producer.ProducerConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonSerializer;

import com.br.luis.aprendendo_kafka.demo.record.OrderRecord;
import com.fasterxml.jackson.databind.ser.std.StringSerializer;

@Configuration
public class KafkaProducerConfig {

    //Pegando o endereço do kafka do application.properties
    //o @Value é uma anotação do spring que injeta o valor da propriedade
    @Value(value = "${spring.kafka.bootstrap-servers}")
    private String bootstrapAddress;


    @Bean
    public ProducerFactory<String, OrderRecord> producerFactory() {
        Map<String, Object> configProps = new HashMap<>();
        configProps.put(JsonSerializer.ADD_TYPE_INFO_HEADERS, false);//Toda vez que eu enviar uma mensagem, o spring vai adicionar um cabeçalho com o tipo do objeto por padrao (true). No meu caso eu não quero isso, então eu coloco false
        configProps.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapAddress);//endereço de onde vou conectar
        configProps.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);//chave da mensagem
        configProps.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);//o valor da mensagem, nesse caso eu to passando string, string, mas poderia passar string e objeto que gostaria de passar, que nesse caso iria como um json
        //JsonSerializer é uma classe do spring que transforma o objeto em json, e o StringSerializer transforma a string em bytes
        return new DefaultKafkaProducerFactory<>(configProps);
    }

    //Feita toda configuração acima, agora eu crio o KafkaTemplate, que é a classe que vai enviar as mensagens para o kafka
    @Bean
    public KafkaTemplate<String, OrderRecord> orderKafkaTemplate() {
        return new KafkaTemplate<>(producerFactory());
    }
    
}
