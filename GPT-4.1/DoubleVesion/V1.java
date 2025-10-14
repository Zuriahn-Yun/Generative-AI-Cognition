import java.util.*;

public class V1 {
    static char[][] base; // level-2 pattern (3x3)
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int m = Integer.parseInt(sc.nextLine().trim());
        
        for (int caseNum = 0; caseNum < m; caseNum++) {
            int n = Integer.parseInt(sc.nextLine().trim());
            base = new char[3][3];
            
            // Read level-2 pattern (3 lines)
            for (int i = 0; i < 3; i++) {
                String line = sc.nextLine();
                // Ensure we handle spaces properly — pad to length 3
                while (line.length() < 3) line += " ";
                base[i] = line.substring(0, 3).toCharArray();
            }
            
            // Generate fractal at level n
            char[][] fractal = generateFractal(n);
            
            // Print output (trim trailing spaces per line)
            for (char[] row : fractal) {
                String s = new String(row).replaceFirst("\\s+$", "");
                System.out.println(s);
            }
            
            // Blank line between fractals
            System.out.println();
        }
    }
    
    // Recursively build fractal
    static char[][] generateFractal(int level) {
        if (level == 1) {
            return new char[][]{{'o'}}; // Level-1 base
        }
        if (level == 2) {
            // Return deep copy of base
            char[][] copy = new char[3][3];
            for (int i = 0; i < 3; i++)
                System.arraycopy(base[i], 0, copy[i], 0, 3);
            return copy;
        }
        
        char[][] smaller = generateFractal(level - 1);
        int smallSize = smaller.length;
        int newSize = 3 * smallSize;
        char[][] result = new char[newSize][newSize];
        
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                for (int x = 0; x < smallSize; x++) {
                    for (int y = 0; y < smallSize; y++) {
                        if (base[i][j] != ' ') {
                            result[i * smallSize + x][j * smallSize + y] = smaller[x][y];
                        } else {
                            result[i * smallSize + x][j * smallSize + y] = ' ';
                        }
                    }
                }
            }
        }
        return result;
    }
}
