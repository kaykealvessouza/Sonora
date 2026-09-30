# Sonora Fase 2
### Início: 28/08/2025

## Objetivo da Fase

O Sonora funciona, compila e faz o cadastro rodar. Mas ele é ingênuo: se você mandar criar uma música com duração negativa, ele obedece. Se o usuário digitar letra onde era para digitar número, o programa explode na cara dele com aquela pilha de vermelho no console.

Nesta fase a gente resolve isso.

O combinado continua o mesmo de sempre:

1. Você clona a pasta da fase anterior para uma nova (`sonora-fase01` vira `sonora-fase02`).
2. A Fase 01 fica congelada, intacta. Você não mexe mais nela.
3. Você evolui as mesmas classes aplicando o que aprendeu sobre exceções, sem quebrar o que já funcionava.

> Se a Fase 01 estava bem feita, esta fase vai fluir. Se estava torta, agora é a hora de endireitar.

O foco desta fase é deixar o sistema robusto:

* Impedir que objetos nasçam em estado inválido.
* Transformar parte da sinalização por valor de retorno em lançamento de exceção.
* Blindar o menu contra digitação errada.
* Aprender a diferenciar situações que devem gerar exceção de situações que são resultados normais do sistema.

Nada de coleção, nada de herança ainda; esses assuntos continuam guardados para as próximas unidades.

#

# O Que *PODE* Usar! (e vai precisar)

```java
• Tudo que você já usava na Fase 01:
  classes, private, construtores, static, sobrecarga, arrays e Scanner
• throw para lançar exceções
• IllegalArgumentException
• IllegalStateException
• NumberFormatException
• ArithmeticException
• ArrayIndexOutOfBoundsException
• IndexOutOfBoundsException
• try
• catch
• Múltiplos catch
• catch por superclasse
• finally
```

## O Que *NÃO PODE* Usar! (é sério)

```java
• ArrayList, List, Map, Set ou qualquer coleção do java.util para guardar dados
  (continua tudo em array)

• Herança (extends) ou interfaces (implements)

• Criar suas próprias classes de exceção
  (subclasses de Exception)

• Leitura ou escrita de arquivos

• Bibliotecas externas
```

### Sobre `throws`

Os slides mostram que `throws` serve para delegar ao método chamador o tratamento de exceções verificadas, como as relacionadas à leitura de arquivos.

Como nesta fase você só vai lançar exceções não verificadas, como `IllegalArgumentException`, você não precisa escrever `throws` nas assinaturas.

É importante saber que `throws` existe e para que serve, mas usá-lo nesta fase é opcional.

#

# 3. Blindando os Construtores e Setters

Na Fase 01, um objeto podia nascer zoado e ninguém reclamava.

Agora não.

A regra de ouro desta seção é:

> Um objeto não pode existir em estado inválido.

Se os dados que chegam no construtor não fazem sentido, o construtor lança uma exceção e o objeto simplesmente não nasce.

O padrão é:

```java
public void setSalario(double novoSalario) {
    if (novoSalario < 0) {
        throw new IllegalArgumentException("Salário incorreto");
    }

    salario = novoSalario;
}
```

Primeiro valida.

Se estiver errado, lança exceção.

Se estiver correto, atribui.

Aplique essa mesma ideia no Sonora.

#

## 3.1. Classe `Musica`

As seguintes validações são obrigatórias:

* `titulo` não pode ser `null`, vazio ou conter apenas espaços.
* `artista` não pode ser `null`, vazio ou conter apenas espaços.
* `duracaoSegundos` precisa ser maior que zero.
* Música de 0 segundos ou duração negativa não existe.

Essas validações devem acontecer no construtor.

Como não há setter público para esses campos, o construtor é o único portão de entrada e deve barrar valores inválidos.

Exemplo:

```java
if (titulo == null || titulo.trim().isEmpty()) {
    throw new IllegalArgumentException(
        "Título inválido: o título não pode ser vazio."
    );
}
```

#

## 3.2. Classe `Usuario`

As seguintes validações são obrigatórias:

