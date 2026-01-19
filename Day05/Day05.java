import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.IOException;
import java.math.BigInteger;
import java.net.MalformedURLException;

public class Day05 {
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
                new BufferedInputStream(new File("Day05/Input.txt").toURI().toURL().openStream()))) {

            result = 0;
            String line = sc.nextLine();
            List<Pair> pairs = new ArrayList<>();
            while (line != null && !line.isEmpty()) {
                long id1 = Long.parseLong(line.split("-")[0]);
                long id2 = Long.parseLong(line.split("-")[1]);
                boolean merged = false;
                for (int i = 0; i < pairs.size(); i++) {
                    if (id2 <= pairs.get(i).second && id1 >= pairs.get(i).first) {
                        // overlap
                        merged = true;
                        break;
                    }
                    if (id2 > pairs.get(i).first && id1 <= pairs.get(i).first) {
                        pairs.get(i).first = id1;
                        pairs.get(i).second = Math.max(pairs.get(i).second, id2);
                        merged = true;
                        break;
                    }
                    if (id1 < pairs.get(i).second && id2 >= pairs.get(i).second) {
                        pairs.get(i).second = id2;
                        pairs.get(i).first = Math.min(pairs.get(i).first, id1);
                        merged = true;
                        break;
                    }
                }
                if (!merged)
                    pairs.add(new Pair(id1, id2));
                line = sc.nextLine();

                for (int i = 0; i < pairs.size(); i++) {
                    for (int j = i + 1; j < pairs.size(); j++) {
                        if (pairs.get(i).second >= pairs.get(j).first && pairs.get(i).first <= pairs.get(j).second) {
                            // overlap
                            pairs.get(i).first = Math.min(pairs.get(i).first, pairs.get(j).first);
                            pairs.get(i).second = Math.max(pairs.get(i).second, pairs.get(j).second);
                            pairs.remove(j);
                            j--;
                        }
                    }
                }
            }
            // order pairs by first
            pairs.sort((a, b) -> Long.compare(a.first, b.first));
            for (Pair p : pairs) {
                long pRes = p.second - p.first + 1;
                result += pRes;
                // String numero = String.format("%016d", p.first);
                // String numero2 = String.format("%016d", p.second);
                // String resultado = String.format("%016d", pRes);
                // System.out.println("Range:\n\t" + numero2 + "\n\t" + numero + "\n\t" +
                // resultado);
                System.out.println("Range: " + p.first + "-" + p.second + " Size: " + pRes);
                // System.out.println(p.second + "-" + p.first);
                // String numero con 12 digits
            }
            System.out.println(result);
            System.out.println(Long.MAX_VALUE);

        }
    }

    // 98
    // 81
    // 78
    // 91
    // 348
    private static void parte1() throws IOException, MalformedURLException {

        try (Scanner sc = new Scanner(
                new BufferedInputStream(new File("Day05/Input.txt").toURI().toURL().openStream()))) {
            String line = sc.nextLine();
            List<Pair> pairs = new ArrayList<>();
            while (line != null && !line.isEmpty()) {
                long id1 = Long.parseLong(line.split("-")[0]);
                long id2 = Long.parseLong(line.split("-")[1]);
                pairs.add(new Pair(id1, id2));
                line = sc.nextLine();
            }
            result = 0;
            while (sc.hasNext()) {
                line = sc.nextLine();
                long id = Long.parseLong(line);
                for (Pair p : pairs) {
                    if (id >= p.first && id <= p.second) {
                        result++;
                        break;
                    }
                }
            }
            System.out.println(result);

        }
    }

}

class Pair {
    public long first;
    public long second;

    public Pair(long first, long second) {
        this.first = first;
        this.second = second;
    }

    public long getFirst() {
        return first;
    }

    public void setFirst(long first) {
        this.first = first;
    }

    public long getSecond() {
        return second;
    }

    public void setSecond(long second) {
        this.second = second;
    }

}
