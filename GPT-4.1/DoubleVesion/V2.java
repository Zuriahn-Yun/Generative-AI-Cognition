import java.io.*;
import java.util.*;

public class V2{
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in, "UTF-8"));
        // read first non-empty trimmed line for m
        Integer m = readNextInt(br);
        if (m == null) return;

        StringBuilder output = new StringBuilder();

        for (int sample = 0; sample < m; sample++) {
            Integer n = readNextInt(br); // level to print
            if (n == null) break;

            // read exactly 3 pattern lines; skip completely empty lines (""), but accept lines with spaces
            String[] base = new String[3];
            int got = 0;
            while (got < 3) {
                String line = br.readLine();
                if (line == null) break;
                // treat completely empty lines as separators (skip), but preserve lines with spaces
                if (line.length() == 0) continue;
                // normalize whitespace (convert any whitespace char to space) to avoid NBSP issues
                line = normalizeToSpaces(line);
                // pad/truncate to 3 chars
                if (line.length() < 3) line = line + " ".repeat(3 - line.length());
                else if (line.length() > 3) line = line.substring(0, 3);
                base[got++] = line;
            }
            if (got < 3) break;

            char[][] fractal = buildFractal(base, n);

            // print fractal rows with trailing spaces trimmed
            for (int i = 0; i < fractal.length; i++) {
                int lastNonSpace = -1;
                for (int j = fractal[i].length - 1; j >= 0; j--) {
                    if (fractal[i][j] != ' ') { lastNonSpace = j; break; }
                }
                if (lastNonSpace < 0) {
                    output.append("\n"); // whole line blank -> print empty line
                } else {
                    output.append(new String(fractal[i], 0, lastNonSpace + 1)).append("\n");
                }
            }
            // blank line after each fractal
            output.append("\n");
        }

        // Print all at once
        System.out.print(output.toString());
    }

    // normalize all whitespace (including NBSP) to regular ASCII space for consistent handling
    static String normalizeToSpaces(String s) {
        StringBuilder b = new StringBuilder(s.length());
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (Character.isWhitespace(c)) b.append(' ');
            else b.append(c);
        }
        return b.toString();
    }

    // Read next non-empty trimmed line and parse to integer; skip blank lines ("") or lines with only whitespace.
    static Integer readNextInt(BufferedReader br) throws IOException {
        String line;
        while ((line = br.readLine()) != null) {
            if (line.trim().length() == 0) continue;
            try {
                return Integer.parseInt(line.trim());
            } catch (NumberFormatException e) {
                // maybe line contains other stuff — attempt to extract first token
                String[] toks = line.trim().split("\\s+");
                if (toks.length > 0) {
                    try {
                        return Integer.parseInt(toks[0]);
                    } catch (NumberFormatException ex) {
                        // continue searching
                    }
                }
            }
        }
        return null;
    }

    // Build fractal at level n given 3x3 base (strings length 3). Base is level 2.
    static char[][] buildFractal(String[] base, int n) {
        // treat any non-space char in base as 'filled'
        char[][] template = new char[3][3];
        for (int i = 0; i < 3; i++) {
            String s = base[i];
            for (int j = 0; j < 3; j++) {
                char ch = s.charAt(j);
                template[i][j] = (ch == ' ') ? ' ' : ch; // keep actual char (not only 'o') but treat space as blank
            }
        }

        // level 2
        char[][] cur = new char[3][3];
        for (int i = 0; i < 3; i++) for (int j = 0; j < 3; j++) cur[i][j] = template[i][j];

        if (n == 2) return cur;

        // iterative expansion from level k to k+1 where each step multiplies size by 3.
        for (int level = 3; level <= n; level++) {
            int s = cur.length;
            int ns = s * 3;
            char[][] next = new char[ns][ns];
            // initialize with spaces
            for (int i = 0; i < ns; i++) Arrays.fill(next[i], ' ');

            // For each of the 3x3 positions in the template:
            // if template[ti][tj] != ' ' then copy the entire cur into the block (ti, tj).
            for (int ti = 0; ti < 3; ti++) {
                for (int tj = 0; tj < 3; tj++) {
                    if (template[ti][tj] != ' ') {
                        int baseRow = ti * s;
                        int baseCol = tj * s;
                        for (int r = 0; r < s; r++) {
                            System.arraycopy(cur[r], 0, next[baseRow + r], baseCol, s);
                        }
                    }
                    // else leave that block as spaces
                }
            }
            cur = next;
        }
        return cur;
    }
}
