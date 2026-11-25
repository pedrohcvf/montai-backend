package com.pedrohcvf.montai.backend;

import org.junit.jupiter.api.Test;
import org.springframework.modulith.core.ApplicationModules;

public class ModularidadeTests {

    @Test
    void testeModularidade(){
        ApplicationModules.of(MontaiBackendApplication.class).verify();
    }

}
