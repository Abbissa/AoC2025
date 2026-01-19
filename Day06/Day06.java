import java.util.ArrayList;
import java.util.Scanner;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.IOException;
import java.math.BigInteger;
import java.net.MalformedURLException;

public class Day06 {
    private static int mapSize = 138;
    private static long result;

    public static void main(String[] args) throws MalformedURLException, IOException {
        // read file Input.txt from the same folder
        parte1();
        parte2();
    }

    // 3121910778619
    // 3150135875
    private static void parte2() throws IOException, MalformedURLException {

        try (Scanner sc = new Scanner(
                new BufferedInputStream(new File("Day06/Input.txt").toURI().toURL().openStream()))) {

        }
    }

    // 98
    // 81
    // 78
    // 91
    // 348
    private static void parte1() throws IOException, MalformedURLException {
        result = 0;
        try (Scanner sc = new Scanner(
                new BufferedInputStream(new File("Day06/Input.txt").toURI().toURL().openStream()))) {
            ArrayList<ArrayList<Long>> listas = new ArrayList<>();
            for (int i = 0; i < 4; i++) {
                String line = sc.nextLine();
                listas.add(new ArrayList<>());
                for (String numero : line.split(" ")) {
                    if (!numero.isEmpty())
                        listas.get(i).add(Long.parseLong(numero));

                }
            }

            String line = sc.nextLine();
            int i = 0;
            for (String c : line.split(" ")) {
                if (c.isEmpty())
                    continue;
                if (c.equals("+")) {

                    for (int j = 0; j < listas.size(); j++) {
                        result += listas.get(i).get(j);
                    }
                } else if (c.equals("*")) {
                    long prod = 1;
                    for (int j = 0; j < listas.size(); j++) {
                        prod *= listas.get(i).get(j);
                    }
                    result += prod;
                }

            }
            System.out.println(result);
        }
    }

}