* `nome` não pode ser `null` nem vazio.
* `email` não pode ser `null` nem vazio.
* `email` precisa conter um `@`.

Não é necessário validar um e-mail de verdade. Basta verificar se não está vazio e se possui um `@`.

Exemplo:

```java
if (email == null || email.trim().isEmpty() || !email.contains("@")) {
    throw new IllegalArgumentException(
        "E-mail inválido: informe um e-mail contendo @."
    );
}
```

#

## 3.3. Classe `Playlist`

As seguintes validações são obrigatórias:

* `nome` não pode ser `null` nem vazio.
* `dono` não pode ser `null`.

Playlist sem dono não faz sentido no modelo.

Exemplo:

```java
if (dono == null) {
    throw new IllegalArgumentException(
        "Dono inválido: a playlist precisa ter um usuário responsável."
    );
}
```

#

## Mensagens das Exceções

A mensagem da exceção não é decoração.

Ela é a informação que descreve o erro.

Uma mensagem como:

```text
erro
```

não ajuda em nada.

Uma mensagem como:

```text
Duração inválida: -30. A duração deve ser maior que zero.
```

é muito melhor.

As mensagens devem permitir entender rapidamente o que deu errado.

#

# 4. Trocando Sinalização por Retorno por Lançamento de Exceção

Na Fase 01, vários métodos avisavam que algo deu errado devolvendo um valor:

```java
adicionar() -> false
buscar() -> null
```

Isso funcionava, mas nem todo problema tem o mesmo peso.

Nesta fase, algumas situações passam a gerar exceções, enquanto outras continuam utilizando valores de retorno.

A principal ideia é saber diferenciar essas situações.

#

## 4.1. O Que VIRA Exceção

Situações que representam uso indevido da classe devem gerar exceção.

Ou seja:

> O programador chamou o método com um argumento que nunca deveria ter passado.

Ajuste a classe `Playlist`.

### `getNaPosicao(int indice)`

Na Fase 01, quando o índice era inválido, o método devolvia `null`.

Agora deve lançar:

```java
throw new IndexOutOfBoundsException(...);
```

Pedir a posição 50 de uma playlist que possui apenas 3 músicas é um erro de quem chamou o método, não uma resposta normal da operação.

### `removerNaPosicao(int indice)`

Mesma regra.

Índice inválido agora lança:

```java
IndexOutOfBoundsException
```

em vez de devolver `false`.

#

## 4.2. O Que CONTINUA como Retorno

Nem tudo vira exceção.

Situações que são resultados esperados e legítimos da operação continuam sendo sinalizadas por retorno.

### `adicionar(Musica musica)`

Adicionar em uma playlist cheia continua devolvendo:

```java
false
```

Playlist cheia é um estado normal e previsível do sistema, não um erro de programação.

Porém:

```java
adicionar(null)
```

é uso indevido.

Nesse caso deve lançar:

```java
IllegalArgumentException
```

### Buscas na `Plataforma`

Os métodos:

```java
buscarMusicaPorId(int)
buscarMusica(String)
```

continuam devolvendo:

```java
null
```

quando não encontram a música.

Procurar algo e não encontrar é uma resposta válida de uma busca.

#

## Regra Prática

```text
Uso errado da classe
        ↓
    EXCEÇÃO

Resultado normal e previsto
        ↓
     RETORNO
```

Exemplos:

```text
Playlist com 3 músicas
→ pedir posição 50
→ EXCEÇÃO

Playlist cheia
→ tentar adicionar outra música
→ false

Buscar música inexistente
→ null

Adicionar música null
→ EXCEÇÃO
```

Essa distinção é importante e vale para muito além do Sonora.

#

# 5. Blindando o Menu do `App`

Agora é onde o usuário digita coisa errada e o programa não pode morrer por causa disso.

Na Fase 01, a dica era utilizar:

```java
hasNextInt()
```

como paliativo.

Agora o tratamento será feito com exceções.

#

## 5.1. Leitura de Opções e Números

Leia a entrada do usuário como texto e converta utilizando:

