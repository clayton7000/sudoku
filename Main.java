import java.util.*;

public class Main {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.println("=== Sudoku Java (console) ===");

        SudokuGame game = new SudokuGame();
        game.newGame(); // sempre dificuldade média

        System.out.println("\nComandos: set L C V | clear L C | hint | check | print | solve | quit");
        game.printBoard();

        while (true) {
            System.out.print("\n> ");
            String line = in.nextLine().trim();
            if (line.isEmpty()) continue;
            String[] parts = line.split("\\s+");
            String cmd = parts[0].toLowerCase(Locale.ROOT);

            try {
                switch (cmd) {
                    case "set":
                        int r = Integer.parseInt(parts[1]);
                        int c = Integer.parseInt(parts[2]);
                        int v = Integer.parseInt(parts[3]);
                        game.set(r, c, v);
                        game.printBoard();
                        if (game.isComplete()) {
                            System.out.println("Parabéns! Você completou o Sudoku!");
                            return;
                        }
                        break;

                    case "clear":
                        r = Integer.parseInt(parts[1]);
                        c = Integer.parseInt(parts[2]);
                        game.clear(r, c);
                        game.printBoard();
                        break;

                    case "hint":
                        Optional<int[]> hint = game.hint();
                        if (hint.isPresent()) {
                            int[] h = hint.get();
                            System.out.println("Dica: linha " + (h[0] + 1) + ", coluna " + (h[1] + 1) + " deve ser " + h[2]);
                        } else {
                            System.out.println("Sem dicas disponíveis.");
                        }
                        game.printBoard();
                        break;

                    case "check":
                        SudokuGame.CheckResult cr = game.checkProgress();
                        if (cr.conflict) {
                            System.out.println("Há conflitos:");
                            for (String m : cr.messages) {
                                System.out.println(m);
                            }
                        } else {
                            System.out.println("Até agora está tudo válido.");
                        }
                        break;

                    case "print":
                        game.printBoard();
                        break;

                    case "solve":
                        System.out.println("Solução:");
                        game.revealSolution();
                        game.printBoard(true);
                        return;

                    case "quit":
                        System.out.println("Até mais!");
                        return;

                    default:
                        System.out.println("Comando desconhecido.");
                        break;
                }
            } catch (Exception e) {
                System.out.println("Erro: " + e.getMessage());
            }
        }
    }
}
