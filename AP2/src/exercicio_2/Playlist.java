package exercicio_2;

import java.security.spec.RSAOtherPrimeInfo;

public class Playlist {
    private NoMusica inicio, atual;

    public void adicionarMusicaFim(String titulo, String artista, String album, int duracacao) {
        NoMusica nova = new NoMusica(titulo, artista, album, duracacao);
        if (inicio == null) {
            inicio = nova;
            atual = nova;
        } else {
            NoMusica aux = inicio;
            while (aux.proximo != null) {
                aux = aux.proximo;
            }
            aux.proximo = nova;
            nova.anterior = aux;
        }
    }

    public void tocarAtual(){
        if(atual != null){
            System.out.println("Reproduzindo....");
            atual.exibir();
        }else {
            System.out.println("Nenhuma música na playlist.");
        }
    }

    public void proximaMusica(){
        if(atual != null && atual.proximo != null){
            atual = atual.proximo;
            tocarAtual();
        }else {
            System.out.println("Fim da playlist");
        }
    }

    public void musicaAnterior(){
        if(atual != null && atual.anterior != null){
            atual = atual.anterior;
            tocarAtual();
        }else {
            System.out.println("Início da playlist");
        }
    }

    public void listarMusica(){
        if(inicio == null){
            System.out.println("Playlist vazia");
            return;
        }
        NoMusica aux = inicio;
        while(aux != null){
            aux.exibir();
            aux = aux.proximo;
        }
    }

    public void removerMusicaPorTitulo(String titulo) {
        NoMusica aux = inicio;
        while (aux != null) {
            if (aux.tituloMusica.equalsIgnoreCase(titulo)) {
                if (aux.anterior != null) aux.anterior.proximo = aux.proximo;
                else inicio = aux.proximo;
                if (aux.proximo != null) aux.proximo.anterior = aux.anterior;
                if (atual == aux) atual = aux.proximo != null ? aux.proximo : aux.anterior;
                System.out.println("Música removida: " + titulo);
                return;
            }
            aux = aux.proximo;
        }
        System.out.println("Música nao encontrada.");
    }

}
