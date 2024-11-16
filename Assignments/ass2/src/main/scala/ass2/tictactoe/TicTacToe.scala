package ass2
package tictactoe

import Mark.*

object TicTacToe extends Game[Board, Int]:
  /** Create the initial state of the game. */
  override def initialState: Board = {
    Board.empty
  }

  /** Decide the next player based on the state. This is feasible by checking
   *  the number of moves made by each player.
   */  
  override def nextPlayer(state: Board): Game.Player = {
    val num_X = state.cells.count(_ == Some(Mark.X))
    val num_O = state.cells.count(_ == Some(Mark.O))

    if num_X > num_O then Game.Player.Min
    else Game.Player.Max
  }
  
  /** Return all possible moves of next player from the current state. */  
  override def possibleMoves(state: Board): LazyList[Int] = {
    LazyList.from(0).take(9).filter(x => state.cells(x) == None)
  }

  /** Check if the move is valid. */
  override def isValidMove(move: Int): Boolean = {
    move >= 0 && move < 9
  }

  /** Check if the move can be made in the current state. The given `move`
   *  is expected to be a valid move. Do not check the validity of the move.
   */  
  override def canMove(state: Board, move: Int): Boolean = {
    state.cells(move) == None
  }
  
  /** Make a move and return the new state. */  
  override def makeMove(state: Board, move: Int): Board = {
    state.updated(move, if nextPlayer(state) == Game.Player.Max then Mark.X else Mark.O)
  }
  
  /** Check if the game is over. */  
  override def gameOver(state: Board): Boolean = {
    state.isFull || winner(state) != None
  }

  /** Evaluate the score of the game from the max player's perspective. */
  override def evaluate(state: Board): Int = {
    winner(state) match
      case Some(Game.Player.Max) => 1    // X wins
      case Some(Game.Player.Min) => -1   // O wins
      case None => 0                     // Draw or ongoing game    
  }
  
  /** Return the winner of the game. */
  override def winner(board: Board): Option[Game.Player] = {
    val winningPositions = List(
      List(0, 1, 2), List(3, 4, 5), List(6, 7, 8), // Rows
      List(0, 3, 6), List(1, 4, 7), List(2, 5, 8), // Columns
      List(0, 4, 8), List(2, 4, 6)                 // Diagonals
    )
    
    for (positions <- winningPositions) {
      val marks = positions.map(board.cells(_))
      if marks.forall(_ == Some(Mark.X)) then return Some(Game.Player.Max)
      if marks.forall(_ == Some(Mark.O)) then return Some(Game.Player.Min)
    }
    
    None  
  }
