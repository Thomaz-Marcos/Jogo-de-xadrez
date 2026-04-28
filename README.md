# Xadrez em Java

Projeto de xadrez desenvolvido em Java com interface gráfica em Swing, com movimentação de peças, validação de jogadas, controle de turno, capturas e uma IA simples para jogadas automáticas.

## Funcionalidades

- Tabuleiro gráfico com Java Swing
- Movimentação de peças
- Validação de jogadas
- Controle de turno entre brancas e pretas
- Captura de peças
- Exibição do status da partida
- Exibição do modo de jogo atual
- Seleção de modo de jogo:
    - Jogador vs Jogador
    - Jogador vs IA
    - IA vs IA
- IA simples com escolha de movimentos válidos

## Estrutura do projeto

```text
src/main/java/com/TMZ/Xadrez
├── controller
│   └── GameController.java
├── model
│   ├── Board.java
│   ├── GameMode.java
│   ├── GameState.java
│   ├── Move.java
│   ├── Piece.java
│   ├── PieceColor.java
│   ├── PieceType.java
│   └── Positivo.java
├── service
│   ├── JogoService.java
│   ├── Regras.java
│   └── RegrasIA.java
└── view
    ├── BoardPanel.java
    ├── MainFrame.java
    └── SquareButton.java
```

## Tecnologias utilizadas

- Java
- Java Swing
- Maven
- Programação Orientada a Objetos

## Como executar

### Pré-requisitos

- Java 17 ou superior
- Maven instalado

### Passos

1. Clone o repositório:

```bash
git clone https://github.com/Thomaz-Marcos/Jogo-de-xadrez.git
```

2. Acesse a pasta do projeto:

```bash
cd Jogo-de-xadrez
```

3. Compile o projeto:

```bash
mvn clean package
```

4. Execute a aplicação pela classe principal do projeto.

Se o projeto estiver configurado com o Maven Exec Plugin, você pode executar com:

```bash
mvn exec:java -Dexec.mainClass="com.TMZ.Xadrez.Main"
```

> Caso o nome da classe principal seja diferente de `com.TMZ.Xadrez.Main`, substitua pelo nome correto da sua classe `main`.

> Se preferir, você também pode executar a classe principal diretamente pela IDE, como IntelliJ IDEA, Eclipse ou VS Code. [web:795][web:809][web:811]

## Como jogar

1. Escolha o modo de jogo.
2. Clique em uma peça do tabuleiro.
3. Clique na casa de destino.
4. O sistema valida se o movimento é permitido.
5. Se for válido, a jogada é realizada e o turno é alterado.
6. Quando o modo incluir IA, a jogada automática será executada no turno correspondente.

## IA do jogo

A classe `RegrasIA` gera os movimentos válidos para a cor atual, separa os movimentos de captura e escolhe uma jogada automaticamente. Quando há capturas disponíveis, a IA prioriza esse tipo de movimento; caso contrário, seleciona um movimento válido aleatório.

## Melhorias futuras

- Implementar xeque
- Implementar xeque-mate
- Implementar promoção de peão
- Implementar roque
- Implementar en passant
- Melhorar a IA com heurísticas
- Adicionar nomes personalizados para os jogadores
- Melhorar a interface visual
- Reiniciar a partida ao trocar o modo de jogo
- Destacar visualmente a peça selecionada e movimentos possíveis

## Observações

O pacote base do projeto é:

```java
package com.TMZ.Xadrez;
```

Se houver alguma referência incorreta com espaço extra, como `com.TMZ.Xadrez `, ela deve ser corrigida para evitar problemas de compilação e organização do código.

## Autor

Desenvolvido por Thomaz Marcos.