# Sonora — Lista 09

Projeto desenvolvido para a disciplina de **Programação Orientada a Objetos** da Universidade Regional de Blumenau (FURB).

## Contexto

Na fase anterior, o Sonora passou a utilizar uma hierarquia de herança para representar diferentes tipos de conteúdo, com `Conteudo` como superclasse e `Musica` e `Podcast` como subclasses.

Nesta fase, essa hierarquia é aprofundada. O conceito de `Conteudo` deixa de representar um objeto genérico que pode ser instanciado diretamente e passa a ser uma **classe abstrata**.

Além disso, o Sonora passa a possuir planos de assinatura. Inicialmente existem três classes independentes:

* `PlanoGratuito`;
* `PlanoIndividual`;
* `PlanoFamilia`.

Como essas classes possuem diversos membros em comum, é aplicada a **generalização em duas etapas**, criando uma hierarquia de planos:

```text
                    Plano
                      ▲
                      |
              +-------+-------+
              |               |
       PlanoGratuito       PlanoPago
                              ▲
                              |
                       +------+------+
                       |             |
                PlanoIndividual  PlanoFamilia
```

A fase também introduz:

* classes abstratas;
* métodos abstratos;
* `final` em métodos;
* `final` em classes;
* contador de reproduções;
* associação entre `Usuario` e `Plano`;
* troca de plano pelo menu do sistema.

---

# Objetivos da fase

Os principais objetivos são:

1. Aplicar generalização em uma hierarquia de classes.
2. Criar a superclasse `Plano`.
3. Criar a superclasse intermediária `PlanoPago`.
4. Identificar e remover membros duplicados.
5. Utilizar classes abstratas.
6. Utilizar métodos abstratos.
7. Aplicar `final` em métodos e classes.
8. Transformar `Conteudo` em uma classe abstrata.
9. Criar o método abstrato `getCreditos()`.
10. Implementar o contador de reproduções.
11. Integrar planos de assinatura aos usuários.
12. Permitir troca de plano através do `App`.
13. Manter o tratamento de exceções das fases anteriores.

---

# Parte A — Generalização dos planos

A generalização foi realizada em duas rodadas.

## Rodada 1 — Superclasse Plano

Inicialmente, os planos possuíam atributos e métodos repetidos.

Os elementos comuns aos três tipos de plano foram identificados e movidos para a superclasse:

```java
Plano
```

As três classes passam a herdar de `Plano`:

```text id="t0h9cb"
Plano
  ▲
  |
  +------------------+------------------+
  |                  |                  |
PlanoGratuito   PlanoIndividual   PlanoFamilia
```

A superclasse concentra os elementos comuns, evitando duplicação entre as subclasses.

Os métodos que possuem comportamentos diferentes entre os planos permanecem nas subclasses.

---

## Rodada 2 — Superclasse PlanoPago

Depois da primeira generalização, foi possível perceber que `PlanoIndividual` e `PlanoFamilia` ainda possuíam membros em comum que não faziam sentido para `PlanoGratuito`.

Por isso foi criada uma segunda superclasse:

```java id="7d6a1q"
PlanoPago
```

A nova hierarquia passa a ser:

```text id="gk3qjv"
                    Plano
                      ▲
                      |
              +-------+-------+
              |               |
       PlanoGratuito       PlanoPago
                              ▲
                              |
                       +------+------+
                       |             |
                PlanoIndividual  PlanoFamilia
```

`PlanoPago` concentra os elementos comuns aos planos pagos, incluindo a validação do preço mensal.

`PlanoGratuito` continua herdando diretamente de `Plano`.

`PlanoIndividual` e `PlanoFamilia` passam a herdar de `PlanoPago`.

---

# Hierarquia final dos planos

## Plano

Representa a base comum dos planos de assinatura.

Por ser um conceito genérico, `Plano` passa a ser uma classe abstrata.

Ela concentra informações e comportamentos compartilhados pelos diferentes planos.

Também fornece o método `resumo()`, responsável por apresentar as informações do plano.

---

## PlanoPago

Representa a generalização dos planos que possuem mensalidade.

Também é uma classe abstrata.

Ela concentra os elementos comuns entre:

* `PlanoIndividual`;
* `PlanoFamilia`.

Entre esses elementos está o preço mensal e sua validação.

---

## PlanoGratuito

Representa o plano gratuito do Sonora.

Possui:

