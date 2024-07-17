package com.interview.esource;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.interview.esource.models.PrimeNumber;
import com.interview.esource.services.NumberService;

@SpringBootApplication
public class EsourceApplication implements CommandLineRunner {
	@Autowired
	NumberService numberService;
	public static void main(String[] args) {
		SpringApplication.run(EsourceApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		List<List<Integer>> listOfListOfNumbers = numberService.generateThreeListOfNumbers();
		List<Integer> availableNumbers = numberService.findAvailableNumbers(listOfListOfNumbers);
		PrimeNumber largestPrimeNumber = numberService.findLargestPrimeNumber(availableNumbers);

		System.out.println("ESourceApplication::List of three arrays: " + listOfListOfNumbers);
		System.out.println("ESourceApplication::List of available numbers: " + availableNumbers);
		System.out.println("ESourceApplication::Largest prime number: " + largestPrimeNumber);
	}
}
