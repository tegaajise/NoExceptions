import java.util.ArrayList;
import java.util.List;

public class Primes {
    private static ArrayList<Integer> primes = new ArrayList<>();
    public static boolean isPrime(int n){
        if (n <= 1){
            return false;
        }
        for (int i = 2; i <= Math.sqrt(n); i++) {
            if (n % i == 0) {
                return false; // Not prime
            }
        }
        return true;
    }

    public static int kthPrime(int k) {
        int nums = primes.size();
        int i = primes.size() > 0 ? primes.get(primes.size() - 1) : 1;
        if (k >= nums) {
            while (nums <= k) {
                if (isPrime(++i)) {
                    primes.add(i);
                    nums++;
                }
            }
        }
        return primes.get(k);
    }

    public static List<Integer> factorize(int n) {
        List<Integer> primeFactors = new ArrayList<>();
        for (int prime : primes) {
            while (n % prime == 0) {
                primeFactors.add(prime);
                n /= prime;
            }
            if (n == 1) {
                break;
            }
        }

        if (n > 1) {
            primeFactors.add(n); // Remaining prime factor (if any)
        }

        return primeFactors;
    }
}

