package com.interview.esource.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.interview.esource.models.PrimeNumber;
import com.interview.esource.services.NumberService;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
public class NumberController {
    @Autowired
    private NumberService numberService; 

    @GetMapping("/v1/esource/ping")
    public ResponseEntity<String> ping() {
        return ResponseEntity.ok("pong");
    }
    
    @GetMapping("/v1/esource/generate-lists-of-numbers")
    public ResponseEntity<List<List<Integer>>> generateThreeListOfNumbers() {
        // Opportunity to enhance this with a param to generate 'n' amount of lists instead of 3 list of numbers
        List<List<Integer>> randomThreeListOfNumbers = numberService.generateThreeListOfNumbers(); 
        return ResponseEntity.ok(randomThreeListOfNumbers);
    }

    @GetMapping("/v1/esource/available-numbers")
    public ResponseEntity<List<Integer>> findAvailableNumbers() {
        List<List<Integer>> listOfListOfNumbers = numberService.generateThreeListOfNumbers();
        List<Integer> availableNumbers = numberService.findAvailableNumbers(listOfListOfNumbers); 
        return ResponseEntity.ok(availableNumbers);
    }

    @GetMapping("/v1/esource/largest-prime-number")
    public ResponseEntity<PrimeNumber> findLargestPrimeNumber() {
        List<List<Integer>> listOfListOfNumbers = numberService.generateThreeListOfNumbers();
        List<Integer> availableNumbers = numberService.findAvailableNumbers(listOfListOfNumbers); 
        PrimeNumber largestPrimeNumber = numberService.findLargestPrimeNumber(availableNumbers);
        return ResponseEntity.ok(largestPrimeNumber);
    }
}
