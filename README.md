# The Tennis Kata
## Description
Tennis is a ball and racket sport that is scored in an interesting way. The scoring system of a tennis match is based on points, games and sets. We will focus on the scoring of points inside a single game for the scope of this exercise, leaving explicitly out the set & match management. 
The rules that we want you to consider are the ones found in the section “Game Score” of the wikipedia page of Tennis Scoring System (excluding the tie-break, which is also out of the scope of this exercise)

###Feature 1 - Scoring a Game in Real Time
We want a library in Java that can be used to score a game in real time, so we can use it for all of the tennis related endeavours we plan to undertake in the future. To begin with, we're going to need a way to update the score when a player wins a point, see what the current score is after each service, and see if there is a winner based on the current score and the rules above. 

## Build and test
The library uses gradle (wrapper) and Cucumber for testing the application domain layer. To test it, type:
```gradle clean test```
