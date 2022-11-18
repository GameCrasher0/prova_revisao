package com.example.Cliente.utils;

import com.example.Cliente.entity.Cliente;
import org.springframework.stereotype.Service;

@Service
public class Calculo {

    public Cliente calc(Cliente cliente){
        if(cliente.getReceita() > 10000000000.00){
            Double valorIdeal = cliente.getReceita() - 999999999.9;
            cliente.setReceitaTotal(valorIdeal);
            return cliente;
        }else{
            Double cincoPorcento = (cliente.getReceita() * 0.05);
            cliente.setReceitaTotal(cliente.getReceita() - cincoPorcento);
            return cliente;
        }
    }
}



