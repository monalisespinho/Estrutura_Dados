package Model;

import Model.Jogador;

import java.util.Scanner;

public class Imovel extends Casa {
    private double precoCompra;
    private double valorAluguel;
    private Jogador dono;
    private boolean hipotecado;
    private double valorHipoteca;

    public Imovel(String nome, double precoCompra, double valorAluguel) {
        super(nome);
        this.precoCompra = precoCompra;
        this.valorAluguel = valorAluguel;
        this.dono = null;
        this.hipotecado = false;
        this.valorHipoteca = precoCompra * 0.5;
    }

    public double getPrecoCompra() {
        return precoCompra;
    }

    public double getValorAluguel() {
        return valorAluguel;
    }

    public Jogador getDono() { return dono; }
    public void setDono(Jogador dono) { this.dono = dono; }

    public boolean isHipotecado() {
        return hipotecado;
    }

    public void setHipotecado(boolean hipotecado) {
        this.hipotecado = hipotecado;
    }

    public double getValorHipoteca() { return valorHipoteca; }
    public double getValorParaQuitar() { return valorHipoteca * 1.1; }

    @Override
    public void acao(Jogador jogador) {
        if (dono == null) {
            System.out.println("Este imóvel não tem proprietário.");
            System.out.printf("Preço de compra: R$ %.2f\n", precoCompra);
            Scanner sc = new Scanner(System.in);
            System.out.println("Deseja comprar este imóvel? (1 - Sim / 2 - Não)");
            String op = sc.nextLine();
            if (op.equals("1")) {
                if (jogador.getSaldo() >= precoCompra) {
                    jogador.setSaldo(jogador.getSaldo() - precoCompra);
                    dono = jogador;
                    jogador.getPropriedades().add(this);
                    System.out.println("Parabéns! Você comprou '" + nome + "' por R$ " + precoCompra);
                    System.out.printf("Seu novo saldo é: R$ %.2f\n", jogador.getSaldo());
                } else {
                    System.out.println("Saldo insuficiente para comprar este imóvel.");
                }
            } else {
                System.out.println("Você optou por não comprar o imóvel.");
            }
        } else if (!dono.equals(jogador)) {
            if (!hipotecado) {
                System.out.printf("Você deve pagar aluguel de R$ %.2f para %s.\n", valorAluguel, dono.getNome());
                if (jogador.getSaldo() >= valorAluguel) {
                    jogador.setSaldo(jogador.getSaldo() - valorAluguel);
                    dono.setSaldo(dono.getSaldo() + valorAluguel);
                } else {
                    System.out.println("Saldo insuficiente para pagar aluguel. Você está falido!");
                    jogador.setFalido(true);
                }
            } else {
                System.out.println("O imóvel está hipotecado, não é preciso pagar aluguel.");
            }
        } else {
            System.out.println("Você parou em um imóvel que você próprio possui.");
        }
    }
}
