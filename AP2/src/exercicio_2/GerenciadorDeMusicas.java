package exercicio_2;

import java.util.Scanner;

public class GerenciadorDeMusicas {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Playlist playlist = new Playlist();
        int opcao;

        do {
            System.out.println("\n1. Proxima musica\n2. Musica anterior\n3. Tocar musica\n4. Adicionar musica\n5. Remover musica\n6. Listar musicas\n7. Sair\nDigite a opcao:");
            opcao = sc.nextInt();
            sc.nextLine();

            switch (opcao) {
                case 1 -> playlist.proximaMusica();
                case 2 -> playlist.musicaAnterior();
                case 3 -> playlist.tocarAtual();
                case 4 -> {
                    System.out.println("Titulo:");
                    String titulo = sc.nextLine();
                    System.out.println("Artista:");
                    String artista = sc.nextLine();
                    System.out.println("Album:");
                    String album = sc.nextLine();
                    System.out.println("Duracao (s):");
                    int duracao = sc.nextInt();
                    sc.nextLine();
                    playlist.adicionarMusicaFim(titulo, artista, album, duracao);
                }
                case 5 -> {
                    System.out.println("Titulo da musica a remover:");
                    String titulo = sc.nextLine();
                    playlist.removerMusicaPorTitulo(titulo);
                }
                case 6 -> playlist.listarMusica();
                case 7 -> System.out.println("Saindo...");
                default -> System.out.println("Opcao invalida.");
            }
        } while (opcao != 7);
        sc.close();
    }
}
