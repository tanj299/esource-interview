package com.interview.esource.services;

import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class NumberService {
    private static final Integer NUMBER_OF_LISTS_TO_GENERATE = 3;
    private static final Integer MIN_VALUE = 0;
    private static final Integer MAX_VALUE = 50; 

    public List<List<Integer>> generateThreeListOfNumbers() {
        List<List<Integer>> randomListOfNumbers = Stream.generate(() -> generateRandomNumbers())
            .limit(NUMBER_OF_LISTS_TO_GENERATE)
            .toList();

        log.info("generateThreeListOfNumbers::Number of lists and its content: " + randomListOfNumbers);
        return randomListOfNumbers;
    }

    public List<Integer> generateRandomNumbers() {
        Random random = new Random();
        List<Integer> randomNumbers = IntStream.rangeClosed(1,15)
            .map(i -> random.nextInt((MAX_VALUE - MIN_VALUE) + 1) + MIN_VALUE)
            .boxed()
            .toList();

        return randomNumbers;
    }
    
    public List<Integer> findAvailableNumbers(List<List<Integer>> listOfListOfNumbers) {
        // Combine all lists of numbers into a set, since sets cannot contain duplicates
        Set<Integer> combinedNumbersSet = listOfListOfNumbers.stream()
            .flatMap(n -> n.stream())
            .collect(Collectors.toSet());

        log.info("findAvailableNumbers::Each list of numbers: " + listOfListOfNumbers);
        log.info("findAvailableNumbers::All numbers combined: " + combinedNumbersSet);

        // Generate a list of numbers from 0 to 50
        List<Integer> allNumbers = IntStream.rangeClosed(0, 50)
            .boxed()
            .collect(Collectors.toList());

        // Then remove each number that is not present from allNumbers based off of the set
        allNumbers.removeAll(combinedNumbersSet);
        log.info("findAvailableNumbers::Unique available numbers: " + allNumbers);

        return allNumbers;
    }

    public Integer findLargestPrimeNumber(List<Integer> availableNumbers) {
        // Find the largest prime number using streams
        Optional<Integer> largestPrimeNumber = availableNumbers.stream()
                .filter(i -> isPrime(i)) // Filter prime numbers
                .max(Integer::compareTo); // Find the maximum prime number

        if (largestPrimeNumber.isPresent()) {
            log.info("findLargestPrimeNumber::Largest prime number is: " + largestPrimeNumber);
            return largestPrimeNumber.get();
        } else {
            log.info("findLargestPrimeNumber::No prime numbers found in the list.");
            return -1;
        }
    }
    
    // Helper function to check if a number is prime
    public boolean isPrime(int number) {
        if (number <= 1) {
            return false;
        }
        
        if (number == 2) {
            return true; // 2 is the only even prime number
        }

        if (number % 2 == 0) {
            return false; // Eliminate even numbers greater than 2
        }

        for (int i = 2; i <= Math.sqrt(number); i++) {
            if (number % i == 0) {
                return false;
            }
        }

        return true;
    }

}
