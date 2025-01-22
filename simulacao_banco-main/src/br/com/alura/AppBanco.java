package br.com.alura;

import java.math.BigDecimal;

public class AppBanco {

    public static void main(String[] args) {
        Cliente cliente = new Cliente("João");
        Conta conta = new Conta(cliente, BigDecimal.valueOf(100));
        OperacaoSaque operacaoSaque = new OperacaoSaque(conta, BigDecimal.valueOf(100));

        Thread threadJoao = new Thread(operacaoSaque);
        Thread threadMaria = new Thread(operacaoSaque);

        threadJoao.start();
        threadMaria.start();

        try {
            threadJoao.join();
            threadMaria.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        System.out.println(Thread.currentThread().getName());
        System.out.println("Saldo final : " + conta.getSaldo());

    }


}
