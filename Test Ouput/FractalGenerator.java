import java.util.*;

public class FractalGenerator {

    // Recursive function to build fractal pattern
    private static char[][] buildFractal(char[][] base, int level) {
        if (level == 2) return base;

        int size = base.length;
        int newSize = size * 3;
        char[][] next = new char[newSize][newSize];

        // Fill with spaces
        for (char[] row : next)
            Arrays.fill(row, ' ');

        // Place smaller fractals in the 3x3 pattern
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (base[i][j] != ' ') {
                    char[][] sub = buildFractal(base, level - 1);
                    for (int r = 0; r < sub.length; r++) {
                        for (int c = 0; c < sub.length; c++) {
                            next[i * size + r][j * size + c] = sub[r][c];
                        }
                    }
                }
            }
        }

        return next;
    }

    private static void printFractal(char[][] fractal) {
        for (char[] row : fractal) {
            String line = new String(row).stripTrailing();
            System.out.println(line);
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int groups = Integer.parseInt(sc.nextLine().trim());

        for (int g = 0; g < groups; g++) {
            int n = Integer.parseInt(sc.nextLine().trim());
            char[][] base = new char[3][3];

            for (int i = 0; i < 3; i++) {
                String line = sc.nextLine();
                for (int j = 0; j < 3; j++) {
                    base[i][j] = (j < line.length()) ? line.charAt(j) : ' ';
                }
            }

            char[][] result = buildFractal(base, n);
            printFractal(result);
            System.out.println(); // Blank line after each fractal
        }

        sc.close();
    }
}
