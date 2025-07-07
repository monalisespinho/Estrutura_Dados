package Model;

public class Imposto extends Casa {
    public Imposto(String nome) {
        super(nome);
    }

    @Override
    public void acao(Jogador jogador) {
        double patrimonio = jogador.calcularPatrimonio();
        double valor = patrimonio * 0.05;
        System.out.println(jogador.getNome() + " pagará imposto de R$ " + valor);
        jogador.removerSaldo(valor);
    }
}
