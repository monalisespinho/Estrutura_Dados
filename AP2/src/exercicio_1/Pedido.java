package exercicio_1;

public class Pedido {
    private int id;
    private String descricao;

    public Pedido(int id, String descricao) {
        this.id = id;
        this.descricao = descricao;
    }

    @Override
    public String toString() {
        return "ID: " + id + " - " + descricao;
    }
}
