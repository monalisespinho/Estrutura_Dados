public class Deque<T> {
    private T[] deque;
    private int inicio;
    private int fim;
    private int tamanho;
    private final int capacidade;

    @SuppressWarnings("unchecked")
    public Deque(int capacidade) {
        this.capacidade = capacidade;
        this.deque = (T[]) new Object[capacidade];
        this.inicio = -1;
        this.fim = 0;
        this.tamanho = 0;
    }

    public boolean estaVazio() {
        return tamanho == 0;
    }

    public boolean estaCheio() {
        return tamanho == capacidade;
    }

    public int tamanho() {
        return tamanho;
    }

    public void limpar() {
        inicio = -1;
        fim = 0;
        tamanho = 0;
        deque = (T[]) new Object[capacidade];
    }

    public void inserirNoInicio(T elemento) {
        if (estaCheio()) {
            throw new IllegalStateException("Deque está cheio");
        }

        if (estaVazio()) {
            inicio = 0;
            fim = 0;
        } else {
            inicio = (inicio - 1 + capacidade) % capacidade;
        }

        deque[inicio] = elemento;
        tamanho++;
    }

    public void inserirNoFim(T elemento) {
        if (estaCheio()) {
            throw new IllegalStateException("Deque está cheio");
        }

        if (estaVazio()) {
            inicio = 0;
            fim = 0;
        } else {
            fim = (fim + 1) % capacidade;
        }

        deque[fim] = elemento;
        tamanho++;
    }

    public T removerDoInicio() {
        if (estaVazio()) {
            throw new IllegalStateException("Deque está vazio");
        }

        T elemento = deque[inicio];
        deque[inicio] = null;
        if (inicio == fim) {
            inicio = -1;
            fim = 0;
        } else {
            inicio = (inicio + 1) % capacidade;
        }
        tamanho--;
        return elemento;
    }

    public T removerDoFim() {
        if (estaVazio()) {
            throw new IllegalStateException("Deque está vazio");
        }

        T elemento = deque[fim];
        deque[fim] = null;
        if (inicio == fim) {
            inicio = -1;
            fim = 0;
        } else {
            fim = (fim - 1 + capacidade) % capacidade;
        }
        tamanho--;
        return elemento;
    }

    public T consultarInicio() {
        if (estaVazio()) {
            throw new IllegalStateException("Deque está vazio");
        }
        return deque[inicio];
    }

    public T consultarFim() {
        if (estaVazio()) {
            throw new IllegalStateException("Deque está vazio");
        }
        return deque[fim];
    }
}
