import java.util.Scanner;

public class FractalGenerator {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int m = Integer.parseInt(sc.nextLine().trim());

        for (int t = 0; t < m; t++) {
            int n = Integer.parseInt(sc.nextLine().trim());
            char[][] base = new char[3][3];

            for (int i = 0; i < 3; i++) {
                String line = sc.nextLine();
                line = padRight(line, 3); // ensure at least 3 characters
                for (int j = 0; j < 3; j++) {
                    base[i][j] = line.charAt(j);
                }
            }

            char[][] fractal = generateFractal(base, n);

            for (char[] row : fractal) {
                System.out.println(rtrim(new String(row))); // remove trailing spaces
            }

            System.out.println(); // blank line after each fractal
        }

        sc.close();
    }

    // Pads string with spaces to the right until it reaches 'length'
    private static String padRight(String s, int length) {
        while (s.length() < length) {
            s += " ";
        }
        return s;
    }

    // Trims trailing spaces
    private static String rtrim(String s) {
        int end = s.length();
        while (end > 0 && s.charAt(end - 1) == ' ') {
            end--;
        }
        return s.substring(0, end);
    }

    // Recursive fractal generation
    private static char[][] generateFractal(char[][] base, int level) {
        if (level == 2) {
            char[][] copy = new char[3][3];
            for (int i = 0; i < 3; i++) {
                System.arraycopy(base[i], 0, copy[i], 0, 3);
            }
            return copy;
        }

        char[][] smaller = generateFractal(base, level - 1);
        int size = smaller.length;
        int newSize = size * 3;
        char[][] result = new char[newSize][newSize];

        for (int i = 0; i < 3; i++) { // rows of base
            for (int j = 0; j < 3; j++) { // cols of base
                for (int r = 0; r < size; r++) {
                    for (int c = 0; c < size; c++) {
                        int rowIndex = i * size + r;
                        int colIndex = j * size + c;
                        result[rowIndex][colIndex] = (base[i][j] == ' ') ? ' ' : smaller[r][c];
                    }
                }
            }
        }

        return result;
    }
}
