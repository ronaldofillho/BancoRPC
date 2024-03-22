package com.gugawag.rpc.banco;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;

public class AppClienteBanco {

    public static void main(String[] args) throws RemoteException, NotBoundException, MalformedURLException {
        // procura o serviço no RMI Registry local. Perceba que o cliente não connhece a implementação do servidor,
        // apenas a interface
        Registry registry = LocateRegistry.getRegistry(args[0]);
        BancoServiceIF banco = (BancoServiceIF) registry.lookup("BancoService");

        menu();
        Scanner entrada = new Scanner(System.in);
        int opcao = entrada.nextInt();

        while(opcao != 9) {
            switch (opcao) {
                case 1: {
                    System.out.println("Digite o número da conta:");
                    String conta = entrada.next();
                    //chamada ao método remoto, como se fosse executar localmente
                    System.out.println(banco.saldo(conta));
                    break;
                }
                case 2: {
                    //chamada ao método remoto, como se fosse executar localmente
                    System.out.println(banco.quantidadeContas());
                    break;
                }
                case 3: {
                    System.out.println("Digite o número da conta:");
                    String numero = entrada.next();
                    System.out.println("Digite o saldo inicial:");
                    double saldo = entrada.nextDouble();
                    banco.adicionarConta(numero, saldo);
                    System.out.println("Conta adicionada com sucesso!");
                    break;
                }
                case 4: {
                    System.out.println("Digite o número da conta:");
                    String numero = entrada.next();
                    Conta conta = banco.pesquisarConta(numero);
                    if (conta != null) {
                        System.out.println("Número da conta: " + conta.getNumero());
                        System.out.println("Saldo: " + conta.getSaldo());
                    } else {
                        System.out.println("Conta não encontrada!");
                    }
                    break;
                }
                case 5: {
                    System.out.println("Digite o número da conta:");
                    String numero = entrada.next();
                    banco.removerConta(numero);
                    System.out.println("Conta removida com sucesso!");
                    break;
                }
            }
            menu();
            opcao = entrada.nextInt();
        }
    }

    public static void menu() {
        System.out.println("\n=== BANCO RMI (ou FMI?!) ===");
        System.out.println("1 - Saldo da conta");
        System.out.println("2 - Quantidade de contas");
        System.out.println("3 - Adicionar conta");
        System.out.println("4 - Pesquisar conta");
        System.out.println("5 - Remover conta");
        System.out.println("9 - Sair");
    }


}
