package com.br.luis.aprendendo_kafka.demo.controler;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.br.luis.aprendendo_kafka.demo.record.OrderRecord;
import com.br.luis.aprendendo_kafka.service.OrderService;

//Controlers servem para controlar as requisições que chegam na aplicação, e são responsáveis por receber as requisições e enviar as respostas
//Controlers são classes que ficam na camada de apresentação da aplicação, e são responsáveis por receber as requisições e enviar as respostas

@RestController //Essa anotação indica que essa classe é um controler, e que ela vai receber requisições HTTP
@RequestMapping("/order") //Essa anotação indica que essa classe vai receber requisições na URL /order
public class OrderControler {

    public final OrderService orderService;

    public OrderControler(OrderService orderService) {
        this.orderService = orderService;
    }
    
    //Essa anotação indica que esse método vai receber requisições POST na URL /order
    //O @RequestBody indica que o corpo da requisição vai ser convertido em um objeto OrderRecord
    @PostMapping
    public void createOrder(@RequestBody OrderRecord orderRecord) {
        orderService.sendOrder(orderRecord);
    }

}
