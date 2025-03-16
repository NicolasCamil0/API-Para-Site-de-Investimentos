package com.mio.todosimple.controller;


import com.mio.todosimple.services.InvestmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class InvestimentController {

    @Autowired
    private InvestmentService investmentService;

    @GetMapping public String getStockData (@PathVariable String x){
        return investmentService.getStockData(x);
    }

}
