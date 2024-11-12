package ass2

import scala.annotation.tailrec
import tictactoe.TicTacToe

@main def play: Unit =
  import tictactoe.*, TicTacToe.*, Mark.*, Game.Player.*

  println("Welcome to Tic-Tac-Toe!")
  println("You are X, and the computer is O.")
  println("Please select the algorithm to use:")
  println("1. Minimax")
  println("2. Alpha-beta pruning")
  print("Enter the number of the algorithm: ")

  val tree =
    scala.io.StdIn.readInt() match
      case 1 => new Minimax(TicTacToe)
      case 2 => new AlphaBeta(TicTacToe)
      case _ => return
    
  import tree.*

  val human = Max // The human player holds the X mark.

  @tailrec def round(state: Board): Unit =
    println(state)

    if gameOver(state) then
      println("Game over!")
      println:
        winner(state) match
          case Some(`human`) => "You win!"
          case Some(_) => "You lose!"
          case None => "It's a draw!"
    else if nextPlayer(state) == human then
      print("It's your turn. Enter your move (0 to 8): ")
      scala.io.StdIn.readInt() match
        case move if isValidMove(move) =>
          if canMove(state, move) then
            round(makeMove(state, move))
          else
            println(s"Position ${move} is not empty. Try again.")
            round(state)
        case move =>
          println(s"Position ${move} is invalid. Try again.")
          round(state)
    else
      val node = createTree(state)
      bestMove(node) match
        case Some(move) if canMove(state, move) =>
          println(s"The computer made a move: $move")
          round(makeMove(state, move))
        case Some(move) =>
          println(s"Error: The computer made a bad move: $move")
        case None =>
          println("Error: The computer cannot make a move.")

  round(initialState)
