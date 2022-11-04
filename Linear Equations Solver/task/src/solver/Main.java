package solver;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.IntStream;


public class Main {
    public static void main(String[] args) throws IOException {
        var qtn =
                read(Paths.get(
                System.getProperty("user.dir"),
                args[1]));
        decide(qtn);
        write(qtn, Paths.get(
                System.getProperty("user.dir"),
                args[3]));
    }
    private static double[][] read(Path file)
                                         throws IOException {
        return Files
                .readAllLines(file)
                .stream()
                .skip(1)
                .map(str -> Arrays
                        .stream(str.split("\\s+"))
                        .mapToDouble(Double::parseDouble)
                        .toArray())
                .toArray(double[][]::new);
    }

    private static void write(double[][] solutions,
                                    Path file)
                                     throws IOException {
        Files
                .writeString(file, Arrays.stream(solutions)
                .map(rslt -> String.format(
                        "%f", rslt[solutions.length]))
                .collect(Collectors.joining("\n")));
    }

    private static void decide(double[][] qtns) {
        var rw = new AtomicInteger(0);
        IntStream
                .range(0, qtns[0].length)
                .forEach(cl -> {
            if (cl != qtns[0].length - 1) {
                IntStream
                        .range(0, qtns.length)
                        .forEach(r -> {

                    if (rw.get() == r) {
                        double sc = 1 / qtns[r][cl];
                        qtns[r] = mltplBySclr(qtns[r], sc);

                    } else {
                        double sc = -(qtns[r][cl] /
                                     qtns[rw.get()][cl]);
                        qtns[r] = addRws(qtns[r],
                                  mltplBySclr(qtns[rw.get()],
                                        sc));
                    }
                });
                rw.getAndIncrement();
            }
        });
    }

    private static double[] mltplBySclr(double[] rw,
                                             double sc) {
        return Arrays
                .stream(rw)
                .map(v -> v * sc)
                .toArray();
    }

    private static double[] addRws(double[] rw1,
                                    double[] rw2) {
        return IntStream
                .range(0, rw1.length)
                .mapToDouble(i -> rw1[i] + rw2[i])
                .toArray();
    }
}