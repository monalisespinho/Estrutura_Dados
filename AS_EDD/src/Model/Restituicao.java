package Model;

public class Restituicao extends Casa {
    private double salario;

    public Restituicao(String nome, double salario) {
        super(nome);
        this.salario = salario;
    }

    @Override
    public void acao(Jogador jogador) {
        double valor = salario * 0.10;
        System.out.println(jogador.getNome() + " recebeu restituição de R$ " + valor);
        jogador.adicionarSaldo(valor);
    }
}

