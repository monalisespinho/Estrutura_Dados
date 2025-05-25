package exercicio_1;

public class PilhaPedidos {
    private No topo;

    public void adicionarPedidoCancelado(Pedido pedido){
        No novo = new No(pedido);
        novo.proximo = topo;
        topo = novo;
    }

    public Pedido removerPedidoCancelado(){
        if (topo == null) return null;
        Pedido pedido = topo.pedido;
        topo = topo.proximo;
        return pedido;
    }

    public void imprimirPedidosCancelados() {
        No atual = topo;
        while (atual != null) {
            System.out.println(atual.pedido);
            atual = atual.proximo;
        }
    }

    public boolean isEmpty() {
        return topo == null;
    }


}
