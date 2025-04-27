package com.br.luis.aprendendo_kafka.demo.record;

import java.math.BigDecimal;

public record OrderRecord(
    Long id,
    String name,
    String description,
    BigDecimal amount
) {
    
}
