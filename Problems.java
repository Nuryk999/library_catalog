import java.util.*;
class Problems {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();
        int l = scan.nextInt();
        int r = scan.nextInt();
        int x = scan.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = scan.nextInt();
        }
        int ans = 0;
        for (int i = 0; i < (1 << n); i++) {
            int sum = 0, min = Integer.MAX_VALUE, max = Integer.MIN_VALUE;
            for (int j = 0; j < n; j++) {
                if ((i & (1 << j)) != 0) {
                    min = Math.min(min, arr[j]);
                    max = Math.max(max, arr[j]);
                    sum += arr[j];
                }
            }
            if (sum >= l && sum <= r && max - min >= x) ans++;
        }
        System.out.println(ans);
    }
}