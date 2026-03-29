package taks_3;

import java.math.BigInteger;

/**
 * Task 3: Factorial digit sum.
 * This program calculates 100! and then finds the sum of its digits.
 * Standard primitive types like long cannot hold 100!,
 * so java.math.BigInteger is used for high-precision arithmetic.
 */
public final class FactorialSum {

    static void main(final String[] args) {

        // Define the number for which factorial is to be calculated
        final int n = 100;

        // Calculate 100 factorial
        final BigInteger factorial = calculateFactorial(n);

        // Convert the large number to string to iterate over digits
        final String factorialStr = factorial.toString();

        // Iterate over each digit and sum them using stream API
       final int sum = factorialStr.chars().map(Character::getNumericValue).sum();

       // Print the sum of the digits
        System.out.println("The sum of the digits in 100! is: " + sum);
    }


    // Calculate factorial of n using BigInteger
    public static BigInteger calculateFactorial(final int n) {
        // Initialize result with 1
        BigInteger result = BigInteger.ONE;
        // Iterate from 2 to n and multiply result by each number
        for (int i = 2; i <= n; i++) {
            result = result.multiply(BigInteger.valueOf(i));
        }
        // Return the resulting factorial
        return result;
    }
}
