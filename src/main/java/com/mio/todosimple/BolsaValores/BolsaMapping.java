package com.mio.todosimple.BolsaValores;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping
@RestController
public class BolsaMapping {

    private final BolsaService bolsaService;

    public BolsaMapping(BolsaService bolsaService) {
        this.bolsaService = bolsaService;
    }

    @GetMapping("/stock")
    public String bolsaValor (){
        return bolsaService.cotacao();
    }


}
