package com.interview.esource;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.interview.esource.models.PrimeNumber;
import com.interview.esource.services.NumberService;

@SpringBootTest
public class EsourceApplicationTests {

	@Autowired
	private NumberService numberService;

	@Test
	void contextLoads() {
	}

	// Test random numbers in list
	@Test
	public void WHEN_list_of_lists_of_numbers_generated_THEN_expect_three_lists() {
		List<List<Integer>> result = numberService.generateThreeListOfNumbers();
		assertEquals(3, result.size());
	}

	@Test
	public void WHEN_list_of_lists_of_numbers_generated_THEN_each_list_contains_fifteen_numbers() {
		List<List<Integer>> result = numberService.generateThreeListOfNumbers();
		assertAll("Each list should contain 15 numbers",
			() -> assertEquals(15, result.get(0).size()),
			() -> assertEquals(15, result.get(1).size()),
			() -> assertEquals(15, result.get(2).size()));
	}

	// Test non-duplicate numbers in list of lists
	@Test
	public void WHEN_list_of_lists_of_number_generated_THEN_no_duplicate_values_in_all_lists() {
		List<List<Integer>> listOfListOfNumbers = numberService.generateThreeListOfNumbers();
		Set<Integer> firstNumbersSet = new HashSet<>(listOfListOfNumbers.get(0));
		Set<Integer> secondNumbersSet = new HashSet<>(listOfListOfNumbers.get(1));
		Set<Integer> thirdNumbersSet = new HashSet<>(listOfListOfNumbers.get(2));

		List<Integer> availableNumbers = numberService.findAvailableNumbers(listOfListOfNumbers);
		Set<Integer> availableNumbersSet = new HashSet<>(availableNumbers);
		assertFalse(firstNumbersSet.equals(availableNumbersSet));
		assertFalse(secondNumbersSet.equals(availableNumbersSet));
		assertFalse(thirdNumbersSet.equals(availableNumbersSet));
	}

	// Test largest prime number in list of lists
	@Test
	public void WHEN_candidate_prime_number_is_negative_THEN_return_false() {
		int negativeNumber = -5;
		boolean isPrimeNumber = numberService.isPrime(negativeNumber);
		assertFalse(isPrimeNumber);
	} 

	@Test
	public void WHEN_candidate_prime_number_is_negative_and_prime_THEN_return_false() {
		int negativeNumber = -47;
		boolean isPrimeNumber = numberService.isPrime(negativeNumber);
		assertFalse(isPrimeNumber);
	} 

	@Test
	public void WHEN_largest_prime_number_THEN_prime_number_object_is_true() {
		List<List<Integer>> listOfListOfNumbers = numberService.generateThreeListOfNumbers();
		List<Integer> availableNumbers = numberService.findAvailableNumbers(listOfListOfNumbers);
		PrimeNumber primeNumber = numberService.findLargestPrimeNumber(availableNumbers);
		assertTrue(primeNumber.isPrimeNumberExists());
	} 
}
