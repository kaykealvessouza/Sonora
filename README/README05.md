# Sonora — Fase 05

Projeto desenvolvido para a disciplina de **Programação Orientada a Objetos** da Universidade Regional de Blumenau (FURB).

## Contexto

Nas fases anteriores, o Sonora trabalhava principalmente com músicas como conteúdo da plataforma. Nesta fase, o sistema passa a reconhecer diferentes tipos de conteúdo, permitindo que músicas e podcasts compartilhem características em comum.

Uma música e um podcast possuem informações como:

* ID;
* Título;
* Duração;
* Capacidade de serem reproduzidos.

Para representar essas características compartilhadas, foi criada a superclasse `Conteudo`.

A classe `Musica` passa a herdar de `Conteudo`, enquanto uma nova classe `Podcast` também é criada como subclasse.

A hierarquia implementada é:

```text
                 Conteudo
                /        \
               /          \
          Musica        Podcast
```

Dessa forma, os atributos e comportamentos comuns ficam concentrados na superclasse, enquanto cada subclasse mantém apenas suas características específicas.

---

## Objetivos da fase

Os principais objetivos desta fase são:

1. Criar a superclasse `Conteudo`.
2. Aplicar herança utilizando `extends`.
3. Fazer `Musica` herdar de `Conteudo`.
4. Criar a classe `Podcast` como subclasse de `Conteudo`.
5. Utilizar membros `protected` na hierarquia.
6. Utilizar `super(...)` nos construtores das subclasses.
7. Utilizar `super.toString()` para reaproveitar o comportamento da superclasse.
8. Sobrescrever métodos utilizando `@Override`.
9. Trabalhar com a herança de `Object` através da sobrescrita de `toString()`.
10. Demonstrar a hierarquia funcionando no `App`.

---

# Parte A — Superclasse Conteudo

A classe `Conteudo` representa a base comum para os diferentes tipos de conteúdo existentes no Sonora.

Ela concentra as informações que são compartilhadas por `Musica` e `Podcast`.

## Atributos

A classe possui:

* `id` — identificador único do conteúdo.
* `titulo` — título do conteúdo.
* `duracaoSegundos` — duração do conteúdo em segundos.

O ID é gerado automaticamente através de um contador estático.

As informações comuns ficam na superclasse para evitar duplicação nas subclasses.

## Validações

A classe `Conteudo` mantém as validações comuns aos diferentes tipos de conteúdo.

### Título

O título:

* não pode ser `null`;
* não pode ser vazio.

Caso a regra seja violada, é lançada uma:

```text
IllegalArgumentException
```

### Duração

A duração deve ser maior que zero.

Valores iguais ou menores que zero também resultam em:

```text
IllegalArgumentException
```

## Métodos

A classe fornece getters e setters para seus atributos, mantendo as validações necessárias.

O acesso ao ID possui visibilidade `protected` quando necessário para utilização pelas subclasses.

A classe também possui o método:

```java
reproduzir()
```

que informa na tela que o conteúdo está sendo reproduzido.

---

# Parte B — Herança com Musica e Podcast

## Musica

A classe `Musica` passa a herdar de:

```java
Conteudo
```

A declaração da classe utiliza:

```java
public class Musica extends Conteudo
```

Com a herança, `Musica` deixa de manter diretamente os atributos que agora pertencem à superclasse:

* `id`;
* `titulo`;
* `duracaoSegundos`.

A classe mantém apenas as características específicas de uma música:

* `artista`;
* `album`.

O construtor utiliza `super(...)` para inicializar a parte herdada:

```java
super(titulo, duracaoSegundos);
```

Depois disso, a própria `Musica` inicializa seus atributos específicos.

---

## Podcast

A classe `Podcast` também herda de:

```java
Conteudo
```

Sua declaração utiliza:

```java
public class Podcast extends Conteudo
```

Além dos atributos herdados, o podcast possui:

* `apresentador`;
* `numeroEpisodio`.

O número do episódio deve ser maior ou igual a `1`.

Caso seja informado um número inválido, deve ser lançada:

```text
IllegalArgumentException
```

Assim como em `Musica`, o construtor de `Podcast` utiliza `super(...)` como primeiro comando para inicializar os atributos pertencentes à superclasse.

---

# Parte C — Sobrescrita de métodos

Toda classe Java herda de `Object`, incluindo o método:

```java
toString()
```

Nesta fase, o método é sobrescrito para permitir que cada tipo de conteúdo apresente suas informações de maneira específica.

## Conteudo.toString()

A implementação da superclasse apresenta as informações comuns:

```text
[id] título (duração)
```

Por exemplo:

```text
[1] Bohemian Rhapsody (355s)
```

---

## Musica.toString()

A classe `Musica` sobrescreve `toString()` utilizando:

```java
@Override
```

A implementação reutiliza as informações da superclasse através de:

```java
super.toString()
```

e acrescenta os dados específicos da música:

* artista;
* álbum.

Dessa maneira, a subclasse não precisa duplicar a lógica existente em `Conteudo`.

---

## Podcast.toString()

A classe `Podcast` também sobrescreve `toString()` utilizando:

```java
@Override
```

A implementação utiliza:

```java
super.toString()
```

para reaproveitar os dados comuns e adiciona:

* apresentador;
* número do episódio.

---

# Uso de super

A palavra-chave `super` é utilizada em dois momentos principais nesta fase.

