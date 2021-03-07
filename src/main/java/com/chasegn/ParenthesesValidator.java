package com.chasegn;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Stack;

public class ParenthesesValidator {
    public static boolean isValid(String s) {
        if (s.length() < 2) {
            return false;
        }

        Stack<Character> stack = new Stack<>();

        for (char c : s.toCharArray()) {
            switch (c) {
                case '(':
                case '{':
                case '[':
                    stack.push(c);
                    break;
                case ')':
                    if (stack.size() < 1 || stack.pop() != '(') return false;
                    break;
                case '}':
                    if (stack.size() < 1 || stack.pop() != '{') return false;
                    break;
                case ']':
                    if (stack.size() < 1 || stack.pop() != '[') return false;
                    break;
            }
        }

        return stack.size() == 0;
    }


    public static void main(String[] args) throws IOException {
//        List<String> list = new ArrayList<>();
//        list.add("()"); // true
//        list.add("()[]{}"); // true
//        list.add("(]"); // false
//        list.add("([)]"); // false
//        list.add("{[]}"); // true
//        list.add(")("); // false
//        list.add("[{]"); // false
//        list.add("(()(()()()))"); // true
//        list.add("{[[]{}]}()()"); // true
//
//        for (String phrase : list) {
//            System.out.println(ParenthesesValidator.isValid(phrase));
//        }

        byte[] encoded = Files.readAllBytes(Paths.get("src/main/resources/sampleLispProgram.txt"));
        System.out.println(ParenthesesValidator.isValid(new String(encoded, Charset.defaultCharset())));
    }
}
