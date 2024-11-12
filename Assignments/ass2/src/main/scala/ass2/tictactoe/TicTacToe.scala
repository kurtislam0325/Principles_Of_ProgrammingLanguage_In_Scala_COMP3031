package ass2
package tictactoe

import Mark.*

object TicTacToe extends Game[Board, Int]:

  override def initialState: Board =  ???
  
  override def nextPlayer(state: Board): Game.Player = ???
  
  override def possibleMoves(state: Board): LazyList[Int] = ???

  override def isValidMove(move: Int): Boolean = ???

  override def canMove(state: Board, move: Int): Boolean = ???
  
  override def makeMove(state: Board, move: Int): Board = ???
  
  override def gameOver(state: Board): Boolean = ???

  override def evaluate(state: Board): Int = ???
  
  override def winner(board: Board): Option[Game.Player] = ???