## Construtores

As subclasses utilizam:

```java
super(titulo, duracaoSegundos);
```

para chamar o construtor da classe `Conteudo`.

Isso permite que a própria superclasse seja responsável pela inicialização e validação dos atributos que pertencem a ela.

## Métodos

Nas implementações de `toString()` das subclasses:

```java
super.toString()
```

é utilizado para reaproveitar a representação dos dados comuns.

A subclasse então complementa a informação com seus próprios atributos.

---

# Parte D — Demonstração no App

O `App` foi atualizado para demonstrar o funcionamento da hierarquia de herança.

A demonstração deve:

1. Criar pelo menos duas músicas.
2. Criar pelo menos um podcast.
3. Chamar `reproduzir()` em cada objeto.
4. Imprimir cada objeto através do `toString()` sobrescrito.

Como `reproduzir()` está definido em `Conteudo`, tanto `Musica` quanto `Podcast` conseguem utilizá-lo através da herança.

Exemplo conceitual:

```text
Musica
   ↓
herda reproduzir()
   ↓
Conteudo
```

e:

```text
Podcast
   ↓
herda reproduzir()
   ↓
Conteudo
```

Não é necessário implementar novamente `reproduzir()` nas subclasses.

---

# Tratamento de exceções

O tratamento de exceções utilizado nas fases anteriores foi mantido.

As entradas inválidas continuam sendo tratadas através de exceções, principalmente:

```text
IllegalArgumentException
```

O `App` utiliza `try/catch` para impedir que entradas inválidas encerrem o programa inesperadamente.

O menu deve continuar funcionando mesmo quando o usuário fornece dados inválidos.

---

# Restrições

Esta fase possui algumas limitações importantes.

### Herança

É utilizada apenas herança simples.

Não são utilizadas interfaces.

### Classes abstratas

`Conteudo` permanece uma classe concreta.

Não são utilizados:

* `abstract`;
* métodos abstratos;
* `final`.

Esses recursos serão trabalhados posteriormente.

### Polimorfismo

Não devem ser utilizados nesta fase:

* `instanceof`;
* cast de tipos;
* tratamento polimórfico de coleções da superclasse.

O objetivo desta etapa é trabalhar a **herança**, e não ainda o polimorfismo.

### Coleções

As coleções continuam utilizando apenas `ArrayList`, conforme implementado na fase anterior.

Não são utilizados:

* `LinkedList`;
* `Map`;
* `Set`;
* outras estruturas de coleção.

### Persistência

Não são utilizados:

* arquivos;
* banco de dados;
* outras formas de persistência.

---

# Estrutura da hierarquia

A estrutura principal da herança implementada nesta fase é:

```text
                  Conteudo
                     |
          +----------+----------+
          |                     |
        Musica               Podcast
          |                     |
      artista                 apresentador
      album                   numeroEpisodio
```

Os atributos comuns ficam em `Conteudo`, enquanto cada subclasse mantém apenas os atributos específicos de seu tipo.

---

# Diagrama de classes

O diagrama de classes deve ser atualizado para representar a nova hierarquia:

```text
Conteudo
   ▲
   |
   +----------------+
   |                |
 Musica          Podcast
```

No diagrama devem ser representados:

* `Conteudo` como superclasse;
* `Musica` como subclasse;
* `Podcast` como subclasse;
* atributos comuns em `Conteudo`;
* atributos específicos em suas respectivas subclasses;
* membros `protected` identificados com `#`;
* seta de especialização indicando a herança.

O diagrama deve acompanhar os códigos do projeto.

---

# Principais mudanças em relação à fase anterior

| Fase anterior                                | Fase atual                                     |
| -------------------------------------------- | ---------------------------------------------- |
| `Musica` concentrava todos os seus atributos | Atributos comuns foram movidos para `Conteudo` |
| Existia apenas o tipo `Musica`               | Agora existem `Musica` e `Podcast`             |
| Não havia superclasse para conteúdos         | Criada a superclasse `Conteudo`                |
| Sem herança                                  | `Musica extends Conteudo`                      |
| —                                            | `Podcast extends Conteudo`                     |
| `toString()` específico                      | `toString()` sobrescrito nas subclasses        |
| —                                            | Uso de `super(...)`                            |
| —                                            | Uso de `super.toString()`                      |
| —                                            | Membros `protected` na hierarquia              |

---

# Tecnologias e conceitos utilizados

* Java
* Programação Orientada a Objetos
* Herança
* `extends`
* Superclasse e subclasses
* `protected`
* `super`
* `@Override`
* `toString()`
* Classe `Object`
* `ArrayList`
* Tratamento de exceções com `try/catch`
* UML

---

# Entregáveis

Ao final da fase, o projeto deve conter:

* Projeto `sonora-fase05`, clonado da fase anterior.
* Classe `Conteudo`.
* `Musica` herdando de `Conteudo`.
* Classe `Podcast` herdando de `Conteudo`.
* Validações da superclasse e das subclasses.
* Sobrescrita de `toString()` em `Musica` e `Podcast`.
* Uso de `super(...)` nos construtores.
* Uso de `super.toString()` nas sobrescritas.
* Demonstração de músicas e podcast no `App`.
* Diagrama de classes atualizado com a hierarquia de herança.
* Tratamento de exceções mantido em todo o código.
* Projeto compilando e executando sem quebrar as funcionalidades das fases anteriores.
