import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Pessoa {
    private String nome;
    private LocalDate dataNascimento;
    Pessoa paiOuMae;
    private List<Pessoa> filhos;

    public Pessoa(String nome, LocalDate dataNascimento, Pessoa paiOuMae) {
        this.nome = nome;
        this.dataNascimento = dataNascimento;
        this.paiOuMae = paiOuMae;
        this.filhos = new ArrayList<>();
    }

    public String getNome() {
        return nome;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public Pessoa getPaiOuMae() {
        return paiOuMae;
    }

    public List<Pessoa> getFilhos() {
        return filhos;
    }

    public void adicionarFilho(Pessoa filho) {
        filhos.add(filho);
    }

    @Override
    public String toString() {
        return nome + (dataNascimento != null ? " (" + dataNascimento + ")" : "");
    }
}
