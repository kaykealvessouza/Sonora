# Planos de Teste

### PL01 — Validar `Musica.getDuracaoFormatada()`

| Caso | Descrição                               | Entrada                | Saída esperada             |
| ---- | --------------------------------------- | ---------------------- | -------------------------- |
| 1    | Duração com minutos e segundos          | Música de 125 segundos | Deve resultar em `"02:05"` |
| 2    | Duração redonda em minutos              | Música de 90 segundos  | Deve resultar em `"01:30"` |
| 3    | Menos de um minuto, com zero a esquerda | Música de 5 segundos   | Deve resultar em `"00:05"` |
| 4    | Dois dígitos nos minutos                | Música de 600 segundos | Deve resultar em `"10:00"` |
| 5    | Valor logo abaixo de dez minutos        | Música de 599 segundos | Deve resultar em `"09:59"` |

### PL02 — Validar construtor de Música com dados inválidos

| Caso | Descrição                           | Entrada                                                        | Saída esperada                         |
| ---- | ----------------------------------- | -------------------------------------------------------------- | -------------------------------------- |
| 1    | Título vazio deve ser rejeitado     | título `""`, artista `"Queen"`, duração `355`                  | Deve lançar `IllegalArgumentException` |
| 2    | Título nulo deve ser rejeitado      | título `null`, artista `"Queen"`, duração `355`                | Deve lançar `IllegalArgumentException` |
| 3    | Artista vazio deve ser rejeitado    | título `"Bohemian Rhapsody"`, artista `""`, duração `355`      | Deve lançar `IllegalArgumentException` |
| 4    | Duração zero deve ser rejeitada     | título válido, artista válido, duração `0`                     | Deve lançar `IllegalArgumentException` |
| 5    | Duração negativa deve ser rejeitada | título válido, artista válido, duração `-10`                   | Deve lançar `IllegalArgumentException` |
| 6    | Dados válidos criam a música        | título `"Bohemian Rhapsody"`, artista `"Queen"`, duração `355` | Objeto criado, com id maior que zero   |

## PL03 — `Playlist.adicionar(música)`

Cobrir:

* Adicionar em playlist com espaço (retorna `true` e a quantidade sobe).
* Adicionar até encher a playlist.
* O que ultrapassa a capacidade retorna `false`.

| Caso | Descrição                            | Entrada                                                        | Saída esperada                         |
| ---- | ------------------------------------ | -------------------------------------------------------------- | -------------------------------------- |
| 1    | Adicionar em playlist com espaço     | duas músicas válidas                                           | Retorna `true` e a quantidade aumenta  |
| 2    | Lotar a playlist                     | For para adicionar músicas                                     | Deve adicionar e a quantidade aumenta  |
| 3    | Ultrapassar o limite da playlist     | 101 músicas em uma playlist                                    | Retorna `false` e não alterada nada    |
| 4    | Adicionar uma música vazia           | Música null                                                    | Deve lançar `IllegalArgumentException` |

## PL04 — `Playlist.getNaPosicao(indice)`

Cobrir:

* Posição válida devolve a música certa.
* Índice negativo.
* Índice além da quantidade.

| Caso | Descrição                             | Entrada                                                        | Saída esperada                          |
| ---- | ------------------------------------- | -------------------------------------------------------------- | --------------------------------------- |
| 1    | Posição válida devolve a música certa | Indice 1                                                       | Retorna `música 1`                      |
| 2    | Índice negativo                       | Indice -1                                                      | Deve lançar `IndexOutOfBoundsException` |
| 3    | Índice além da quantidade             | Indice > quantidade                                            | Deve lançar `IndexOutOfBoundsException` |

## PL05 — `Playlist.removerNaPosicao(indice)`

Cobrir:

* Remoção de uma posição válida reorganiza sem deixar buraco (a música seguinte assume a posição).
* Índice inválido.

| Caso | Descrição                             | Entrada                                                        | Saída esperada                          |
| ---- | ------------------------------------- | -------------------------------------------------------------- | --------------------------------------- |
| 1    | Remover música válida                 | Indice 3                                                       | Remove música sem deixar buraco         |
| 2    | Índice negativo                       | Indice -1                                                      | Deve lançar `IndexOutOfBoundsException` |
| 3    | Índice além da quantidade             | Indice > quantidade                                            | Deve lançar `IndexOutOfBoundsException` |

## PL06 — `Plataforma`

Métodos:

* `buscarMusica(título)`
* `buscarMusicaPorId(id)`

Cobrir:

* Música cadastrada e encontrada.
* Busca por título inexistente.
* Busca por ID inexistente.

| Caso | Descrição                             | Entrada                                                        | Saída esperada                          |
| ---- | ------------------------------------- | -------------------------------------------------------------- | --------------------------------------- |
| 1    | Buscar música válida por Id           | Indice 3                                                       | Retornar música encontrada              |
| 2    | Buscar música válida por Título       | titulo "Ride"                                                  | Retornar música encontrada              |
| 3    | Buscar por id inexistente             | Indice 101                                                     | Retornar `null`                         |
| 4    | Buscar por título inexistente         | titulo "Riding"                                                | Retornar `null`                         |

## PL07 — `Musica.reproduzir()`

Cobrir:

* Cada chamada aumenta o contador de reproduções em um.

| Caso | Descrição                                        | Entrada                                                        | Saída esperada                          |
| ---- | ------------------------------------------------ | -------------------------------------------------------------- | --------------------------------------- |
| 1    | Reprodução válida aumenta o contador em um       | Chamar reproduzir() para uma música válida                     | Aumentar contador em um                 |
| 2    | Várias reproduções aumentam o contador crescente | Chamar reproduzir() para uma música válida 5 vezes             | Aumentar contador em cinco              |
| 3    | Reproduzir num loop                              | Chamar reproduzir() num for                                    | Aumentar diversas vezes o contador      |

## PL08 — Bônus: Contadores de ID

Cobrir:

* IDs de Música saem sequenciais (`1`, `2`, `3`...).
* IDs de Música são independentes dos IDs de Usuário.

| Caso | Descrição                                        | Entrada                                                        | Saída esperada                                |
| ---- | ------------------------------------------------ | -------------------------------------------------------------- | --------------------------------------------- |
| 1    | IDs de Música devem ser sequenciais              | Criar duas músicas válidas                                     | A primeira deve receber ID 1 e a segunda ID 2 |
| 2    | IDs de Usuários devem ser sequenciais            | Criar dois usuários válidos                                    | O primeiro deve receber ID 1 e o segundo ID 2 |
| 3    | IDs de Música e Usuário devem ser independentes  | Criar dois usuários válidos e uma música válida                | A música deve receber seu próprio ID - 1      |