* nome `"Gratuito"`;
* limite de 1 dispositivo;
* anúncios;
* mensalidade igual a `0.0`.

A classe é declarada como `final`, impedindo que outras classes herdem dela.

Essa restrição representa uma regra de negócio do sistema: o plano gratuito não pode ser especializado para remover os anúncios.

---

## PlanoIndividual

Representa o plano pago individual.

Possui:

* nome `"Individual"`;
* limite de 1 dispositivo;
* preço mensal.

O cálculo da mensalidade corresponde ao preço informado.

A classe herda de `PlanoPago`.

### Classe sem membros próprios

Após as duas etapas de generalização, deve ser analisado o que restaria em `PlanoIndividual` caso o cálculo simples da mensalidade também fosse movido para `PlanoPago`.

Se a classe não possuísse mais nenhum membro próprio, ela se tornaria uma classe vazia.

Uma classe que não possui comportamento ou estado próprio após a generalização pode indicar que a especialização não possui uma responsabilidade adicional além de representar um tipo específico na hierarquia.

A explicação dessa situação é mantida como comentário no topo da classe `PlanoIndividual`, conforme solicitado na atividade.

---

## PlanoFamilia

Representa o plano pago para famílias.

Possui:

* nome `"Familia"`;
* limite de 6 dispositivos;
* preço mensal;
* quantidade de membros.

A quantidade de membros deve estar entre `1` e `6`.

A mensalidade é calculada considerando o preço base e o acréscimo para os membros adicionais.

A classe herda de `PlanoPago`.

---

# Parte B — Classes e métodos abstratos

A generalização revelou classes que representam conceitos genéricos e que não devem ser instanciadas diretamente.

Nesta fase, essas classes são transformadas em abstratas.

## Plano abstrato

A classe:

```java id="6zqdrt"
Plano
```

é declarada como:

```java id="bz3e3r"
public abstract class Plano
```

Isso impede a criação direta de objetos `Plano`.

Também são definidos métodos abstratos para comportamentos que variam entre os tipos de plano.

---

## Métodos abstratos de Plano

O método:

```java id="4lhr7s"
calcularMensalidade()
```

passa a ser abstrato.

Cada plano concreto é responsável por implementar seu próprio cálculo.

O método:

```java id="a2h7nk"
temAnuncios()
```

também passa a ser abstrato.

Cada tipo de plano informa se possui ou não anúncios.

As implementações utilizam:

```java
@Override
```

para indicar que estão sobrescrevendo métodos definidos na superclasse.

---

## PlanoPago abstrato

A classe:

```java id="sm0a0h"
PlanoPago
```

também é declarada como abstrata.

Como ela é uma classe intermediária, não precisa necessariamente implementar todos os métodos abstratos herdados de `Plano`.

As subclasses concretas assumem as responsabilidades que permanecerem abstratas.

---

# Conteudo como classe abstrata

A classe `Conteudo`, criada na fase anterior, também passa a ser abstrata.

Sua declaração passa a ser:

```java id="u7d9oe"
public abstract class Conteudo
```

Isso representa a ideia de que não existe um conteúdo genérico no Sonora.

Existem tipos concretos de conteúdo, como:

* `Musica`;
* `Podcast`.

Portanto, não deve ser possível criar:

```java id="5y34vr"
// Conteudo c = new Conteudo("Generico", 120);
```

Essa linha permanece comentada no `App`, acompanhada de um comentário explicando que o compilador rejeita a criação porque `Conteudo` é uma classe abstrata.

---

# getCreditos()

A classe `Conteudo` recebe o método abstrato:

```java id="d7g8m1"
public abstract String getCreditos();
```

Esse método representa as informações específicas que identificam os responsáveis pelo conteúdo.

Cada subclasse fornece sua própria implementação.

## Musica

A `Musica` pode utilizar informações como:

```text id="qk8z5s"
artista + álbum
```

## Podcast

O `Podcast` pode utilizar:

```text id="rq5x5t"
número do episódio + apresentador
```

---

# reproduzir()

O método `reproduzir()` permanece na classe `Conteudo`, mas passa a utilizar `getCreditos()` na mensagem.

Exemplo:

```text id="o3g1j9"
Reproduzindo: Bohemian Rhapsody - Queen (A Night at the Opera)
```

Como `getCreditos()` é abstrato, cada tipo concreto de conteúdo fornece as informações específicas.

---

# Parte C — final

A palavra-chave `final` é utilizada para impedir alterações em comportamentos ou estruturas que não devem ser modificados pelas subclasses.

