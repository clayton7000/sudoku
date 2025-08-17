 // ===== Jogo =====

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Random;

public class SudokuGame {
        private final Random rnd = new Random();
        private int[][] solution = new int[9][9];
        private int[][] puzzle = new int[9][9];
        private boolean[][] fixed = new boolean[9][9];

        public void newGame() {
            solution = generateSolvedGrid();
            for (int r = 0; r < 9; r++) System.arraycopy(solution[r], 0, puzzle[r], 0, 9);
            digHoles(50); // sempre dificuldade média
            for (int r = 0; r < 9; r++)
                for (int c = 0; c < 9; c++)
                    fixed[r][c] = puzzle[r][c] != 0;
        }

        public void set(int row1, int col1, int val) {
            int r = row1 - 1, c = col1 - 1;
            if (fixed[r][c]) throw new IllegalArgumentException("Essa célula é fixa.");
            puzzle[r][c] = val;
        }

        public void clear(int row1, int col1) {
            int r = row1 - 1, c = col1 - 1;
            if (fixed[r][c]) throw new IllegalArgumentException("Essa célula é fixa.");
            puzzle[r][c] = 0;
        }

        public Optional<int[]> hint() {
            List<int[]> empties = new ArrayList<>();
            for (int r = 0; r < 9; r++)
                for (int c = 0; c < 9; c++)
                    if (puzzle[r][c] == 0) empties.add(new int[]{r, c});
            if (empties.isEmpty()) return Optional.empty();
            int[] pos = empties.get(rnd.nextInt(empties.size()));
            return Optional.of(new int[]{pos[0], pos[1], solution[pos[0]][pos[1]]});
        }

        public boolean isComplete() {
            for (int r = 0; r < 9; r++)
                for (int c = 0; c < 9; c++)
                    if (puzzle[r][c] != solution[r][c]) return false;
            return true;
        }

        public void revealSolution() {
            for (int r = 0; r < 9; r++) System.arraycopy(solution[r], 0, puzzle[r], 0, 9);
            for (int r = 0; r < 9; r++) Arrays.fill(fixed[r], true);
        }

        public void printBoard() { printBoard(false); }
        public void printBoard(boolean showAllFixed) {
            String sep = "+-------+-------+-------+";
            for (int r = 0; r < 9; r++) {
                if (r % 3 == 0) System.out.println(sep);
                for (int c = 0; c < 9; c++) {
                    if (c % 3 == 0) System.out.print("| ");
                    int v = puzzle[r][c];
                    System.out.print((v == 0 ? "." : v) + " ");
                }
                System.out.println("|");
            }
            System.out.println(sep);
        }

        public CheckResult checkProgress() {
            List<String> msgs = new ArrayList<>();
            boolean conflict = false;
            for (int r = 0; r < 9; r++) {
                boolean[] seen = new boolean[10];
                for (int c = 0; c < 9; c++) {
                    int v = puzzle[r][c];
                    if (v != 0) {
                        if (seen[v]) { msgs.add("Duplicata na linha " + (r+1)); conflict = true; }
                        seen[v] = true;
                    }
                }
            }
            return new CheckResult(conflict, msgs);
        }
        static class CheckResult {
            boolean conflict;
            List<String> messages;
            CheckResult(boolean c, List<String> m) { conflict = c; messages = m; }
        }

        private int[][] generateSolvedGrid() {
            int[][] g = new int[9][9];
            backtrackFill(g, 0, 0);
            return g;
        }
        private boolean backtrackFill(int[][] g, int r, int c) {
            if (r == 9) return true;
            int nr = (c == 8 ? r + 1 : r), nc = (c + 1) % 9;
            List<Integer> nums = new ArrayList<>();
            for (int v = 1; v <= 9; v++) nums.add(v);
            Collections.shuffle(nums, rnd);
            for (int v : nums) {
                if (isValid(g, r, c, v)) {
                    g[r][c] = v;
                    if (backtrackFill(g, nr, nc)) return true;
                    g[r][c] = 0;
                }
            }
            return false;
        }
        private boolean isValid(int[][] g, int r, int c, int v) {
            for (int i = 0; i < 9; i++)
                if (g[r][i] == v || g[i][c] == v) return false;
            int br = (r/3)*3, bc = (c/3)*3;
            for (int i = 0; i < 3; i++)
                for (int j = 0; j < 3; j++)
                    if (g[br+i][bc+j] == v) return false;
            return true;
        }
        private void digHoles(int holes) {
            List<int[]> cells = new ArrayList<>();
            for (int r = 0; r < 9; r++)
                for (int c = 0; c < 9; c++)
                    cells.add(new int[]{r,c});
            Collections.shuffle(cells, rnd);
            for (int i = 0; i < holes && i < cells.size(); i++) {
                int[] cell = cells.get(i);
                puzzle[cell[0]][cell[1]] = 0;
            }
        }
    }