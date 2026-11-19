# Jogo da Velha em Java (Terminal)

Projeto desenvolvido para a disciplina de Programação Orientada a Objetos, implementando um Jogo da Velha executado via terminal onde um jogador humano enfrenta o computador.
---
## Integrantes do Grupo

* **Beatriz Caroline Moreno Tavares** — RA: 25021024
* **Breno Masiesque** — RA: 25020087
* **João Vitor Teles Carneiro** — RA: 25019774

## Estrutura do Projeto

O código-fonte está organizado da seguinte forma:

```text
Velha/
├── Velha.java                   # Classe principal que coordena o fluxo das partidas
└── jogador/
    ├── AnalistaSituacional.java # Verifica condições de vitória, empate e fim de jogo
    ├── Jogador.java             # Interface que define o contrato comum aos jogadores
    ├── JogadorAutomatico.java   # Implementação da inteligência do computador
    ├── JogadorHumano.java       # Leitura e validação das jogadas via teclado
    ├── Marca.java               # Representação e validação dos símbolos (X e O)
    └── Tabuleiro.java           # Gerenciamento da matriz 3x3 e acesso às posições
```

## Instruções de Compilação e Execução

### Pré-requisitos
* **Java Development Kit (JDK)** instalado (versão 8 ou superior) e configurado nas variáveis de ambiente (`PATH`).

### Passo a passo pelo Terminal

1. Abra o terminal e navegue até a pasta `Velha` (onde se encontra o arquivo `Velha.java`):
   ```bash
   cd caminho/para/Jogo-da-Velha-main/Velha
   ```

2. Compile a classe principal e o pacote `jogador`:
   ```bash
   javac Velha.java jogador/*.java
   ```

3. Execute o programa:
   ```bash
   java Velha
   ```


## Estratégia do Jogador Automático

O `JogadorAutomatico` avalia o estado atual do tabuleiro a cada turno e toma decisões seguindo estritamente a seguinte ordem de prioridade:

1. **Vencer imediatamente:** Verifica todas as linhas, colunas e diagonais em busca de duas marcas próprias acompanhadas de uma posição livre. Caso encontre, preenche a posição restante para garantir a vitória.
2. **Bloquear o adversário:** Se não houver chance de vitória imediata, verifica se o jogador humano possui duas marcas alinhadas com uma casa livre e ocupa essa posição para impedir a derrota no próximo turno.
3. **Preparar uma possibilidade de vitória (Critério adotado):** Quando não há risco imediato nem vitória garantida, o computador busca posições estratégicas que favoreçam a formação de futuras linhas vencedoras — priorizando o **centro do tabuleiro** (por participar de 4 combinações possíveis de vitória: 1 linha, 1 coluna e 2 diagonais), seguido pelos **cantos** (cada um participa de 3 combinações) ou posições em fileiras onde já possua uma marca própria e nenhuma marca do adversário.
4. **Ocupar uma posição livre:** Caso nenhuma das condições anteriores se aplique, seleciona a primeira posição disponível (ou uma posição livre aleatória) no tabuleiro.


## Limitações e Observações

* **Interface:** A interação ocorre exclusivamente via linha de comando (terminal), exigindo que o usuário informe coordenadas numéricas válidas para linha e coluna.
* **Dificuldade:** Conforme especificado nos requisitos, o jogador automático utiliza uma heurística baseada em prioridades, não sendo um algoritmo invencível (como Minimax completo), o que permite ao jogador humano vencer caso crie múltiplas ameaças simultâneas (*fork*).
* **Funcionalidades:** Todas as funcionalidades obrigatórias — validação de entradas, alternância de turnos via polimorfismo, clonagem profunda do tabuleiro (`clone()` e construtor de cópia), tratamento de empate e reinício de partida — foram implementadas e testadas.