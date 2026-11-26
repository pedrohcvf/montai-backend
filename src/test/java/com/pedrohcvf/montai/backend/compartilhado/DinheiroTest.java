package com.pedrohcvf.montai.backend.compartilhado;

import org.junit.jupiter.api.Test;
import java.math.BigDecimal;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class DinheiroTest {

    @Test
    void devePadronizarDinheiro(){
        Dinheiro a = new Dinheiro(new BigDecimal("10.0"));
        Dinheiro b = new Dinheiro(new BigDecimal("10.00"));
        assertEquals(a,b);
    }

    @Test
    void naoDeveAceitarValorNulo(){
        assertThrows(IllegalArgumentException.class, ()-> new Dinheiro(null));
    }

    @Test
    void naoDeveAceitarValorNegativo(){
        assertThrows(IllegalArgumentException.class, ()-> new Dinheiro(new BigDecimal(-1)));
    }

    @Test
    void somarValor(){
        Dinheiro a = new Dinheiro(new BigDecimal("0.10"));
        Dinheiro b = new Dinheiro(new BigDecimal("0.20"));
        Dinheiro resultado = a.somar(b);
        assertEquals( new Dinheiro(new BigDecimal("0.30")), resultado);
    }

    @Test
    void naoDeveSubtrairParaNegativo(){
        Dinheiro a = new Dinheiro(new BigDecimal("5"));
        Dinheiro b = new Dinheiro(new BigDecimal("10"));
        assertThrows(IllegalArgumentException.class,()-> a.subtrair(b));
    }

    @Test
    void comissaoTest(){
        Dinheiro a = new Dinheiro(new BigDecimal("10.01"));
        Dinheiro resultado = a.percentual(new BigDecimal("12"));
        assertEquals(new BigDecimal("1.20"), resultado.valor());
    }

}
