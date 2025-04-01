package com.mio.todosimple.Selic.Logica;

import com.mio.todosimple.Selic.Service.SelicService;

import java.util.Scanner;

public class LogicaSelicUser {

    Scanner scanner = new Scanner(System.in);

    public final SelicService selicService;

    public LogicaSelicUser(SelicService selicService) {
        this.selicService = selicService;

    }

    public void selicUserValue (){
        Double selicDouble = selicService.getUltimaSelic();
        double selicTaxaMensal = selicDouble / 12 / 100;
        System.out.println("Digite o valor desejado: ");
        if (scanner.hasNextDouble()){
            double userValor = scanner.nextDouble();
            double rendimento = userValor * selicTaxaMensal;
            System.out.println("Rendimento mensal de: " + rendimento );
            System.out.println("Rendimento anual: " + rendimento * 12);

        } else System.out.println("Entrada inválida, tente novamente digitando apenas números");

    }
}
