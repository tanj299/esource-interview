package com.interview.esource.models;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class PrimeNumber implements Serializable {
    int primeNumber;
    boolean isPrimeNumberExist; 
}
