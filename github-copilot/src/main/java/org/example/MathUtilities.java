package main.java.org.example;

public class MathUtilities {
    private static final Logger logger = LoggerFactory.getLogger(MathUtilities.class);

    public static int add(int a, int b) {
        logger.info("Adding {} and {}", a, b);
        int result = a + b;
        logger.info("Result of add: {}", result);
        return result;
    }

    public static int multiply(int a, int b) {
        logger.info("Multiplying {} and {}", a, b);
        int result = a * b;
        logger.info("Result of multiply: {}", result);
        return result;
    }

    public static int factorial(int n) {
        logger.info("Calculating factorial of {}", n);
        if (n < 0) throw new IllegalArgumentException("Negative numbers do not have a factorial.");
        if (n == 0 || n == 1) {
            logger.info("Factorial of {} is 1", n);
            return 1;
        }
        int result = 1;
        for (int i = 2; i <= n; i++) {
            result *= i;
        }
        logger.info("Result of factorial({}): {}", n, result);
        return result;
    }

    public static java.util.ArrayList<Integer> primesUpTo(int n) {
        logger.info("Finding primes up to {}", n);
        java.util.ArrayList<Integer> primes = new java.util.ArrayList<>();
        if (n < 2) return primes;
        boolean[] isPrime = new boolean[n + 1];
        for (int i = 2; i <= n; i++) {
            isPrime[i] = true;
        }
        for (int p = 2; p * p <= n; p++) {
            if (isPrime[p]) {
                for (int i = p * p; i <= n; i += p) {
                    isPrime[i] = false;
                }
            }
        }
        for (int i = 2; i <= n; i++) {
            if (isPrime[i]) {
                primes.add(i);
            }
        }
        logger.info("Primes up to {}: {}", n, primes);
        return primes;
    }

    public static int gcd(int a, int b) {
        logger.info("Calculating GCD of {} and {}", a, b);
        if (b == 0) {
            logger.info("GCD result: {}", Math.abs(a));
            return Math.abs(a);
        }
        int result = gcd(b, a % b);
        logger.info("GCD intermediate result: {}", result);
        return result;
    }

    public static boolean isPrime(int n) {
        logger.info("Checking if {} is prime", n);
        if (n <= 1) {
            logger.info("{} is not prime", n);
            return false;
        }
        for (int i = 2; i <= Math.sqrt(n); i++) {
            if (n % i == 0) {
                logger.info("{} is not prime", n);
                return false;
            }
        }
        logger.info("{} is prime", n);
        return true;
    }

    public static int sumOfEvenNumbers(java.util.ArrayList<Integer> numbers) {
        logger.info("Calculating sum of even numbers in {}", numbers);
        int sum = 0;
        for (int num : numbers) {
            if (num % 2 == 0) {
                sum += num;
            }
        }
        logger.info("Sum of even numbers: {}", sum);
        return sum;
    }
}
