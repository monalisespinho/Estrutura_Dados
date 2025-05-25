package exercicio_1;

import java.util.Scanner;

public class SistemaPedidos {
    public static void main(String[] args) {
        FilaPedidos fila = new FilaPedidos();
        PilhaPedidos pilha = new PilhaPedidos();
        Scanner sc = new Scanner(System.in);
        int opcao, id = 1;

        do {
            System.out.println("\n1. Novo Pedido");
            System.out.println("2. Atender Pedido");
            System.out.println("3. Cancelar Pedido");
            System.out.println("4. Restaurar Pedido");
            System.out.println("5. Imprimir Pedidos Pendentes");
            System.out.println("6. Imprimir Pedidos Cancelados");
            System.out.println("0. Sair");
            System.out.print("Escolha uma opção: ");
            opcao = sc.nextInt();
            sc.nextLine();


            switch (opcao) {
                case 1:
                    System.out.print("Descrição do pedido: ");
                    String desc = sc.nextLine();
                    fila.adicionarPedido(new Pedido(id++, desc));
                    break;
                case 2:
                    Pedido atendido = fila.removerPedido();
                    System.out.println(atendido != null ? "Pedido atendido: " + atendido : "Nenhum pedido pendente.");
                    break;
                case 3:
                    Pedido cancelado = fila.removerPedido();
                    if (cancelado != null) {
                        pilha.adicionarPedidoCancelado(cancelado);
                        System.out.println("Pedido cancelado: " + cancelado);
                    } else {
                        System.out.println("Nenhum pedido para cancelar.");
                    }
                    break;
                case 4:
                    Pedido restaurado = pilha.removerPedidoCancelado();
                    if (restaurado != null) {
                        fila.adicionarPedido(restaurado);
                        System.out.println("Pedido restaurado: " + restaurado);
                    } else {
                        System.out.println("Nenhum pedido cancelado para restaurar.");
                    }
                    break;
                case 5:
                    System.out.println("Pedidos Pendentes:");
                    fila.imprimirPedidos();
                    break;
                case 6:
                    System.out.println("Pedidos Cancelados:");
                    pilha.imprimirPedidosCancelados();
                    break;
            }

        } while (opcao != 0);

        sc.close();
    }

}
