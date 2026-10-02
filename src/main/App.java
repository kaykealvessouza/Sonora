package main;

import java.util.ArrayList;
import java.util.Scanner;

public class App {

    public static void main(String[] args) {

        Plataforma sonora = new Plataforma();
        Scanner scan = new Scanner(System.in);

        // Musica
        Musica musica;
        Musica encontrada;

        String album;
        String titulo;
        String artista;
        int duracaoSegundos;

        int idMusica;

        // Podcast
        PodCast podcast;
        ArrayList<PodCast> podcasts = new ArrayList<>();

        String apresentador;
        int numeroEpisodio;
        int idPodcast;

        // Usuario
        Usuario user;
        Usuario dono;

        String nome;
        String email;

        int idUser;

        // Playlist
        Playlist playlist;

        String nomePlaylist;

        int opcao = 100;
        String entrada;

        /*
         * Conteudo e Plano são classes abstratas e não podem ser instanciadas.
         *
         * Conteudo c = new Conteudo("Generico", 120);
         * Erro de compilação: Conteudo é uma classe abstrata e não pode ser
         * instanciada diretamente.
         *
         * Plano p = new Plano("Generico", 1);
         * Erro de compilação: Plano é uma classe abstrata e não pode ser
         * instanciada diretamente.
         */

        /*
         * PlanoGratuito é uma classe final e não pode ser estendida.
         *
         * class PlanoEspecial extends PlanoGratuito {
         * }
         *
         * Erro de compilação: não é possível herdar de uma classe final.
         */

        do {
            System.out.println("\n\n╔══════════════════════════════════════╗");
            System.out.println("║               SONORA                 ║");
            System.out.println("╠══════════════════════════════════════╣");
            System.out.println("║ 1 - Cadastrar música manualmente     ║");
            System.out.println("║ 2 - Cadastrar podcast                ║");
            System.out.println("║ 3 - Cadastrar usuário                ║");
            System.out.println("║ 4 - Gerenciar playlists              ║");
            System.out.println("║ 5 - Buscar música por ID             ║");
            System.out.println("║ 6 - Buscar música por título         ║");
            System.out.println("║ 7 - Reproduzir uma música            ║");
            System.out.println("║ 8 - Buscar podcast por ID            ║");
            System.out.println("║ 9 - Reproduzir um podcast            ║");
            System.out.println("║ 10 - Listar acervo                   ║");
            System.out.println("║ 11 - Seguir usuário                  ║");
            System.out.println("║ 12 - Deixar de seguir usuário        ║");
            System.out.println("║ 13 - Listar usuários seguidos        ║");
            System.out.println("║ 14 - Gerenciar plano                 ║");
            System.out.println("║ 0 - Sair                             ║");
            System.out.println("╚══════════════════════════════════════╝");

            System.out.print("Opção: ");

            try {
                opcao = Integer.parseInt(scan.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("\nValor inválido. Digite um número.");
                opcao = 100;
            }

            switch (opcao) {

                case 1:
                    // cadastrar música

                    try {
                        System.out.print("\nQual o Titulo da música? -> ");
                        titulo = scan.nextLine();

                        System.out.print("\nQual o nome do artista? -> ");
                        artista = scan.nextLine();

                        System.out.print("\nQual o nome do Álbum? -> ");
                        album = scan.nextLine();

                        System.out.print("\nQuantos segundos a música possui? -> ");
                        duracaoSegundos = Integer.parseInt(scan.nextLine());

                        musica = new Musica(titulo, artista, duracaoSegundos, album);

                        if (sonora.cadastrarMusica(musica)) {
                            System.out.printf("\n%dª Música da Sonora cadastrada com suceso",
                                    sonora.getTotalMusicas());
                        } else {
                            System.out.println("Falha ao cadastrar música. Verifique os dados da música!");
                        }

                    } catch (NumberFormatException e) {
                        System.out.println("\nA duração precisa ser um número inteiro.");

                    } catch (IllegalArgumentException e) {
                        System.out.println("\nErro: " + e.getMessage());
                    }

                    break;

                case 2:
                    // cadastrar podcast

                    try {
                        System.out.print("\nQual o título do podcast? -> ");
                        titulo = scan.nextLine();

                        System.out.print("\nQual o nome do apresentador? -> ");
                        apresentador = scan.nextLine();

                        System.out.print("\nQual o número do episódio? -> ");
                        numeroEpisodio = Integer.parseInt(scan.nextLine());

                        System.out.print("\nQuantos segundos o episódio possui? -> ");
                        duracaoSegundos = Integer.parseInt(scan.nextLine());

                        podcast = new PodCast(
                                titulo,
                                duracaoSegundos,
                                apresentador,
                                numeroEpisodio);

                        podcasts.add(podcast);

                        System.out.println("\nPodcast cadastrado com sucesso!");
                        System.out.println(podcast);

                    } catch (NumberFormatException e) {
                        System.out.println("\nO número do episódio e a duração precisam ser números inteiros.");

                    } catch (IllegalArgumentException e) {
                        System.out.println("\nErro: " + e.getMessage());
                    }

                    break;

                case 3:
                    // cadastrar usuário

                    try {
                        System.out.print("\nQual o nome do user? -> ");
                        nome = scan.nextLine();

                        System.out.print("\nQual o e-mail? -> ");
                        email = scan.nextLine();

                        user = new Usuario(nome, email);

                        if (sonora.cadastrarUsuario(user)) {
                            System.out.printf("\n%dº Usuário da Sonora cadastrado com sucesso",
                                    sonora.getTotalUsuarios());
                        } else {
                            System.out.println("Falha ao cadastrar usuário. Verifique os dados!");
                        }
                    } catch (IllegalArgumentException e) {
                        System.out.println("\nErro: " + e.getMessage());
                    }

                    break;

                case 4:

                    // Gerenciar playlists

                    int opcaoPlaylist = 100;

                    do {
                        System.out.println("\n╔══════════════════════════════════════╗");
                        System.out.println("║          GERENCIAR PLAYLISTS         ║");
                        System.out.println("╠══════════════════════════════════════╣");
                        System.out.println("║ 1 - Criar playlist                   ║");
                        System.out.println("║ 2 - Adicionar música à playlist      ║");
                        System.out.println("║ 3 - Remover música da playlist       ║");
                        System.out.println("║ 4 - Listar músicas da playlist       ║");
                        System.out.println("║ 5 - Reproduzir música da playlist    ║");
                        System.out.println("║ 6 - Reproduzir playlist inteira      ║");
                        System.out.println("║ 0 - Voltar                           ║");
                        System.out.println("╚══════════════════════════════════════╝");

                        System.out.print("Opção: ");

                        try {
                            opcaoPlaylist = Integer.parseInt(scan.nextLine());
                        } catch (NumberFormatException e) {
                            System.out.println("\nValor inválido. Digite um número.");
                            opcaoPlaylist = 100;
                        }

                        switch (opcaoPlaylist) {

                            case 1:
                                // Criar playlist
                                try {
                                    System.out.print("\nQual o nome da sua Playlist? -> ");
                                    nomePlaylist = scan.nextLine();

                                    System.out.print("\nQual o dono da sua Playlist? (Digite o id) -> ");
                                    idUser = Integer.parseInt(scan.nextLine());

                                    dono = sonora.buscarUsuarioPorId(idUser);

                                    if (dono == null) {
                                        System.out.println("\nUsuário não encontrado!");
                                        break;
                                    }

                                    playlist = new Playlist(nomePlaylist, dono);

                                    if (sonora.cadastrarPlaylist(playlist)) {
                                        System.out.println("\n========== " + playlist.getNome() + " ==========");
                                        System.out.println("\nPlaylist criada com sucesso!");
                                    } else {
                                        System.out.println("\nFalha ao criar playlist!");
                                    }
                                } catch (NumberFormatException e) {
                                    System.out.println("\nO ID do usuário precisa ser um número.");
                                } catch (IllegalArgumentException e) {
                                    System.out.println("\nErro: " + e.getMessage());
                                }

                                break;

                            case 2:
                                // Adicionar música à playlist
                                try {
                                    System.out.print(
                                            "\nQual o ID ou nome da música que deseja adicionar a música? -> ");
                                    entrada = scan.nextLine();

                                    encontrada = sonora.buscarMusicaGeral(entrada);

                                    if (encontrada == null) {
                                        System.out.println("\nMúsica não encontrada!");
                                    } else {
                                        System.out.printf(
                                                "\nDeseja adicionar a música %s a qual playlist? Digite o nome -> ",
                                                encontrada.getTitulo());
                                        entrada = scan.nextLine();

                                        playlist = sonora.buscarPlaylist(entrada);

                                        if (playlist == null) {
                                            System.out.println("Playlist não existe");
                                        } else {
                                            if (playlist.adicionar(encontrada)) {
                                                System.out.println(
                                                        "\n========== " + playlist.getNome() + " ==========");
                                                System.out.println("Música adicionada com sucesso!");
                                            } else {
                                                System.out.println("Não foi possível adicionar!");
                                            }
                                        }
                                    }
                                } catch (IllegalArgumentException e) {
                                    System.out.println("\nErro: " + e.getMessage());
                                }

                                break;

                            case 3:
                                // Remover música da playlist
                                try {
                                    System.out.print("\nQual o nome da playlist que deseja remover a música? -> ");
                                    entrada = scan.nextLine();

                                    playlist = sonora.buscarPlaylist(entrada);

                                    if (playlist == null) {
                                        System.out.println("Playlist não existe");
                                    } else {
                                        System.out.print("\nQual o ID ou nome da música que deseja excluir? -> ");
                                        entrada = scan.nextLine();

                                        encontrada = sonora.buscarMusicaGeral(entrada);

                                        if (encontrada == null) {
                                            System.out.println("Música não encontrada!");
                                            break;
                                        }

                                        int posicaoEncontrada = playlist.getPosicaoDaMusica(encontrada);

                                        if (posicaoEncontrada == -1) {
                                            System.out.println("\nMúsica não encontrada!");
                                        } else {
                                            if (playlist.removerNaPosicao(posicaoEncontrada)) {
                                                System.out.println(
                                                        "\n========== " + playlist.getNome() + " ==========");
                                                System.out.println("Música removida com sucesso!");
                                            } else {
                                                System.out.println("Não foi possível remover a música!");
                                            }
                                        }
                                    }
                                } catch (IndexOutOfBoundsException e) {
                                    System.out.println("\nÍndice fora de posição");
                                } catch (IllegalArgumentException e) {
                                    System.out.println("\nErro: " + e.getMessage());
                                }

                                break;

                            case 4:
                                // Listar músicas da playlist
                                try {
                                    System.out.print("\nQual o nome da playlist? -> ");
                                    entrada = scan.nextLine();

                                    playlist = sonora.buscarPlaylist(entrada);

                                    if (playlist == null) {
                                        System.out.println("Playlist não existe");
                                    } else {
                                        System.out.println("\n========== " + playlist.getNome() + " ==========");

                                        for (int i = 1; i <= playlist.getQuantidade(); i++) {
                                            encontrada = playlist.getNaPosicao(i);
                                            exibirMusica(encontrada);
                                        }
                                    }
                                } catch (IndexOutOfBoundsException e) {
                                    System.out.println("\nÍndice fora de posição");
                                } catch (IllegalArgumentException e) {
                                    System.out.println("\nErro: " + e.getMessage());
                                }

                                break;

                            case 5:
                                // Reproduzir música da playlist

                                try {
                                    System.out.print(
                                            "\nQual o nome da playlist que deseja reproduzir a música? -> ");
                                    entrada = scan.nextLine();

                                    playlist = sonora.buscarPlaylist(entrada);

                                    if (playlist == null) {
                                        System.out.println("Playlist não existe");
                                    } else {
                                        System.out.print(
                                                "\nQual o ID ou nome da música que deseja reproduzir? -> ");
                                        entrada = scan.nextLine();

                                        encontrada = sonora.buscarMusicaGeral(entrada);

                                        if (encontrada == null) {
                                            System.out.println("\nMúsica não encontrada!");
                                        } else if (playlist.getPosicaoDaMusica(encontrada) == -1) {
                                            System.out.println("\nMúsica não encontrada na playlist!");
                                        } else {
                                            System.out.println(
                                                    "\n========== " + playlist.getNome() + " ==========");
                                            encontrada.reproduzir();
                                        }
                                    }
                                } catch (IllegalArgumentException e) {
                                    System.out.println("\nErro: " + e.getMessage());
                                }

                                break;

                            case 6:
                                // Reproduzir playlist inteira

                                try {
                                    System.out.print(
                                            "\nQual o nome da playlist que deseja reproduzir inteira? -> ");
                                    entrada = scan.nextLine();

                                    playlist = sonora.buscarPlaylist(entrada);

                                    if (playlist == null) {
                                        System.out.println("Playlist não existe");
                                    } else {
                                        System.out.println(
                                                "\n========== " + playlist.getNome() + " ==========");
                                        playlist.reproduzirTudo();
                                    }
                                } catch (IllegalArgumentException e) {
                                    System.out.println("\nErro: " + e.getMessage());
                                }

                                break;

                            case 0:
                                System.out.println("\nVoltando...");
                                break;

                            default:
                                System.out.println("\nOpção inválida.");
                        }

                    } while (opcaoPlaylist != 0);

                    break;

                case 5:
                    // buscar música por ID
                    try {
                        System.out.print("\nQual o id da música que deseja procurar? -> ");
                        idMusica = Integer.parseInt(scan.nextLine());

                        encontrada = sonora.buscarMusica(idMusica);

                        if (encontrada == null) {
                            System.out.println("\nMúsica não encontrada!");
                            break;
                        }

                        exibirMusica(encontrada);
                    } catch (NumberFormatException e) {
                        System.out.println("\nValor inválido. Digite um número.");
                    } catch (IllegalArgumentException e) {
                        System.out.println("\nErro: " + e.getMessage());
                    }

                    break;

                case 6:
                    // buscar música por título

                    try {
                        System.out.print("\nQual o nome da música que deseja procurar? -> ");
                        titulo = scan.nextLine();

                        encontrada = sonora.buscarMusica(titulo);

                        if (encontrada == null) {
                            System.out.println("\nMúsica não encontrada!");
                            break;
                        }

                        exibirMusica(encontrada);
                    } catch (IllegalArgumentException e) {
                        System.out.println("\nErro: " + e.getMessage());
                    }

                    break;

                case 7:
                    // reproduzir música

                    try {
                        System.out.print("\nQual o ID ou nome da música que deseja reproduzir? -> ");
                        entrada = scan.nextLine();

                        encontrada = sonora.buscarMusicaGeral(entrada);

                        if (encontrada == null) {
                            System.out.println("\nMúsica não encontrada!");
                        } else {
                            encontrada.reproduzir();

                            System.out.println(
                                    "Total de reproduções: " + encontrada.getReproducoes());
                        }
                    } catch (IllegalArgumentException e) {
                        System.out.println("\nErro: " + e.getMessage());
                    }

                    break;

                case 8:
                    // buscar podcast por ID

                    try {
                        System.out.print("\nQual o ID do podcast que deseja procurar? -> ");
                        idPodcast = Integer.parseInt(scan.nextLine());

                        PodCast encontradoPodcast = null;

                        for (PodCast p : podcasts) {
                            if (p.getId() == idPodcast) {
                                encontradoPodcast = p;
                                break;
                            }
                        }

                        if (encontradoPodcast == null) {
                            System.out.println("\nPodcast não encontrado!");
                        } else {
                            exibirPodcast(encontradoPodcast);
                        }

                    } catch (NumberFormatException e) {
                        System.out.println("\nO ID do podcast precisa ser um número.");
                    } catch (IllegalArgumentException e) {
                        System.out.println("\nErro: " + e.getMessage());
                    }

                    break;

                case 9:
                    // reproduzir podcast

                    try {
                        System.out.print("\nQual o ID do podcast que deseja reproduzir? -> ");
                        idPodcast = Integer.parseInt(scan.nextLine());

                        PodCast encontradoPodcast = null;

                        for (PodCast p : podcasts) {
                            if (p.getId() == idPodcast) {
                                encontradoPodcast = p;
                                break;
                            }
                        }

                        if (encontradoPodcast == null) {
                            System.out.println("\nPodcast não encontrado!");
                        } else {
                            encontradoPodcast.reproduzir();

                            System.out.println(
                                    "Total de reproduções: " + encontradoPodcast.getReproducoes());
                        }

                    } catch (NumberFormatException e) {
                        System.out.println("\nO ID do podcast precisa ser um número.");
                    } catch (IllegalArgumentException e) {
                        System.out.println("\nErro: " + e.getMessage());
                    }

                    break;

                case 10:
                    // listar acervo

                    try {
                        System.out.println("\n========== ACERVO SONORA ==========");

                        if (sonora.getTotalMusicas() == 0 && podcasts.isEmpty()) {
                            System.out.println("O acervo está vazio.");
                            break;
                        }

                        if (sonora.getTotalMusicas() > 0) {
                            System.out.println("\n---------- MÚSICAS ----------");

                            for (int i = 1; i <= sonora.getTotalMusicas(); i++) {
                                encontrada = sonora.getMusicaNaPosicao(i);

                                exibirMusica(encontrada);
                            }
                        }

                        if (!podcasts.isEmpty()) {
                            System.out.println("\n---------- PODCASTS ----------");

                            for (PodCast p : podcasts) {
                                exibirPodcast(p);
                            }
                        }

                    } catch (IllegalArgumentException e) {
                        System.out.println("\nErro: " + e.getMessage());
                    }

                    break;

                case 11:
                    // seguir usuário
                    try {
                        System.out.print("\nQual o ID do usuário que irá seguir? -> ");
                        int idSeguidor = Integer.parseInt(scan.nextLine());

                        System.out.print("Qual o ID do usuário que deseja seguir? -> ");
                        int idSeguido = Integer.parseInt(scan.nextLine());

                        Usuario seguidor = sonora.buscarUsuarioPorId(idSeguidor);
                        Usuario seguido = sonora.buscarUsuarioPorId(idSeguido);

                        if (seguidor == null || seguido == null) {
                            System.out.println("\nUsuário não encontrado!");
                            break;
                        }

                        seguidor.seguir(seguido);

                        System.out.println(
                                "\n" + seguidor.getNome() + " agora segue "
                                        + seguido.getNome() + ".");

                    } catch (NumberFormatException e) {
                        System.out.println("\nO ID do usuário precisa ser um número.");
                    } catch (IllegalArgumentException e) {
                        System.out.println("\nErro: " + e.getMessage());
                    }

                    break;

                case 12:
                    // deixar de seguir usuário
                    try {
                        System.out.print("\nQual o ID do usuário que deixará de seguir? -> ");
                        int idSeguidor = Integer.parseInt(scan.nextLine());

                        System.out.print("Qual o ID do usuário que deseja deixar de seguir? -> ");
                        int idSeguido = Integer.parseInt(scan.nextLine());

                        Usuario seguidor = sonora.buscarUsuarioPorId(idSeguidor);
                        Usuario seguido = sonora.buscarUsuarioPorId(idSeguido);

                        if (seguidor == null || seguido == null) {
                            System.out.println("\nUsuário não encontrado!");
                            break;
                        }

                        seguidor.deixarDeSeguir(seguido);

                        System.out.println(
                                "\n" + seguidor.getNome() + " deixou de seguir "
                                        + seguido.getNome() + ".");

                    } catch (NumberFormatException e) {
                        System.out.println("\nO ID do usuário precisa ser um número.");
                    } catch (IllegalArgumentException e) {
                        System.out.println("\nErro: " + e.getMessage());
                    }

                    break;

                case 13:
                    // listar usuários seguidos
                    try {
                        System.out.print("\nQual o ID do usuário? -> ");
                        int idSeguidor = Integer.parseInt(scan.nextLine());

                        Usuario seguidor = sonora.buscarUsuarioPorId(idSeguidor);

                        if (seguidor == null) {
                            System.out.println("\nUsuário não encontrado!");
                            break;
                        }

                        System.out.println(
                                "\n========== USUÁRIOS SEGUIDOS POR "
                                        + seguidor.getNome() + " ==========");

                        if (seguidor.getQuantidadeSeguindo() == 0) {
                            System.out.println("Este usuário não segue ninguém.");
                            break;
                        }

                        for (Usuario seguido : seguidor.getSeguindo()) {
                            System.out.println(
                                    "ID: " + seguido.getId()
                                            + " | Nome: " + seguido.getNome()
                                            + " | E-mail: " + seguido.getEmail());
                        }

                    } catch (NumberFormatException e) {
                        System.out.println("\nO ID do usuário precisa ser um número.");
                    } catch (IllegalArgumentException e) {
                        System.out.println("\nErro: " + e.getMessage());
                    }

                    break;

                case 14:
                    // Gerenciar plano

                    int opcaoPlano = 100;

                    do {
                        System.out.println("\n╔══════════════════════════════════════╗");
                        System.out.println("║            GERENCIAR PLANO           ║");
                        System.out.println("╠══════════════════════════════════════╣");
                        System.out.println("║ 1 - Visualizar plano atual           ║");
                        System.out.println("║ 2 - Assinar plano gratuito           ║");
                        System.out.println("║ 3 - Assinar plano individual         ║");
                        System.out.println("║ 4 - Assinar plano família            ║");
                        System.out.println("║ 0 - Voltar                            ║");
                        System.out.println("╚══════════════════════════════════════╝");

                        System.out.print("Opção: ");

                        try {
                            opcaoPlano = Integer.parseInt(scan.nextLine());
                        } catch (NumberFormatException e) {
                            System.out.println("\nValor inválido. Digite um número.");
                            opcaoPlano = 100;
                        }

                        switch (opcaoPlano) {

                            case 1:
                                // visualizar plano atual

                                try {
                                    System.out.print("\nQual o ID do usuário? -> ");
                                    idUser = Integer.parseInt(scan.nextLine());

                                    user = sonora.buscarUsuarioPorId(idUser);

                                    if (user == null) {
                                        System.out.println("\nUsuário não encontrado!");
                                        break;
                                    }

                                    System.out.println("\n========== PLANO ATUAL ==========");
                                    System.out.println("Usuário: " + user.getNome());
                                    System.out.println("Plano: " + user.getPlano().resumo());

                                    if (user.getPlano() instanceof PlanoFamilia) {
                                        PlanoFamilia planoFamilia =
                                                (PlanoFamilia) user.getPlano();

                                        System.out.println(
                                                "Membros: "
                                                        + planoFamilia.getQuantidadeMembros());
                                    }

                                } catch (NumberFormatException e) {
                                    System.out.println("\nO ID do usuário precisa ser um número.");
                                } catch (IllegalArgumentException e) {
                                    System.out.println("\nErro: " + e.getMessage());
                                }

                                break;

                            case 2:
                                // assinar plano gratuito

                                try {
                                    System.out.print("\nQual o ID do usuário? -> ");
                                    idUser = Integer.parseInt(scan.nextLine());

                                    user = sonora.buscarUsuarioPorId(idUser);

                                    if (user == null) {
                                        System.out.println("\nUsuário não encontrado!");
                                        break;
                                    }

                                    user.assinar(new PlanoGratuito());

                                    System.out.println("\nPlano gratuito assinado com sucesso!");
                                    System.out.println(
                                            "Plano atual: " + user.getPlano().resumo());

                                } catch (NumberFormatException e) {
                                    System.out.println("\nO ID do usuário precisa ser um número.");
                                } catch (IllegalArgumentException e) {
                                    System.out.println("\nErro: " + e.getMessage());
                                }

                                break;

                            case 3:
                                // assinar plano individual

                                try {
                                    System.out.print("\nQual o ID do usuário? -> ");
                                    idUser = Integer.parseInt(scan.nextLine());

                                    user = sonora.buscarUsuarioPorId(idUser);

                                    if (user == null) {
                                        System.out.println("\nUsuário não encontrado!");
                                        break;
                                    }

                                    System.out.print(
                                            "Qual o preço mensal do plano individual? -> ");

                                    double precoMensal = Double.parseDouble(
                                            scan.nextLine().replace(",", "."));

                                    PlanoIndividual planoIndividual =
                                            new PlanoIndividual(precoMensal);

                                    user.assinar(planoIndividual);

                                    System.out.println(
                                            "\nPlano individual assinado com sucesso!");
                                    System.out.println(
                                            "Plano atual: " + user.getPlano().resumo());

                                } catch (NumberFormatException e) {
                                    System.out.println(
                                            "\nO preço precisa ser um número válido.");
                                } catch (IllegalArgumentException e) {
                                    System.out.println("\nErro: " + e.getMessage());
                                }

                                break;

                            case 4:
                                // assinar plano família

                                try {
                                    System.out.print("\nQual o ID do usuário? -> ");
                                    idUser = Integer.parseInt(scan.nextLine());

                                    user = sonora.buscarUsuarioPorId(idUser);

                                    if (user == null) {
                                        System.out.println("\nUsuário não encontrado!");
                                        break;
                                    }

                                    System.out.print(
                                            "Qual o preço mensal do plano família? -> ");

                                    double precoMensal = Double.parseDouble(
                                            scan.nextLine().replace(",", "."));

                                    System.out.print(
                                            "Quantos membros terá o plano família? -> ");

                                    int quantidadeMembros =
                                            Integer.parseInt(scan.nextLine());

                                    PlanoFamilia planoFamilia =
                                            new PlanoFamilia(
                                                    precoMensal,
                                                    quantidadeMembros);

                                    user.assinar(planoFamilia);

                                    System.out.println(
                                            "\nPlano família assinado com sucesso!");
                                    System.out.println(
                                            "Plano atual: " + user.getPlano().resumo());
                                    System.out.println(
                                            "Membros: "
                                                    + planoFamilia.getQuantidadeMembros());

                                } catch (NumberFormatException e) {
                                    System.out.println(
                                            "\nO preço e a quantidade de membros precisam ser números válidos.");
                                } catch (IllegalArgumentException e) {
                                    System.out.println("\nErro: " + e.getMessage());
                                }

                                break;

                            case 0:
                                System.out.println("\nVoltando...");
                                break;

                            default:
                                System.out.println("\nOpção inválida.");
                        }

                    } while (opcaoPlano != 0);

                    break;

                case 0:
                    System.out.println("Saindo...");
                    break;

                default:
                    System.out.println("Opção inválida.");
            }

        } while (opcao != 0);

        scan.close();
    }

    public static void exibirMusica(Musica musica) {
        System.out.println("\nID: " + musica.getId());
        System.out.println("Título: " + musica.getTitulo());
        System.out.println("Artista: " + musica.getArtista());
        System.out.println("Duração: " + musica.getDuracaoFormatada());
        System.out.println("Reproduções: " + musica.getReproducoes());
    }

    public static void exibirPodcast(PodCast podcast) {
        System.out.println("\nID: " + podcast.getId());
        System.out.println("Título: " + podcast.getTitulo());
        System.out.println("Apresentador: " + podcast.getApresentador());
        System.out.println("Episódio: " + podcast.getNumeroEpisodio());
        System.out.println("Duração: " + podcast.getDuracaoFormatada());
        System.out.println("Reproduções: " + podcast.getReproducoes());
    }
}