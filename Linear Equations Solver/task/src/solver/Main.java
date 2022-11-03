package solver;

public class Main {
    public static void main(String[] args) {
        var numbers = java.util.Arrays
                          .stream(new java.util
                                 .Scanner(System.in)
                                 .nextLine()
                                 .split(" "))
                          .mapToDouble(Double::
                                      parseDouble)
                          .toArray();
        System.out.println(numbers[1] / numbers[0]);
    }
}
