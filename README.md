# mastermind

# Java version - Java 17

This is a game where a player tries to guess the number combinations. 
At the end of each attempt to guess the 4 number combinations, 
the computer will provide feedback whether the player had guess a number correctly, 
or/and a number and digit correctly. A player must guess the 
right number combinations within 10 attempts to win the game.

# Example Run:

Game initializes and selects “0 1 3 5” <br />

Player guesses “2 2 4 6”, game responds “all incorrect” <br />

Player guesses “0 2 4 6”, game responds “1 correct number and 1 correct location” <br />

Player guesses “2 2 1 1”, game responds “1 correct number and 0 correct location”

# API to generate random numbers
Use Random generator API (https://www.random.org/clients/http/api/) 
to randomly select 4 numbers from 0 ~ 7