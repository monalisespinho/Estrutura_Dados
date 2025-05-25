package exercicio_2;

public class NoMusica {
     String tituloMusica;
    private String artista;
    private String album;
    private int duracao;

    NoMusica anterior;
    NoMusica proximo;

    public NoMusica(String tituloMusica, String artista, String album, int duracao) {
        this.tituloMusica = tituloMusica;
        this.artista = artista;
        this.album = album;
        this.duracao = duracao;
        this.proximo = null;
        this.anterior = null;

    }

    public void exibir() {
        System.out.println("Titulo: " + tituloMusica);
        System.out.println("Artista: " + artista);
        System.out.println("Album: " + album);
        System.out.println("Duracao: " + duracao + "s\n");
    }
}
