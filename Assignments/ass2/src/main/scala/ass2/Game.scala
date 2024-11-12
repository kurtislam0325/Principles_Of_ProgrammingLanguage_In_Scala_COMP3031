package ass2

trait Game[State, Move]:
  /** Create the initial state of the game. */
  def initialState: State

  /** Decide the next player based on the state. This is feasible by checking
   *  the number of moves made by each player.
   */
  def nextPlayer(state: State): Game.Player

  /** Return all possible moves of next player from the current state. */
  def possibleMoves(state: State): LazyList[Move]

  /** Check if the move is valid. */
  def isValidMove(move: Move): Boolean

  /** Check if the move can be made in the current state. The given `move`
   *  is expected to be a valid move. Do not check the validity of the move.
   */
  def canMove(state: State, move: Move): Boolean

  /** Make a move and return the new state. */
  def makeMove(state: State, move: Move): State

  /** Check if the game is over. */
  def gameOver(state: State): Boolean

  /** Evaluate the score of the game from the max player's perspective. */
  def evaluate(state: State): Int

  /** Return the winner of the game. */
  def winner(state: State): Option[Game.Player]
end Game

object Game:
  enum Player:
    /** The player who tries to maximize the score. */
    case Max

    /** The player who tries to minimize the score. */
    case Min

    /** Return the ordering required by the player. */
    def ordering[T](implicit ord: Ordering[T]): Ordering[T] = this match
      case Max => ord
      case Min => ord.reverse
