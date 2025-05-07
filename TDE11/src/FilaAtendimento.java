import java.util.LinkedList;
import java.util.Queue;

public class FilaAtendimento {

    private Queue<Cliente> fila;
    private int totalTempo;
    private int totalClientes;

    public FilaAtendimento() {
        fila = new LinkedList<>();
        totalTempo = 0;
        totalClientes = 0;
    }

    public void inserirCliente(String nome, int tempoAtendimento){
        Cliente cliente = new Cliente(nome, tempoAtendimento);
        fila.add(cliente);
        System.out.println("Cliente inserido com sucesso!");
    }

    public void atenderCliente(){
        if (!fila.isEmpty()){
            Cliente cliente = fila.poll();
            System.out.println("Atendimento: " + cliente.getNome() + " Tempo estimado de atendimento: " + cliente.getTempoAtendimento());
            totalTempo += cliente.getTempoAtendimento();
            totalClientes ++;
        }else {
            System.out.println("Fila vazia! Nenhum cliente para atender.");
        }
    }

    public void atenderTodos(){
        if(!fila.isEmpty()){
            atenderCliente();
        }
        System.out.println("Todos clientes atendidos");
        System.out.println("Tempo total gasto de atendimento: " + totalTempo + " minutos");
        System.out.println("Número total de atendimentos: " + totalClientes);
    }
}