```java
Integer.parseInt()
```

dentro de um `try`.

Se o usuário digitar:

```text
abc
```

onde deveria existir um número, `parseInt()` lança:

```java
NumberFormatException
```

O `catch` deve avisar o usuário e permitir que ele tente novamente.

Exemplo:

```java
Scanner teclado = new Scanner(System.in);

int opcao;

while (true) {
    try {
        System.out.print("Escolha uma opção: ");
        opcao = Integer.parseInt(teclado.nextLine());
        break;

    } catch (NumberFormatException e) {
        System.out.println("Valor inválido. Digite um número.");
    }
}
```

A ideia é que uma entrada inválida não derrube o programa.

#

## 5.2. Capturando as Exceções das Classes

Como os construtores e alguns métodos agora podem lançar exceções, as chamadas dentro do menu precisam estar protegidas.

Por exemplo, ao cadastrar uma música:

```java
try {
    Musica m = new Musica(titulo, artista, duracao);

    plataforma.cadastrarMusica(m);

    System.out.println("Música cadastrada!");

} catch (IllegalArgumentException e) {
    System.out.println(
        "Não foi possível cadastrar: " + e.getMessage()
    );
}
```

Se o usuário tentar cadastrar:

```text
Título: Música X
Artista: Artista X
Duração: -30
```

o objeto não será criado e o programa continuará funcionando.

#

## 5.3. Múltiplos `catch`

Em pelo menos um ponto do `App`, deve existir um `try` com mais de um `catch`, tratando exceções diferentes de formas diferentes.

Uma boa candidata é a operação de reproduzir ou remover uma música por posição.

Exemplo:

```java
try {
    int pos = Integer.parseInt(teclado.nextLine());

    Musica m = playlist.getNaPosicao(pos);

    m.reproduzir();

    System.out.println("Tocando: " + m.getTitulo());

} catch (NumberFormatException e) {
    System.out.println("A posição precisa ser um número.");

} catch (IndexOutOfBoundsException e) {
    System.out.println("Essa posição não existe na playlist.");
}
```

### Atenção à ordem dos `catch`

Os `catch` são testados na ordem em que aparecem.

Uma exceção mais específica deve aparecer antes de uma mais genérica.

Correto:

```java
catch (NumberFormatException e) {
    // ...
}
catch (Exception e) {
    // ...
}
```

Errado:

```java
catch (Exception e) {
    // ...
}
catch (NumberFormatException e) {
    // ...
}
```

O segundo caso não compila, porque `Exception` já capturaria a `NumberFormatException`.

#

## 5.4. Um `finally` para Fechar

Use `finally` em pelo menos um `try` do `App`.

O `finally` executa sempre, independentemente de ter ocorrido uma exceção ou não.

Um uso honesto nesta fase é imprimir uma mensagem de operação finalizada:

```java
try {
    // operação

} catch (Exception e) {
    System.out.println("Erro: " + e.getMessage());

} finally {
    System.out.println("Operação finalizada.");
}
```

Não é necessário colocar `finally` em todo lugar.

Um bem colocado já demonstra que você entendeu para que ele serve.

#

# 6. Comportamentos que Precisam Funcionar

Isto é o que separa um "compilou" de um trabalho bem feito.

Na correção, cada um destes comportamentos poderá ser testado.

### Objeto não nasce inválido

Tentar criar:

```java
new Musica("", "X", 100)
```

ou:

```java
new Musica("Y", "Z", -5)
```

deve lançar:

```java
IllegalArgumentException
```

O objeto não é criado.

### A mensagem descreve o erro

A exceção deve possuir uma mensagem que faça sentido.

Evite mensagens genéricas como:

```text
erro
```

### Exceção onde é uso indevido

Os métodos:

```java
getNaPosicao()
removerNaPosicao()
```

com índice inválido devem lançar:

```java
IndexOutOfBoundsException
```

### Retorno onde é fluxo normal

Adicionar em uma playlist cheia:

```java
false
```

Buscar algo inexistente:

```java
null
```