## reproduzir() final

A classe `Conteudo` passa a possuir um contador de reproduções:

```java id="s4r0n2"
reproducoes
```

O método:

```java id="a6o3pv"
reproduzir()
```

incrementa esse contador antes de exibir a mensagem de reprodução.

O método é declarado como:

```java id="5l0qcc"
public final void reproduzir()
```

Isso impede que `Musica` ou `Podcast` sobrescrevam o método e alterem sua lógica.

O contador possui getter, mas não possui setter público.

---

## resumo() final

O método:

```java id="r3q1ku"
resumo()
```

é declarado como `final` em `Plano`.

O formato do resumo é comum a todos os planos.

O que muda entre os tipos de plano é o cálculo da mensalidade, que já é garantido pelo método abstrato:

```java
calcularMensalidade()
```

Assim, as subclasses não podem alterar a estrutura do resumo.

---

## PlanoGratuito final

A classe:

```java id="x0h7qd"
PlanoGratuito
```

é declarada como:

```java
public final class PlanoGratuito
```

Isso impede a criação de subclasses de `PlanoGratuito`.

A regra representa uma restrição de negócio do Sonora: não é permitido criar uma especialização do plano gratuito que altere, por exemplo, o comportamento relacionado aos anúncios.

No `App`, fica registrada uma tentativa comentada de extensão da classe, acompanhada da explicação do erro de compilação.

Exemplo:

```java id="o6m0tw"
// class PlanoGratuitoPremium extends PlanoGratuito { }
// Erro: não é possível herdar de uma classe final.
```

---

# Parte D — Integração com Usuario

A classe `Usuario` passa a possuir um plano de assinatura.

Cada usuário possui exatamente um plano atual.

Um usuário recém-criado começa automaticamente com:

```text id="2u7i1e"
PlanoGratuito
```

---

## assinar()

Foi criado na classe `Usuario` o método:

```java id="m9b8wa"
assinar(Plano novoPlano)
```

Esse método permite trocar o plano atual do usuário.

Exemplo:

```text id="q6b0k2"
Usuário
   |
   └── PlanoGratuito

        ↓ assinar()

Usuário
   |
   └── PlanoIndividual
```

Um plano `null` não é permitido.

Nesse caso deve ser lançada:

```text id="8f3p7n"
IllegalArgumentException
```

---

# Associação Usuario e Plano

O relacionamento entre `Usuario` e `Plano` também é representado no diagrama UML.

Um usuário possui um plano atual, enquanto um mesmo tipo de plano pode estar associado a vários usuários.

A associação deve conter no diagrama:

* papel;
* nome da associação;
* multiplicidade;
* navegabilidade.

A multiplicidade do lado do `Usuario` representa que cada usuário possui um plano atual.

A multiplicidade do lado do `Plano` representa que um mesmo plano pode ser utilizado por vários usuários.

---

# Alterações no App

O menu do `App` foi atualizado para trabalhar com os planos de assinatura.

O usuário pode:

* visualizar o plano atual;
* trocar para o plano gratuito;
* assinar o plano individual;
* assinar o plano família;
* informar os valores necessários para criação dos planos pagos.

As entradas inválidas continuam sendo tratadas através de `try/catch`.

O menu não deve ser encerrado por uma entrada inválida.

---

# Demonstração das reproduções

A demonstração do sistema deve reproduzir músicas e podcasts mais de uma vez.

Depois das reproduções, o contador de cada conteúdo deve ser exibido.

Exemplo:

```text id="bq2v1c"
Bohemian Rhapsody → 3 reproduções
Podcast Episódio 5 → 2 reproduções
```

O contador é mantido pela superclasse `Conteudo`, sendo compartilhado pela implementação de `reproduzir()` de todos os tipos concretos.

---

# Diagrama de classes

O diagrama deve ser atualizado para representar:

* `Conteudo` como classe abstrata;
* `Plano` como classe abstrata;
* `PlanoPago` como classe abstrata;
* `PlanoGratuito` como classe `final`;
* `Musica` herdando de `Conteudo`;
* `Podcast` herdando de `Conteudo`;
* `PlanoGratuito` herdando diretamente de `Plano`;
* `PlanoIndividual` herdando de `PlanoPago`;
* `PlanoFamilia` herdando de `PlanoPago`;
* métodos abstratos em itálico;
* membros `protected` indicados com `#`;
* associação entre `Usuario` e `Plano`;
* multiplicidades;
* papéis;
* nomes das associações;
* navegabilidade.

