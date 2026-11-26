package com.pedrohcvf.montai.backend.compartilhado.dinheiro;

import java.math.BigDecimal;
import java.math.RoundingMode;

public record Dinheiro(BigDecimal valor) {

    // Seta um padrão de 2 casas decimais para o dinheiro
    public Dinheiro {
        if (valor == null){
            throw new IllegalArgumentException("Valor inserido não pode ser nulo.");
        }
        if (valor.compareTo(BigDecimal.ZERO) < 0){
            throw new IllegalArgumentException("Valor inserido não pode ser negativo");
        }
        valor = valor.setScale(2, RoundingMode.HALF_EVEN);
    }

    public Dinheiro somar(Dinheiro entrada){
        return new Dinheiro(this.valor.add(entrada.valor));
    }

    public Dinheiro subtrair(Dinheiro entrada){
        return new Dinheiro(this.valor.subtract(entrada.valor));
    }

    public Dinheiro percentual(BigDecimal porcentagem){
        return new Dinheiro(this.valor.multiply(porcentagem.divide(BigDecimal.valueOf(100))));
    }

}
