import java.util.*;
public class Code {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        long n = scan.nextLong();
        long sum = (1 + n) * n / 2;
        long power = (long)Math.ceil(Math.log(n)) + 1;
        long two_sum = (long)Math.pow(2, power);
        long ans = sum - 2 * two_sum + 2;
        System.out.println(power);
        System.out.println(ans);
    }
}

