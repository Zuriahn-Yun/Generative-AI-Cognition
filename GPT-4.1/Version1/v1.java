import java.util.*;

public class FractalGenerator {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int m = Integer.parseInt(sc.nextLine()); // number of fractal groups

        for (int group = 0; group < m; group++) {
            int n = Integer.parseInt(sc.nextLine()); // level to generate

            char[][] level2 = new char[3][3];
            for (int i = 0; i < 3; i++) {
                String line = sc.nextLine();
                for (int j = 0; j < 3; j++) {
                    level2[i][j] = line.charAt(j);
                }
            }

            char[][] fractal = generateFractal(level2, n);

            printFractal(fractal);
            System.out.println(); // blank line after each fractal
        }
        sc.close();
    }

    // Recursive fractal generator
    private static char[][] generateFractal(char[][] level2, int n) {
        if (n == 2) {
            return level2;
        }

        char[][] prev = generateFractal(level2, n - 1);
        int sizePrev = prev.length;
        int size = sizePrev * 3;

        char[][] result = new char[size][size];

        for (int i = 0; i < sizePrev; i++) {
            for (int j = 0; j < sizePrev; j++) {
                char c = prev[i][j];
                for (int r = 0; r < 3; r++) {
                    for (int s = 0; s < 3; s++) {
                        result[i * 3 + r][j * 3 + s] = (c == ' ') ? ' ' : level2[r][s];
                    }
                }
            }
        }

        return result;
    }

    // Print the fractal without trailing spaces
    private static void printFractal(char[][] fractal) {
        for (char[] row : fractal) {
            int lastNonSpace = row.length - 1;
            while (lastNonSpace >= 0 && row[lastNonSpace] == ' ') lastNonSpace--;
            for (int i = 0; i <= lastNonSpace; i++) {
                System.out.print(row[i]);
            }
            System.out.println();
        }
    }
}
