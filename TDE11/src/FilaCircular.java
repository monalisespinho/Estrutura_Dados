public class FilaCircular {

    private int [] fila;
    private  int inicio;
    private  int fim;
    private int tamanho;
    private int capacidade;

    public FilaCircular(int capacidade) {
        this.capacidade = capacidade;
        this.fila = new int[capacidade];
        this.inicio = 0;
        this.fim = 0;
        this.tamanho = 0;
    }

    public boolean estaVazia(){
        return tamanho == 0;
    }

    public boolean estaCheia(){
        return tamanho == capacidade;
    }

    public boolean enfileirar(int elemento){
        if(estaCheia()){
            System.out.println("Fila cheia. Não é possível enfileirar" + elemento);
            return false;
        }
        fila[fim] = elemento;
        fim = (fim + 1) % capacidade;
        tamanho ++;
        return true;
    }

    public Integer desenfileirar() {
        if (estaVazia()) {
            System.out.println("Fila vazia. Não há o que desenfileirar.");
            return null;
        }
        int elementoRemovido = fila[inicio];
        inicio = (inicio + 1) % capacidade;
        tamanho--;
        return elementoRemovido;
    }

    public Integer espiar() {
        if (estaVazia()) {
            System.out.println("Fila vazia. Nada para espiar.");
            return null;
        }
        return fila[inicio];
    }

    public void mostrarFila() {
        System.out.print("Fila: ");
        for (int i = 0; i < tamanho; i++) {
            int index = (inicio + i) % capacidade;
            System.out.print(fila[index] + " ");
        }
        System.out.println();

    }
}
