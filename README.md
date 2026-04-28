# Xadrez em Java

Projeto de xadrez desenvolvido em Java com interface gráfica em Swing, com movimentação de peças, validação de jogadas, controle de turno, capturas e uma IA simples para jogadas automáticas.

## Funcionalidades

- Tabuleiro gráfico com Java Swing
- Movimentação de peças
- Validação de jogadas
- Controle de turno entre brancas e pretas
- Captura de peças
- Exibição do status da partida
- Estrutura para nomes dos jogadores
- IA simples com escolha de movimentos válidos

## Estrutura do projeto

```text
src/main/java/com/TMZ/Xadrez
├── controller
│   └── GameController.java
├── model
│   ├── Board.java
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
git clone <URL_DO_REPOSITORIO>
```

2. Acesse a pasta do projeto:
```bash
cd nome-do-projeto
```

3. Compile o projeto:
```bash
mvn clean install
```

4. Execute a aplicação:
```bash
mvn spring-boot:run
```

> Se o projeto não estiver usando Spring Boot de verdade e for apenas Java Swing, execute a classe principal diretamente pela IDE.

## Como jogar

1. Clique em uma peça do tabuleiro.
2. Clique na casa de destino.
3. O sistema valida se o movimento é permitido.
4. Se for válido, a jogada é realizada e o turno é alterado.

## IA do jogo

A classe `RegrasIA` gera todos os movimentos válidos para a cor atual, separa os movimentos de captura e escolhe um deles.
Se houver capturas disponíveis, a IA prioriza capturas.
Caso contrário, ela escolhe um movimento válido aleatório.

## Melhorias futuras

- Implementar xeque
- Implementar xeque-mate
- Implementar promoção de peão
- Implementar roque
- Implementar en passant
- Melhorar a IA com heurísticas
- Adicionar seleção de nomes dos jogadores
- Melhorar a interface visual

## Observações

O pacote correto do projeto é:

```java
package com.TMZ.Xadrez;
```

Se houver alguma referência com espaço extra, como `com.TMZ.Xadrez `, ela deve ser corrigida.

## Autor

Desenvolvido por TMZ.
