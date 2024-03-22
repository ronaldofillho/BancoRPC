package com.gugawag.rpc.banco;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;
import java.util.Map;

public class BancoServiceServer extends UnicastRemoteObject implements BancoServiceIF {

    private Map<String, Double> saldoContas;

    private Map<String, Conta> contas;

    public BancoServiceServer() throws RemoteException {
        saldoContas = new HashMap<>();
        contas = new HashMap<>();
        saldoContas.put("1", 100.0);
        saldoContas.put("2", 156.0);
        saldoContas.put("3", 950.0);
        contas.put("1", new Conta("1", 100.0));
        contas.put("2", new Conta("2", 156.0));
        contas.put("3", new Conta("3", 950.0));
    }

    @Override
    public double saldo(String conta) throws RemoteException {
        return saldoContas.get(conta);
    }

    @Override
    public int quantidadeContas() throws RemoteException {
        return saldoContas.size();
    }

    @Override
    public void adicionarConta(String numero, double saldo) throws RemoteException {
        Conta conta = new Conta(numero, saldo);
        contas.put(numero, conta);
        saldoContas.put(numero, saldo);
    }

    @Override
    public Conta pesquisarConta(String numero) throws RemoteException {
        return contas.get(numero);
    }

    @Override
    public void removerConta(String numero) throws RemoteException {
        contas.remove(numero);
        saldoContas.remove(numero);
    }
}
