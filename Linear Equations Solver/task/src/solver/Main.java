package solver;

public class Main {

    public static void main(String[] args) {
        var sc = new java.util.Scanner(System.in);
        var nums = new double[6];
        for (int i = 0; i < nums.length; i++) {
            nums[i] = sc.nextDouble();
        }
        double y = (nums[0] * nums[5] - nums[2] * nums[3]) /
                (nums[0] * nums[4] - nums[1] * nums[3]);
        double x = (nums[2] - nums[1] * y) / nums[0];
        System.out.println(x + " " + y);
    }
}