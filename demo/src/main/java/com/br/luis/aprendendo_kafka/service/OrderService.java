package com.br.luis.aprendendo_kafka.service;

import java.util.Random;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.br.luis.aprendendo_kafka.demo.record.OrderRecord;

//Services são classes que ficam na camada de serviço da aplicação, e são responsáveis por implementar a lógica de negócio da aplicação
//Services são classes que ficam entre o controler e o repositório, e são responsáveis por implementar a lógica de negócio da aplicação
@Service // Essa anotação indica que essa classe é um service, e que ela vai ser
         // gerenciada pelo spring
public class OrderService {

    private final KafkaTemplate<String, OrderRecord> kafkaTemplateOrder;
    private final Random random = new Random();

    public OrderService(KafkaTemplate<String, OrderRecord> kafkaTemplateOrder) {
        this.kafkaTemplateOrder = kafkaTemplateOrder;
    }

    @SuppressWarnings("null")
    public void sendOrder(OrderRecord orderRecord) {

        int partition = random.nextInt(2);
        System.out.println("Sent message to partition: " + partition);
        System.out.println("Sending order: " + orderRecord.name());
        kafkaTemplateOrder.send("luis-order-processed", partition, null, orderRecord);

    }

}
