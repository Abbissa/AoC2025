import java.util.ArrayList;
import java.util.Scanner;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.IOException;
import java.math.BigInteger;
import java.net.MalformedURLException;

public class Day04 {
    private static int mapSize = 138;
    private static int result;

    public static void main(String[] args) throws MalformedURLException, IOException {
        // read file Input.txt from the same folder
        parte1();
        parte2();
    }

    // 3121910778619
    // 3150135875
    private static void parte2() throws IOException, MalformedURLException {

        try (Scanner sc = new Scanner(
                new BufferedInputStream(new File("Day04/Input.txt").toURI().toURL().openStream()))) {
            result = 0;
            boolean[][] mapa = leer(mapSize, sc);
            // cls en console

            while (true) {
                int resultBefore = result;
                boolean[][] mapaCopy = cloneMapa(mapa);
                System.out.print("\033[H\033[2J");
                resolver(mapa, mapaCopy);
                System.out.flush();
                mapa = mapaCopy;
                if (result == resultBefore) {
                    break;

                }
            }
            System.out.println(result);
        }
    }

    // 98
    // 81
    // 78
    // 91
    // 348
    private static void parte1() throws IOException, MalformedURLException {

        try (Scanner sc = new Scanner(
                new BufferedInputStream(new File("Day04/Input.txt").toURI().toURL().openStream()))) {
            boolean[][] mapa = leer(mapSize, sc);
            result = 0;

            boolean[][] mapaCopy = cloneMapa(mapa);
            resolver(mapa, mapaCopy);
            System.out.println(result);

        }
    }

    private static void resolver(boolean[][] mapa, boolean[][] mapaCopy) {
        for (int i = 0; i < mapa.length; i++) {
            for (int j = 0; j < mapa[i].length; j++) {
                if (mapa[i][j]) {
                    int accesible = calculateAccessible(mapa, i, j);
                    result += accesible;
                    if (accesible == 1) {
                        mapaCopy[i][j] = false;
                        System.out.print('x');
                    } else
                        System.out.print('@');
                } else {
                    System.out.print('·');

                }
            }
            System.out.println();
        }
    }

    private static boolean[][] leer(int mapSize, Scanner sc) {
        boolean[][] mapa = new boolean[mapSize][mapSize];
        int row = 0;
        while (sc.hasNextLine()) {
            String rowLine = sc.nextLine();
            int col = 0;
            for (char cell : rowLine.toCharArray()) {
                if (cell == '@') {
                    mapa[row][col] = true;
                }
                col++;
            }
            row++;
        }
        return mapa;
    }

    private static boolean[][] cloneMapa(boolean[][] mapa) {
        boolean[][] mapaCopy = new boolean[mapa.length][mapa[0].length];
        for (int i = 0; i < mapa.length; i++) {
            mapaCopy[i] = mapa[i].clone();
        }
        return mapaCopy;
    }

    private static int calculateAccessible(boolean[][] mapa, int i, int j) {
        int[][] direcciones = {
                { 1, 1 }, { 0, 1 },
                { -1, 1 }, { -1, 0 },
                { -1, -1 }, { 0, -1 },
                { 1, -1 }, { 1, 0 } };
        int nRolls = 0;

        for (int k = 0; k < direcciones.length; k++) {

            int x = i + direcciones[k][0];
            int y = j + direcciones[k][1];
            if (x < 0 || x > mapa.length - 1)
                continue;
            if (y < 0 || y > mapa.length - 1)
                continue;
            if (mapa[x][y])
                nRolls++;

        }
        if (nRolls < 4)
            return 1;
        return 0;
    }

}
