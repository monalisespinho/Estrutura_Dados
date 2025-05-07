public class Main {
    public static void main(String[] args) {
        FilaAtendimento fila = new FilaAtendimento();

        fila.inserirCliente("Ana", 5);
        fila.inserirCliente("Pedro", 3);
        fila.inserirCliente("João", 8);

        fila.atenderTodos();
    }
}