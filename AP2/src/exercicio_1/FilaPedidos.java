package exercicio_1;

public class FilaPedidos {

    private No inicio, fim;

    public void adicionarPedido(Pedido pedido) {
        No novo = new No(pedido);
        if (fim != null) fim.proximo = novo;
        fim = novo;
        if (inicio == null) inicio = novo;
    }

    public Pedido removerPedido() {
        if (inicio == null) return null;
        Pedido pedido = inicio.pedido;
        inicio = inicio.proximo;
        if (inicio == null) fim = null;
        return pedido;
    }

    public void imprimirPedidos(){
        No atual = inicio;
        while (atual != null){
            System.out.println(atual.pedido);
            atual = atual.proximo;
        }
    }

    public boolean isEmpty() {
        return inicio == null;
    }

}
