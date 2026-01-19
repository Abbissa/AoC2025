import java.util.Scanner;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;

public class Day01 {

    public static void main(String[] args) throws MalformedURLException, IOException {
        // read file Input.txt from the same folder
        parte1();
        parte2();
    }

    private static void parte2() throws IOException, MalformedURLException {
        try (Scanner scanner = new Scanner(
                new BufferedInputStream(new File("Day01/Input.txt").toURI().toURL().openStream()))) {
            int dialPos = 50;
            int ceros = 0;
            while (scanner.hasNextLine()) {
                int dialPosBefore = dialPos;
                String line = scanner.nextLine();
                int move = Integer.parseInt(line.substring(1));
                if (line.charAt(0) == 'R') {
                    dialPos += move;
                } else {
                    dialPos -= move;
                }

                // calculate how many times we passed through 0
                if (line.charAt(0) == 'R') {
                    for (int i = 1; i <= move; i++) {
                        int currentPos = (dialPosBefore + i) % 100;
                        if (currentPos == 0) {
                            ceros++;
                        }
                    }
                } else {
                    for (int i = 1; i <= move; i++) {
                        int currentPos = (dialPosBefore - i);
                        if (currentPos < 0) {
                            currentPos += 100;
                        }
                        currentPos = currentPos % 100;
                        if (currentPos == 0) {
                            ceros++;
                        }
                    }
                }
                if (dialPos < 0) {
                    dialPos += 100;
                }
                dialPos = dialPos % 100;
            }
            System.out.println(ceros);

        }
    }

    private static void parte1() throws IOException, MalformedURLException {
        try (Scanner scanner = new Scanner(
                new BufferedInputStream(new File("Day01/Input.txt").toURI().toURL().openStream()))) {
            int dialPos = 50;
            int ceros = 0;
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                if (line.charAt(0) == 'R') {
                    dialPos += Integer.parseInt(line.substring(1));
                } else {
                    dialPos -= Integer.parseInt(line.substring(1));
                }
                if (dialPos < 0) {
                    dialPos += 100;
                }
                dialPos = dialPos % 100;
                if (dialPos == 0) {
                    ceros++;
                }
            }
            System.out.println(ceros);

        }
    }

}
