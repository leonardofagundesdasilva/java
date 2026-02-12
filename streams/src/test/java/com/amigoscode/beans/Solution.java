package com.amigoscode.beans;

import java.io.*;
import java.util.*;
import java.util.stream.*;
import static java.util.stream.Collectors.joining;

class Result {
    private static final List<Character> OPEN_BRACKETS = List.of('{', '[', '(');

    /*
     * Complete the 'isBalanced' function below.
     *
     * The function is expected to return a STRING.
     * The function accepts STRING s as parameter.
     */
    //balanced {[([[({}{})]])]}
    //not balanced {[[[({})]])]}
    // }{}
    // {{{
    //{()}
    //{{]
    //s = {{
    public static String isBalanced(String s) {
        //Create stack
        Stack<Character> stack = new Stack<>();

        //Verify every char
        //If open bracket, add to stack
        //If close bracker, check if top of stack is equivalent
        //If not equivalent, return NO
        //If equivalent, keep checking
        for(Character c: s.toCharArray()) {
            if(isOpenBracket(c)) {
                stack.push(c);
            } else if(stack.isEmpty()
                    || !isEquivalent(c, stack.pop())) {
                return "NO";
            }
        }

        //If stack not empty in the end, return NO
        return stack.isEmpty() ? "YES" : "NO";
    }

    private static boolean isOpenBracket(char c) {
        return OPEN_BRACKETS.contains(c);
    }

    private static boolean isEquivalent(char closeBracket, char openBracket) {
        return openBracket == getEquivalent(closeBracket);
    }

    private static char getEquivalent(char closeBracket) {
        switch(closeBracket) {
            case ']' : return '[';
            case '}' : return '{';
            default: return '(';
        }
    }

}

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int t = Integer.parseInt(bufferedReader.readLine().trim());

        IntStream.range(0, t).forEach(tItr -> {
            try {
                String s = bufferedReader.readLine();

                String result = Result.isBalanced(s);

                bufferedWriter.write(result);
                bufferedWriter.newLine();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        bufferedReader.close();
        bufferedWriter.close();
    }
}