Esses comportamentos continuam iguais à Fase 01.

### O menu não morre

Digitar texto onde se espera um número não deve derrubar o programa.

O `catch` deve informar o problema e o menu deve continuar funcionando.

### Múltiplos `catch`

Deve existir pelo menos um `try` com mais de um `catch`, na ordem correta.

### `finally`

Deve existir pelo menos um `finally` com uma finalidade justificável.

#

# 7. Roteiro de Demonstração

Prepare o `App` para conseguir demonstrar todos estes comportamentos.

### 1. Título inválido

Tentar cadastrar uma música com título vazio.

Esperado:

```text
IllegalArgumentException
```

A exceção deve ser tratada e o programa deve continuar vivo.

### 2. Duração inválida

Tentar cadastrar uma música com duração:

```text
0
```

ou:

```text
-10
```

Esperado:

```text
IllegalArgumentException
```

### 3. E-mail inválido

Tentar cadastrar um usuário com um e-mail sem `@`.

Esperado:

```text
IllegalArgumentException
```

### 4. Posição inexistente

Chamar:

```java
getNaPosicao()
```

com uma posição que não existe.

Esperado:

```text
IndexOutOfBoundsException
```

tratada pelo `App`.

Não deve mais retornar `null`.

### 5. Playlist cheia

Encher uma playlist até 100 músicas.

Tentar adicionar a 101ª.

Esperado:

```java
false
```

A quantidade deve continuar em 100.

Isso continua sendo retorno, não exceção.

### 6. Busca inexistente

Buscar uma música por um ID que não existe.

Esperado:

```java
null
```

### 7. Entrada inválida no menu

Quando o programa pedir um número, digitar:

```text
abc
```

Esperado:

```text
Valor inválido. Digite um número.
```

O programa deve continuar funcionando e pedir novamente.

### 8. Múltiplos `catch`

Disparar duas exceções diferentes para demonstrar que cada `catch` trata seu respectivo erro.

### 9. `finally`

Mostrar o `finally` executando:

* em um caso de sucesso;
* em um caso de erro.

#

# 8. Erros Comuns que Vão Dar Dor de Cabeça

Evite:

* Validar no construtor mas atribuir antes de validar.

  Valide primeiro e atribua depois.

* Trocar tudo por exceção.

  Playlist cheia e busca sem resultado continuam sendo situações normais e devem continuar usando retorno.

* Colocar `catch (Exception e)` antes de um `catch` mais específico.

  A superclasse deve ficar por último.

* Usar um `catch` vazio.

  Um `catch` que não faz nada esconde o erro.

* Deixar o construtor lançar exceção mas não proteger a chamada no `App`.

  Nesse caso o objeto não nasce, mas a exceção sobe até o `main` e pode derrubar o programa.

* Escrever `throws` achando que isso resolve o problema.

  `throws` apenas delega o tratamento para quem chamou o método. Em algum ponto da cadeia ainda será necessário tratar a exceção.

* Usar mensagens inúteis.

  Evite:

```text
erro
deu ruim
inválido
```

  Prefira mensagens que expliquem exatamente o problema:

```text
Duração inválida: -30. A duração deve ser maior que zero.
```

#

# 9. Regra Geral da Fase 2

```text
                 SONORA FASE 2
                       │
          ┌────────────┴────────────┐
          │                         │
     Estado inválido          Fluxo normal
          │                         │
       EXCEÇÃO                   RETORNO
          │                         │
   ┌──────┼──────┐             ┌────┴────┐
   │      │      │             │         │
 null   vazio   inválido     cheio    não achou
   │      │      │             │         │
   └──────┴──────┘             │         │
          │                    false     null
          ▼
 IllegalArgumentException

Índice inválido
        │
        ▼
IndexOutOfBoundsException
```

A ideia central da Fase 2 é simples:

> **Exceções tratam situações excepcionais ou uso incorreto da classe. Retornos representam resultados normais e esperados da operação.**

A Fase 02 deve evoluir a Fase 01 sem destruir o que já funcionava.