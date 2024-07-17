# ESource Interview

## Table of Contents 
- [ESource Interview](#esource-interview)
  - [Table of Contents](#table-of-contents)
  - [Description](#description)
  - [Available APIs](#available-apis)
  - [Author](#author)

## Description
This service used for the ESource interview process 

## Available APIs
- **List of Numbers**
  - `/v1/esource/generate-lists-of-numbers`
  - **GET** a list (currently requirement is at 3 lists) of numbers that contains 15 numbers in each list, ranging from 0-50
- **Unique Numbers**
  - `/v1/esource/available-numbers`
  - **GET** the list of unique numbers **not present** from the three lists above
- **Largest Prime Number**
  - `/v1/esource/largest-prime-number`
  - **GET** the largest prime number from the generated lists

## Author
Jayson Tan