Uma representação simplificada da hierarquia é:

```text id="k8f3wl"
                         <<abstract>>
                          Conteudo
                             ▲
                             |
                    +--------+--------+
                    |                 |
                 Musica            Podcast


                         <<abstract>>
                            Plano
                             ▲
                  +----------+----------+
                  |                     |
            <<final>>              <<abstract>>
          PlanoGratuito            PlanoPago
                                       ▲
                                +------+------+
                                |             |
                         PlanoIndividual  PlanoFamilia
```

---

# Principais mudanças em relação à fase anterior

| Fase anterior                                   | Lista 09                                  |
| ----------------------------------------------- | ----------------------------------------- |
| `Conteudo` concreta                             | `Conteudo` abstrata                       |
| `Conteudo` podia ser instanciada                | `Conteudo` não pode ser instanciada       |
| Não existia `getCreditos()`                     | Método abstrato `getCreditos()`           |
| `reproduzir()` podia ser sobrescrito            | `reproduzir()` é `final`                  |
| Não havia contador de reproduções na hierarquia | `Conteudo` possui contador de reproduções |
| Planos independentes                            | Hierarquia de planos                      |
| Sem `Plano`                                     | `Plano` abstrata                          |
| Sem `PlanoPago`                                 | `PlanoPago` abstrata                      |
| —                                               | Métodos abstratos                         |
| —                                               | `resumo()` `final`                        |
| —                                               | `PlanoGratuito` `final`                   |
| `Usuario` sem plano                             | `Usuario` possui um `Plano`               |
| —                                               | Troca de plano com `assinar()`            |

---

# Restrições

Esta fase deve respeitar as seguintes restrições:

* Utilizar herança simples.
* Utilizar classes abstratas quando especificado.
* Utilizar métodos abstratos quando especificado.
* Utilizar `final` conforme as regras da atividade.
* Não utilizar interfaces.
* Não utilizar recursos de polimorfismo que ainda não foram solicitados.
* Manter os `ArrayList` já utilizados na fase anterior.
* Não utilizar `LinkedList`, `Map`, `Set` ou outras coleções.
* Não utilizar arquivos.
* Não utilizar banco de dados.
* Manter o tratamento de exceções das fases anteriores.
* Não quebrar as funcionalidades já existentes.

---

# Estrutura do projeto

A estrutura esperada inclui as classes relacionadas ao conteúdo e aos planos:

```text id="x9j4na"
sonora-lista09/
├── src/
│   └── main/
│       ├── App.java
│       ├── Conteudo.java
│       ├── Musica.java
│       ├── Podcast.java
│       ├── Usuario.java
│       ├── Playlist.java
│       ├── Plataforma.java
│       ├── Plano.java
│       ├── PlanoPago.java
│       ├── PlanoGratuito.java
│       ├── PlanoIndividual.java
│       └── PlanoFamilia.java
│
├── docs/
│   └── diagrama-classes.png
│
└── README.md
```

---

# Tecnologias e conceitos utilizados

* Java
* Programação Orientada a Objetos
* Generalização
* Herança
* Classes abstratas
* Métodos abstratos
* `extends`
* `super`
* `protected`
* `@Override`
* `final`
* `ArrayList`
* UML
* Tratamento de exceções
* Encapsulamento

---

# Entregáveis

Ao final da Lista 09, o projeto deve conter:

* Projeto `sonora-lista09`, baseado na fase anterior.
* Hierarquia generalizada dos planos.
* Classe abstrata `Plano`.
* Classe abstrata `PlanoPago`.
* Classe `PlanoGratuito` declarada como `final`.
* Classes `PlanoIndividual` e `PlanoFamilia`.
* `Conteudo` declarada como classe abstrata.
* Método abstrato `getCreditos()`.
* `reproduzir()` `final`, com contador de reproduções.
* `resumo()` `final`.
* Integração de `Plano` com `Usuario`.
* Método `assinar(Plano novoPlano)`.
* Opções de troca e consulta de plano no `App`.
* Demonstração dos contadores de reprodução.
* Tentativas comentadas de instanciar classes abstratas.
* Tentativa comentada de estender `PlanoGratuito`.
* Diagrama UML atualizado.
* Tratamento de exceções mantido.
* Projeto compilando e executando corretamente sem quebrar as funcionalidades anteriores.
