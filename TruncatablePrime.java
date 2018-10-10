/**
 * Truncatable prime is a prime number that when you successively remove digits
 * from one end of the prime, you are left with a new prime number. 
 * Examples: 997 is called a left-truncatable prime as the numbers 997, 97, 
 * and 7 are all prime. 7393 is a right-truncatable prime as the numbers 7393, 
 * 739, 73, and 7 formed by removing digits from its right are also prime. 
 * The task is to find the largest left-truncatable and right-truncatable primes
 * less than 1.000.000 
 * No zeroes are allowed in truncatable prime.
 */
package truncatableprime;

public class TruncatablePrime {

    private final int limit;
    
    public static void main(String[] args) {
        TruncatablePrime tp = new TruncatablePrime(1000000);
        int right = tp.maxRightTrunc();
        int left = tp.maxLeftTrunc();
        System.out.printf("largest %s-truncatable prime -> %d%n"
                + "largest %s-truncatable prime -> %d%n", 
                "left", left, "right", right);
    }

    public TruncatablePrime(int limit) {
        this.limit = limit;
    }
    
    public boolean isPrime(int number) {
        if (number == 2) return true;
        if (number < 2 || number % 2 == 0) return false;
        for(int i = 3; i * i <= number; i += 2) {
            if(number % i == 0)
                return false;
        }
        return true;
    }
    
    public int maxRightTrunc() {
        int number = this.limit;
        for (; number > 1; number--) {
            int iter = number;
            while (this.isPrime(iter)) {
                iter /= 10;
            }
            if (iter == 0) {
                return number;
            }
        }
        return -1;
    }
    
    public int maxLeftTrunc() {
        int number = this.limit;
        for (; number > 1; number--) {
            int iter = number;
            int digits = this.digits(iter);
            while (this.isPrime(iter) && !String.valueOf(iter).contains("0")) {
                digits--;
                iter %= Math.pow(10, digits);
            }
            if (iter == 0) {
                return number;
            }
        }
        return -1;
    }

    public int digits(int number) {
        int count = 0;
        while (number > 0) {
            count++;
            number /= 10;
        }
        return count;
    }
}