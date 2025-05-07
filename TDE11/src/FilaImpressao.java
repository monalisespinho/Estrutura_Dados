import java.util.LinkedList;
import java.util.Queue;

public class FilaImpressao {
    private Queue<Documento> fila;
    private int totalPaginas;
    private Documento maiorDocumento;

    private int contadorImpressao;


    public FilaImpressao() {
        fila = new LinkedList<>();
        totalPaginas = 0;
    }

    public void inserirDocumento(String nome, int numeroPaginas){
        Documento documento = new Documento(nome, numeroPaginas);
        fila.add(documento);
        System.out.println("Documento inserido com sucesso!");
    }

    public void imprimir(){
        while (!fila.isEmpty()){
            Documento atual = fila.poll();
            System.out.println("Imprimindo: " + atual);
            totalPaginas += atual.getNumeroPaginas();
            contadorImpressao ++;

            if(maiorDocumento == null || atual.getNumeroPaginas()> maiorDocumento.getNumeroPaginas()){
                maiorDocumento = atual;
            }

            if (contadorImpressao % 2 == 0 && !fila.isEmpty()){
                System.out.println("Próximo na fila: " + fila.peek());
            }
        }

        System.out.println("\n--- Relatório Final ---");
        System.out.println("Documento com maior número de páginas: " + maiorDocumento);
        System.out.println("Total de páginas impressas: " + totalPaginas);
    }


}
