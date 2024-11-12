# Assignment 2: Tic-Tac-Toe

## Overview

In this assignment, you will implement a Tic-Tac-Toe game and. The project is divided into three parts: tic-tac-toe game, minimax algorithm, and alpha-beta pruning. Each part builds upon the previous one, so it is important to complete them in order.

## Part 1: Tic-Tac-Toe

[Tic-tac-toe](https://en.wikipedia.org/wiki/Tic-tac-toe) (or Xs and Os)
is a paper-and-pencil game for two players who take turns marking the spaces in a three-by-three grid with X or O.
The player who succeeds in placing three of their marks in a horizontal, vertical, or diagonal row is the winner.

In this part, you will implement the core game logic for Tic-Tac-Toe. To be more specific, you need to:

1. **Implement the `Board` class in *Board.scala*.** This class represents the game board and includes methods to:

   1. Check if the board is full with `isFull`.
   2. Access and update cells with `apply`.
   3. Update the board with `update`.
   4. Create a new board with `empty`.

   You MUST NOT change the parameters of `Board` class because we will access the underlying `Vector` in the tests. If you make any changes to them, the tests might fail.

2. **Implement the `Game` in *TicTacToe.scala*.** This class defines the game logic of tic-tac-toe, including methods to:

   1. Get the initial state with `initialState`.
   2. Determine the current player with `currentPlayer`.
   3. List possible moves with `possibleMoves`.
   4. Make a move with `makeMove`.
   5. Check if the game is over with `gameOver`.
   6. Evaluate the game state `evaluate`.
   7. Get the winner with `winner`.

   You can read the definition of `Game` to gain more insight.

### Notes

- `Board`'s `toString` method is implemented for you. You can use it to visualize the game board.
- We always start with player `Max` (X) and player `Min` (O) alternates.
- The game is over when one player wins or the board is full.

### Hints

- Most functions can be implemented in a single line.
  The remaining functions can be implemented in a few lines.
- The implementation of `evaluate` is various and heuristic.
  There is not a unique way to implement it.
  The most straightforward way is to check all possible lines, and

  - if player `Max` wins, return `1`;
  - if player `Min` wins, return `-1`; or
  - if the game is a draw, return `0`.

  You can also explore other heuristic ways to implement it.

## Part 2: Minimax Algorithm

The [minimax][minimax] algorithm is an algorithm
for finding the best move in a two-player game.
The algorithm first builds a game tree.
Leaf nodes present game states where the game is over,
while internal nodes present game states where the game is in progress.

In this part, you will implement the minimax algorithm to find the best move for the current player.
Your main task is to implement the `bestMove` method,
which should recursively evaluate the game tree to find the best move for the current player:

- if the game is over, return the score of the state;
- if the current player is `Max`, choose the move with the highest score; or
- if the current player is `Min`, choose the move with the lowest score.

[minimax]: https://en.wikipedia.org/wiki/Minimax#Minimax_algorithm_with_alternate_moves

### Notes

1. We provide an interactive command-line tic-tac-toe shell in `play.scala`
   for you to quickly test your implementation.
   You can run `run` in the sbt console to start the game.
   Make sure you have finished the first part before running the game.
2. If you haven't heard of this algorithm, don't be afraid, it's simple.
   Basically, it traverses the tree and calculates the maximum/minimum value in different plies.
   Read the Wikipedia page (especially the [_Pseudocode_ section][minimax-pseudocode])
   or other tutorials and implement it in a functional programming manner.
3. Your implementation should go through every node in the game tree without setting a depth limit.
   Since the algorithm traverses the entire game tree to find the best move,
   the laziness of `LazyList` is not utilized. It is fine to be inefficient in this part.

[minimax-pseudocode]: https://en.wikipedia.org/wiki/Minimax#Pseudocode

## Part 3: Alpha-Beta Pruning

We have implemented the minimax algorithm, which basically explores the entire game tree to find the best move.
In this part, you will implement the [alpha-beta pruning][ab] algorithm to optimize the minimax algorithm.

You should implement the `bestMove` method of the `AlphaBeta` given instance.
This method should recursively and selectively explore the game tree using alpha-beta pruning to find the best move for the current player.

- if the game is over, return the evaluation of the state;
- if the current player is `Max`, use Alpha-Beta pruning to choose the move with the highest evaluation; or
- if the current player is `Min`, use Alpha-Beta pruning to choose the move with the lowest evaluation.

[ab]: https://en.wikipedia.org/wiki/Alpha%E2%80%93beta_pruning

### Notes

1. You should make use of the laziness of `LazyList` to prune unnecessary branches in the game tree.
2. Read the Wikipedia or other tutorials to understand the algorithm.
   It's not difficult once you have learned how to implement the minimax algorithm.

## Tests

The skeleton project includes a small set of tests for each part to help you verify your implementation.
You can run the tests using the `test` command in the sbt console.
These tests only cover the basic functionality of your implementation.
We will evaluate your submission with additional test cases.

To be more specific, we will check:

- that you have visited every node in the game tree in the minimax algorithm;
- that you have pruned unnecessary branches in the alpha-beta pruning algorithm by checking the number of nodes created;
- that your implementation should be able to draw on tic-tac-toe with our implementation; and
- the effectiveness of your implementation of alpha-beta pruning on a larger tic-tac-toe-like game (technically speaking, a [_m,n,k_-game][mnk] with slightly larger _m_, _n_, and _k_).
  Your implementation is not required to win every game,
  but it should be able to place a mark to block the opponent from forming a line,
  which is a very basic requirement for a game search algorithm.

[mnk]: https://en.wikipedia.org/wiki/M,n,k-game
  

## Submission

Submit a zip archive containing your implementation of `TicTacToe.scala`, `Minimax.scala`, and `AlphaBeta.scala` files. The zip archive should only have these files.