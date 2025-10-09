package org.example;

import java.util.*;

public class Main {
    static int size; // size of final fractal
    static char[][] base = new char[3][3]; // level-2 template

    static char[][] build(int level) {
        if (level == 2) {
            // return copy of base pattern
            char[][] res = new char[3][3];
            for (int i = 0; i < 3; i++)
                res[i] = Arrays.copyOf(base[i], 3);
            return res;
        }

        char[][] prev = build(level - 1);
        int prevSize = prev.length;
        int newSize = prevSize * 3;
        char[][] res = new char[newSize][newSize];

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                char c = base[i][j];
                for (int x = 0; x < prevSize; x++) {
                    for (int y = 0; y < prevSize; y++) {
                        if (c == ' ')
                            res[i * prevSize + x][j * prevSize + y] = ' ';
                        else
                            res[i * prevSize + x][j * prevSize + y] = prev[x][y];
                    }
                }
            }
        }
        return res;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int m = Integer.parseInt(sc.nextLine().trim());

        for (int t = 0; t < m; t++) {
            int n = Integer.parseInt(sc.nextLine().trim());
            for (int i = 0; i < 3; i++) {
                String line = sc.nextLine();
                // ensure 3 characters, fill missing with spaces if needed
                base[i] = String.format("%-3s", line).toCharArray();
            }

            char[][] fractal = build(n);
            for (char[] row : fractal) {
                // trim right spaces
                String line = new String(row).replaceAll("\\s+$", "");
                System.out.println(line);
            }

            if (t != m - 1) System.out.println(); // blank line between outputs
        }

        sc.close();
    }
}
