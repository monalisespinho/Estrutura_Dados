package Model;

public class Inicio extends Casa {
    private double salario;

    public Inicio(String nome, double salario) {
        super(nome);
        this.salario = salario;
    }

    public double getSalario() {
        return salario;
    }

    @Override
    public void acao(Jogador jogador) {
        // O jogador recebe o salário ao passar pelo início (gerenciado na movimentação)
        System.out.println(jogador.getNome() + " passou pelo início e receberá salário de R$ " + salario);
        jogador.adicionarSaldo(salario);
    }
}
