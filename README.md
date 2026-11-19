# Jogo da Velha em Java (Terminal)

Projeto desenvolvido para a disciplina de **Programação Orientada a Objetos**. Implementa um Jogo da Velha executado via terminal, onde um jogador humano enfrenta o computador.

---

## Integrantes do Grupo

* **Beatriz Caroline Moreno Tavares** — RA: 25021024
* **Breno Masiesque** — RA: 25020087
* **João Vitor Teles Carneiro** — RA: 25019774

---

## Estrutura do Projeto

```text
Velha/
├── Velha.java                   # Classe principal: coordena as partidas e a alternância de turnos
└── jogador/
    ├── AnalistaSituacional.java # Verifica vitória, empate e fim de jogo
    ├── Jogador.java             # Interface com o contrato comum aos jogadores
    ├── JogadorAutomatico.java   # Inteligência do computador
    ├── JogadorHumano.java       # Leitura e validação das jogadas via teclado
    ├── Marca.java               # Símbolo do jogador (X ou O), com validação
    └── Tabuleiro.java           # Matriz 3x3 e acesso às posições
```

---

## Como Compilar e Executar

### Pré-requisitos

* **JDK** (versão 8 ou superior) instalado e configurado no `PATH`.

### Passo a passo

1. Abra o terminal e entre na pasta `Velha` (onde está o `Velha.java`):

   ```bash
   cd caminho/para/Jogo-da-Velha/Velha
   ```

2. Compile a classe principal e o pacote `jogador`:

   ```bash
   javac Velha.java jogador/*.java
   ```

3. Execute:

   ```bash
   java Velha
   ```

---

## Como o Jogo Funciona

* A cada partida, um sorteio define quem começa; nas partidas seguintes, a ordem **alterna**.
* Quem é criado primeiro define a marca: se o **humano começa**, ele escolhe `X` ou `O` e o computador fica com a oposta; se o **computador começa**, ele sorteia sua marca e o humano recebe a oposta.
* O humano informa **linha** e **coluna** (de 1 a 3). Entradas inválidas ou posições ocupadas são recusadas e a pergunta se repete.
* Ao fim da partida, o programa mostra o resultado (vitória, derrota ou empate) e pergunta se o usuário quer jogar novamente.

---

## Conceitos de POO Aplicados

* **Interface e polimorfismo:** `Jogador` é implementada por `JogadorHumano` e `JogadorAutomatico`. O `Velha.java` chama `facaSuaJogada()` sem saber qual dos dois está jogando.
* **Encapsulamento:** todos os atributos são `private`, e as validações ficam nos construtores e nos métodos de acesso.
* **Sobrecarga de construtores:** `JogadorHumano` e `JogadorAutomatico` têm um construtor que define a marca sozinho e outro que recebe a marca pronta.
* **Métodos obrigatórios:** `toString`, `equals` e `hashCode` nas classes principais, além de `clone()` e construtor de cópia em `Tabuleiro`.
* **Imutabilidade:** `Marca` não possui métodos que alterem o símbolo, o que torna seguro compartilhar a mesma marca entre tabuleiros copiados.

---

## Estratégia do Jogador Automático

O `JogadorAutomatico` analisa o tabuleiro a cada turno e segue **estritamente** esta ordem de prioridade, parando na primeira regra que puder aplicar:

1. **Vencer imediatamente:** procura uma linha, coluna ou diagonal com duas marcas próprias e uma posição livre, e preenche a livre.
2. **Bloquear o adversário:** procura uma linha com duas marcas do humano e uma posição livre, e ocupa a livre para impedir a derrota.
3. **Preparar uma possibilidade de vitória:** procura uma linha com **uma marca própria e duas posições livres** e joga na primeira livre dela, ficando com duas marcas na linha.
4. **Ocupar uma posição livre:** se nenhuma regra anterior se aplicar, joga na **primeira posição livre** do tabuleiro, varrendo da esquerda para a direita e de cima para baixo.

---

## Limitações e Observações

* **Interface:** a interação é exclusivamente via terminal, com coordenadas numéricas de 1 a 3.
* **Dificuldade:** o computador usa uma heurística baseada em prioridades e **não é invencível**. Ele não simula jogadas futuras, por isso o humano pode vencer criando duas ameaças ao mesmo tempo (*fork*). Um algoritmo completo, como o Minimax, seria necessário para torná-lo imbatível.
* **Possíveis melhorias:**
  * Priorizar o **centro** e os **cantos** antes de jogar na primeira posição livre (o centro participa de 4 combinações de vitória e cada canto de 3).
  * Definir a constante com as 8 linhas vencedoras em um único lugar (hoje ela aparece em `AnalistaSituacional` e em `JogadorAutomatico`).
  * Substituir os blocos `catch` vazios por tratamento de erro com mensagem.
