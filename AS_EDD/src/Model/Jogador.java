package Model;

import java.util.ArrayList;
import java.util.List;

public class Jogador {
    private String nome;
    private double saldo;
    private Casa posicaoAtual;
    private List<Imovel> propriedades;
    private boolean falido;

    public Jogador(String nome, double saldoInicial, Casa posicaoInicial) {
        this.nome = nome;
        this.saldo = saldoInicial;
        this.posicaoAtual = posicaoInicial;
        this.propriedades = new ArrayList<>();
        this.falido = false;
    }

    public String getNome() {
        return nome;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public Casa getPosicaoAtual() {
        return posicaoAtual;
    }

    public void setPosicaoAtual(Casa posicaoAtual) {
        this.posicaoAtual = posicaoAtual;
    }

    public List<Imovel> getPropriedades() {
        return propriedades;
    }

    public boolean isFalido() {
        return falido;
    }

    public void setFalido(boolean falido) {
        this.falido = falido;
    }

    public void avancar(int casas) {
        for (int i = 0; i < casas; i++) {
            posicaoAtual = posicaoAtual.getProxima();
        }
    }

    public void exibirStatusCompleto() {
        System.out.println("\n--- Status Completo do Jogador ---");
        System.out.println("Nome: " + nome);
        System.out.printf("Saldo: R$ %.2f\n", saldo);
        System.out.println("Posição atual: " + posicaoAtual.getNome());
        System.out.println("Propriedades:");
        if (propriedades.isEmpty()) {
            System.out.println("  Nenhuma propriedade.");
        } else {
            for (Imovel imovel : propriedades) {
                String status = imovel.getNome();
                if (imovel.isHipotecado()) status += " (Hipotecada)";
                System.out.println("  - " + status);
            }
        }
        System.out.println("-------------------------------\n");
    }

    public double calcularPatrimonio() {
        double total = saldo;
        for (Imovel im : propriedades) {
            total += im.getPrecoCompra();
        }
        return total;
    }

    public void removerSaldo(double valor) {
        if (valor < 0) {
            throw new IllegalArgumentException("Valor para remover não pode ser negativo.");
        }
        this.saldo -= valor;
        if (this.saldo < 0) {
            this.saldo = 0; // ou pode definir falido, conforme regra do jogo
        }
    }
    public void adicionarSaldo(double valor) {
        if (valor < 0) {
            throw new IllegalArgumentException("Valor para adicionar não pode ser negativo.");
        }
        this.saldo += valor;
    }

    public void adicionarImovel(Imovel imovel) {
        propriedades.add(imovel);
        imovel.setDono(this);
    }

    public void removerImovel(Imovel imovel) {
        propriedades.remove(imovel);
        imovel.setDono(null);
    }

    public List<Imovel> getPropriedadesDisponiveisParaHipoteca() {
        List<Imovel> disponiveis = new ArrayList<>();
        for (Imovel imovel : propriedades) {
            if (!imovel.isHipotecado()) {
                disponiveis.add(imovel);
            }
        }
        return disponiveis;
    }

    // Retorna propriedades que estão hipotecadas
    public List<Imovel> getPropriedadesHipotecadas() {
        List<Imovel> hipotecadas = new ArrayList<>();
        for (Imovel imovel : propriedades) {
            if (imovel.isHipotecado()) {
                hipotecadas.add(imovel);
            }
        }
        return hipotecadas;
    }

    // Hipoteca a propriedade e adiciona o valor da hipoteca ao saldo do jogador
    public void hipotecar(Imovel imovel) {
        if (propriedades.contains(imovel) && !imovel.isHipotecado()) {
            imovel.setHipotecado(true);
            this.saldo += imovel.getValorHipoteca();
        }
    }

    // Paga a hipoteca (valor para quitar), se saldo for suficiente, e "libera" o imóvel
    public boolean pagarHipoteca(Imovel imovel) {
        double valor = imovel.getValorParaQuitar();
        if (propriedades.contains(imovel) && imovel.isHipotecado() && saldo >= valor) {
            saldo -= valor;
            imovel.setHipotecado(false);
            return true;
        }
        return false;
    }

}


