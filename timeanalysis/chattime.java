package timeanalysis;
import java.util.*;
import java.io.File;
import java.io.FileNotFoundException;

// Fractal Generation from ChatGPT Code
public class chattime {
    
    public static void main(String[] args) {
        try {
            long startTime = System.nanoTime();

            File input = new File("timeanalysis\\input.txt"); // Different 
            Scanner sc = new Scanner(input); // Different 
            int m = Integer.parseInt(sc.nextLine().trim());

            for (int t = 0; t < m; t++) {
                int n = Integer.parseInt(sc.nextLine().trim()); // No Longer used
                char[][] base = new char[3][3];

                for (int i = 0; i < 3; i++) {
                    String line = sc.nextLine();
                    line = padRight(line, 3); // ensure at least 3 characters
                    for (int j = 0; j < 3; j++) {
                        base[i][j] = line.charAt(j);
                    }
                }
                for (int r = 2; r < 8; r++){ // Different

                char[][] fractal = generateFractal(base, r);

                for (char[] row : fractal) {
                    System.out.println(rtrim(new String(row))); // remove trailing spaces
                }

                System.out.println(); // blank line after each fractal

                } // Different
            }

            sc.close();

            long endtime = System.nanoTime();

            long duration = endtime - startTime;
            System.out.println("Correct Implementation");
            System.out.println("Execution time: " + duration + " ns");
            System.out.println("Execution time: " + (duration / 1_000_000.0) + " ms");


        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + e.getMessage());
        }
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