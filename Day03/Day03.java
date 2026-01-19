import java.util.Scanner;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.IOException;
import java.math.BigInteger;
import java.net.MalformedURLException;

public class Day03 {

    public static void main(String[] args) throws MalformedURLException, IOException {
        // read file Input.txt from the same folder
        // parte1();
        parte2();
    }

    // 3121910778619
    // 3150135875
    private static void parte2() throws IOException, MalformedURLException {
        try (Scanner sc = new Scanner(
                new BufferedInputStream(new File("Day03/Input.txt").toURI().toURL().openStream()))) {
            BigInteger res = new BigInteger("0");
            System.out.println("SSS");
            int n = 0;
            while (sc.hasNextLine()) {
                String batteryRack = sc.nextLine();
                long[] lista = new long[12];
                for (int i = 0; i < batteryRack.toCharArray().length; i++) {
                    int battery = Integer.parseInt(batteryRack.charAt(i) + "");
                    assignNewNumber(lista, battery);

                }
                System.out.println(n + ": " + lista[11] + "\t" + batteryRack);
                res = res.add(BigInteger.valueOf(lista[11]));
                n++;
            }
            System.out.println(res);
        }
    }

    private static void assignNewNumber(long[] lista, long newBatt) {
        for (int i = lista.length - 1; i >= 0; i--) {
            if (i == 0) {
                if (newBatt > lista[0]) {
                    lista[0] = newBatt;
                }
                continue;
            }
            if (lista[i - 1] == 0)
                continue;
            long newNumber = lista[i - 1] * 10 + newBatt;
            if (newNumber > lista[i])
                lista[i] = newNumber;
        }

    }

    // 98
    // 81
    // 78
    // 91
    // 348
    private static void parte1() throws IOException, MalformedURLException {
        try (Scanner sc = new Scanner(
                new BufferedInputStream(new File("Day03/Input.txt").toURI().toURL().openStream()))) {
            int res = 0;
            System.out.println("SSS");
            int n = 0;
            while (sc.hasNextLine()) {
                String batteryRack = sc.nextLine();
                int maxBattery = 0;
                Integer firstNumber = 0;
                Integer secondNumber = 0;
                for (int i = 0; i < batteryRack.toCharArray().length; i++) {
                    int battery = Integer.parseInt(batteryRack.charAt(i) + "");
                    if (firstNumber == 0)
                        firstNumber = battery;
                    else if (secondNumber == 0) {
                        maxBattery = firstNumber * 10 + battery;
                        if (battery > firstNumber) {
                            firstNumber = battery;
                            secondNumber = 0;
                        } else {
                            secondNumber = battery;
                        }

                    } else if (battery > firstNumber) {
                        int newBattery = firstNumber * 10 + battery;
                        maxBattery = newBattery;
                        firstNumber = battery;
                        secondNumber = 0;
                    } else if (battery > secondNumber) {
                        int newBattery = firstNumber * 10 + battery;
                        maxBattery = newBattery;
                        secondNumber = battery;
                    }

                }
                System.out.println(n + ": " + maxBattery + "\t" + batteryRack);
                res += maxBattery;
                n++;
            }
            System.out.println(res);
        }
    }

}
