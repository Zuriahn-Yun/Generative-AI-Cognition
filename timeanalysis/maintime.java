package timeanalysis;

import java.io.File;
import java.util.Scanner;
import testoutput.Main;
// Fractal Generation from Correct Implementation Code

public class maintime {
    public static void main(String[] args) {
        try {
            long startTime = System.nanoTime();
            File input = new File("timeanalysis\\input.txt"); // Different
            Scanner scanner = new Scanner(input); // Different

            int m = Integer.parseInt(scanner.nextLine());
            while (m-- > 0) {
                int n = Integer.parseInt(scanner.nextLine());
                char[][] shape = new char[3][3];
                for (int i = 0; i < 3; i++) {
                    shape[i] = scanner.nextLine().toCharArray();
                }
                for (int r = 2; r < 8; r++) {
                    Fractal fractal = new Fractal(n, 'o', shape);

                    fractal.printFractal();
                }
            }
            long endtime = System.nanoTime();
            
            long duration = endtime - startTime;
            System.out.println("Correct Implementation");
            System.out.println("Execution time: " + duration + " ns");
            System.out.println("Execution time: " + (duration / 1_000_000.0) + " ms");
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}

class Fractal {
    private int n;
    private char symbol;
    private char[][] shape;
    private char[][] fractal;

    public Fractal(int n, char symbol, char[][] shape) {
        this.n = n;
        this.symbol = symbol;
        this.shape = shape;

        init();
        fill(n, 0, 0);
    }

    public void printFractal() {
        for (int i = 0; i < fractal.length; i++) {
            int stop = -1;
            for (int j = 0; j < fractal[i].length; j++) {
                if (fractal[i][j] == 'o') {
                    stop = j;
                }
            }
            for (int j = 0; j <= stop; j++) {
                System.out.print(fractal[i][j]);
            }
            System.out.println();
        }

    }

    private void init() {
        int size = (int) Math.pow(3, n - 1);
        fractal = new char[size][size];
        for (int i = 0; i < fractal.length; i++) {
            for (int j = 0; j < fractal[i].length; j++) {
                fractal[i][j] = ' ';
            }
        }
    }

    private void fill(int n, int row, int col) {
        if (n == 1) {
            fractal[row][col] = symbol;
            return;
        }
        int size = (int) Math.pow(3, n - 1);
        for (int i = 0; i < shape.length; i++) {
            for (int j = 0; j < shape[i].length; j++) {
                if (shape[i][j] == symbol) {
                    int innerSize = size / 3;
                    int innerRow = row + innerSize * i;
                    int innerCol = col + innerSize * j;
                    fill(n - 1, innerRow, innerCol);
                }
            }
        }
    }
}
