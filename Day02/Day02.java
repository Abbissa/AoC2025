import java.util.Scanner;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.IOException;
import java.math.BigInteger;
import java.net.MalformedURLException;

public class Day02 {

    public static void main(String[] args) throws MalformedURLException, IOException {
        // read file Input.txt from the same folder
        // parte1();
        parte2();
    }

    private static void parte2() throws IOException, MalformedURLException {
        try (Scanner sc = new Scanner(
                new BufferedInputStream(new File("Day02/Input.txt").toURI().toURL().openStream()))) {
            String productsIDs = sc.nextLine();
            String[] products = productsIDs.split(",");
            BigInteger res = new BigInteger("0");
            for (int i = 0; i < products.length; i++) {
                long id1 = Long.parseLong(products[i].split("-")[0]);
                long id2 = Long.parseLong(products[i].split("-")[1]);
                while (id1 <= id2) {
                    String idStr = String.valueOf(id1);
                    int banda = 1;
                    while (banda <= idStr.length() / 2) {
                        String id = idStr.substring(0, banda);
                        String resStr = "";
                        while (resStr.length() < idStr.length()) {
                            resStr += id;
                        }
                        if (resStr.equals(idStr)) {
                            res = res.add(BigInteger.valueOf(id1));
                            break;
                        }
                        banda++;

                    }
                    id1++;

                }

            }
            System.out.println(res);

        }
    }

    // 38437576658
    // 38437576658
    // 38437576669
    // 38437576669
    private static void parte1() throws IOException, MalformedURLException {
        try (Scanner sc = new Scanner(
                new BufferedInputStream(new File("Day02/Input.txt").toURI().toURL().openStream()))) {
            String productsIDs = sc.nextLine();
            String[] products = productsIDs.split(",");
            BigInteger res = new BigInteger("0");
            for (int i = 0; i < products.length; i++) {
                long id1 = Long.parseLong(products[i].split("-")[0]);
                long id2 = Long.parseLong(products[i].split("-")[1]);

                while (id1 <= id2) {
                    String idStr = String.valueOf(id1);
                    // avanzar hasta la siguiente potencia de 10

                    // continue;
                    if (idStr.substring(0, idStr.length() / 2)
                            .equals(idStr.substring(idStr.length() / 2))) {
                        // System.out.println(idStr.substring(0, idStr.length() / 2));
                        // System.out.println(idStr.substring(idStr.length() / 2));
                        System.out.println(id1);
                        res = res.add(BigInteger.valueOf(id1));
                        id1 += Math.pow(10, idStr.length() / 2);
                        // continue;
                    } else {
                    }
                    id1++;

                }
            }
            System.out.println(res);

        }
    }

}
