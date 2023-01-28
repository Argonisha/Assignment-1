# Assignment-1
## Instructions
Go to Command Prompt and locate where the file is saved  
Once in the directory where the file is saved type "javac FindPrimes.java"  
Next type "java FindPrimes"  
You should now see a test file called primes.txt with the appropriate information.
## Description
When approaching this assignment, I first spawn the 8 threads to be used  
I then use all 8 threads to go through every odd number, starting from 99,999,999 because all even numbers are not prime.  
The first 10 numbers that are found are put into an array of largest numbers, and most of the time are ordered correctly, but I run them through a sort later just in case.  
The number is checked for prime, if its prime the number is added to the total and the counter is incremented by 1.  
My runtime while testing on my home computer varies between 5600ms and 6400ms.  
