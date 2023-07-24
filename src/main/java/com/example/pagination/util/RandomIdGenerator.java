package com.example.pagination.util;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
@Slf4j
public class RandomIdGenerator {
    private static final String LETTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final String NUMBERS = "0123456789";

    public static void main(String[] args) {
        String pattern = "@#@#$"; // Customize the pattern as needed
        int maxIterations = calculateMaxIterations(pattern);
        log.info("Max iterations : {}", maxIterations);
        generateRandomIDs(pattern, maxIterations).take(maxIterations).subscribe(System.out::println);
    }

    private static Flux<String> generateRandomIDs(String pattern, int maxIterations) {
        return Flux.range(0, maxIterations)
                .map(i -> generateRandomID(pattern));
    }

    private static String generateRandomID(String pattern) {
        StringBuilder idBuilder = new StringBuilder();
        for (char c : pattern.toCharArray()) {
            switch (c) {
                case '#':
                    idBuilder.append(randomDigit());
                    break;
                case '@':
                    idBuilder.append(randomLetter());
                    break;
                case '$':
                    idBuilder.append(randomAlphaNumeric());
                    break;
                default:
                    idBuilder.append(c);
            }
        }
        return idBuilder.toString();
    }

    private static char randomLetter() {
        return LETTERS.charAt((int) (Math.random() * LETTERS.length()));
    }

    private static char randomDigit() {
        return NUMBERS.charAt((int) (Math.random() * NUMBERS.length()));
    }

    private static char randomAlphaNumeric() {
        String characters = LETTERS + NUMBERS;
        return characters.charAt((int) (Math.random() * characters.length()));
    }

    private static int calculateMaxIterations(String pattern) {
        int maxIterations = 1;
        for (char c : pattern.toCharArray()) {
            if (c == '#') {
                maxIterations *= 10; // If pattern has a digit, each place can have 10 possibilities (0-9)
            }
        }
        return maxIterations;
    }
}
