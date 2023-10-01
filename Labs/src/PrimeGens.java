import java.util.Iterator;
public class PrimeGens {

    public static class TwinPrimes implements Iterator<Integer>{
        private int curr = 3;
        @Override
        public boolean hasNext() {
            return true;
        }

        @Override
        public Integer next() {
            int twinPrime;
            while (true) {
                if (Primes.isPrime(curr) && Primes.isPrime(curr + 2)) {
                    twinPrime = curr;
                    curr += 2; // Move to the next odd number
                    break;
                }
                curr += 2; // Move to the next odd number
            }
            return twinPrime;
        }
    }

    public static class SafePrimes implements Iterator<Integer>{
        private int curr = 2;
        @Override
        public boolean hasNext() {
            return true;
        }

        @Override
        public Integer next() {
            while (true) {
                if (isSafePrime(Primes.kthPrime(curr))) {
                    int safePrime = Primes.kthPrime(curr++);
                    return safePrime;
                }
                curr++;
            }
        }

        private boolean isSafePrime(int n) {
            int p = (n - 1) / 2;
            return Primes.isPrime(p);
        }
    }

    public static class StrongPrimes implements Iterator<Integer>{
        private int curr = 1;
        @Override
        public boolean hasNext() {
            return true;
        }

        @Override
        public Integer next() {
            while (true) {
                if (isStrongPrime(curr)) {
                    int strongPrime = Primes.kthPrime(curr++);
                    return strongPrime;
                }
                curr++;
            }
        }

        private boolean isStrongPrime(int num) {

            int prevPrime = Primes.kthPrime(num - 1);
            int nextPrime = Primes.kthPrime(num + 1);

            double average = (prevPrime + nextPrime) / 2.0;
            return Primes.kthPrime(num) > average;
        }
    }


}
