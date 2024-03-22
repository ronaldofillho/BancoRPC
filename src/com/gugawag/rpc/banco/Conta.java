package com.gugawag.rpc.banco;
import java.io.Serializable;

public class Conta implements Serializable{
    private String numero;
    private double saldo;


    public Conta(String numero, Double saldo){
        this.numero = numero;
        this.saldo = saldo;
    }

    public String getNumero(){
        return numero;
    }

    public Double getSaldo(){
        return saldo;
    }

    public void setSaldo(){
        this.saldo = saldo;
    }
}
