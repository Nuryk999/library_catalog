import java.util.Scanner;

public class Combination {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        long ans = 1;
        int t = scan.nextInt();
        for (int i = 0; i < t; i++) {
            int n = scan.nextInt();
            int k = scan.nextInt();
            ans *= combination(n, k);
        }
        System.out.println(ans);
    }

    static long combination(int n, int k) {
        if (k < 0 || k > n) {
            return 0; // Invalid input
        }

        if (k > n - k) {
            k = n - k; // Choose the smaller k for efficiency
        }

        long result = 1;

        for (int i = 1; i <= k; i++) {
            result = result * (n - i + 1) / i;
        }

        return result;
    }
    static long combination2(int n, int k) {
        if (k == 0) {
            return 1;
        }

        long lim = Math.max(n - k, k);
        long tmp = Math.min(n - k, k);
        long denominator = 1, numerator = 1;

        for (int i = n; i > lim; i--) {
            if (tmp > 0) {
                denominator *=  tmp / gcd(tmp, i);
                numerator *= (long)i / gcd(tmp, i);
            } else {
                numerator *= i;
            }
            tmp--;
        }

        return numerator / denominator;
    }

    public static long gcd(long a, long b) {
        if (a == 0) return b;
        return gcd(b % a, a);
    }
}
/*
int main() {
    int x, y, z, w; cin >> x >> y >> z >> w;
    int xt = 0, yt = 0, zt = 0, ans = 0;
    while (xt <= w) {
        while (yt <= w) {
            while (zt <= w) {
                if (xt + yt + zt == w) {
                    ans++;
                    cout << "(" << xt << ", " << yt << ", " << zt << ")" << endl;
                }
                zt += z;
            }
            zt = 0;
            yt += y;
        }
        xt += x;
        yt = 0;
    }
    cout << "In total we have " << ans << " possible combinations";
    return 0;
}
*/
