# Sudoku em Java (Console)

Este é um jogo de **Sudoku** desenvolvido em **Java**, jogado diretamente no console/terminal.  
O programa gera automaticamente um tabuleiro e permite que o jogador interaja através de comandos de texto.

---

## 🎮 Funcionalidades
- Geração automática de tabuleiros de Sudoku válidos.
- Comandos para jogar:
  - `set L C V` → coloca o valor **V** (1-9) na linha **L** e coluna **C** (valores de 1 a 9).
  - `clear L C` → limpa a célula indicada (se não for fixa).
  - `hint` → mostra uma dica (um número correto para uma célula vazia).
  - `check` → verifica se o preenchimento atual é válido (sem duplicatas).
  - `print` → imprime o tabuleiro atual.
  - `solve` → revela a solução completa do Sudoku.
  - `quit` → sai do jogo.

---

## 📷 Exemplo de jogo

=== Sudoku Java (console) ===

Comandos: set L C V | clear L C | hint | check | print | solve | quit

+-------+-------+-------+
| . . 3 | . 2 . | 6 . . |
| 9 . . | 3 . 5 | . . 1 |
| . . 1 | 8 . 6 | 4 . . |
+-------+-------+-------+
| . . 8 | 1 . 2 | 9 . . |
| 7 . . | . . . | . . 8 |
| . . 6 | 7 . 8 | 2 . . |
+-------+-------+-------+
| . . 2 | 6 . 9 | 5 . . |
| 8 . . | 2 . 3 | . . 9 |
| . . 5 | . 1 . | 3 . . |
+-------+-------+-------+

> set 1 1 4