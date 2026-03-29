package task_1;

import java.util.LinkedList;
import java.util.List;
import java.util.Stack;
import java.util.stream.Collectors;

/**
 * Task 1: Balanced parentheses.
 * This program generates all possible combinations of balanced parentheses for a given number of pairs.
 * It uses a backtracking algorithm to explore all potential combinations while ensuring that the parentheses remain balanced.
 */
public final class BalancedParentheses {

    public static void main(final String[] args) {
        // Define the number of parentheses to check
        final int n = 3;

        // Initialize the stack and result lists
        final Stack<String> stack = new Stack<>();
        final List<String> result = new LinkedList<>();

        // Generate all combinations of balanced 'parentheses'
        backtrack(n, 0, 0, stack, result);

        // Print the results
        System.out.println("Result: " + result.size());
        System.out.println("[" + String.join(", ", result) + "]");
    }

    public static void backtrack(final int n, final int open, final int close, final Stack<String> stack, final List<String> result) {
        //check if the n is negative
        if (n < 0) {
            return;
        }

        // check if the number of open and close parentheses are equal to n, if so add the current combination to the result list
        if (open == close && open == n) {
            result.add(String.join("", stack));
            return;
        }

        // generate all combinations of balanced parentheses by adding or removing parentheses from the stack
        if (open < n) {
          stack.push("(");
           backtrack(n, open + 1, close, stack, result);
           stack.pop();
        }

        // generate all combinations of balanced parentheses by adding or removing parentheses from the stack
        if (close < open) {
          stack.push(")");
           backtrack(n, open, close + 1, stack, result);
           stack.pop();
        }
    }
}