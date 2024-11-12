package ass2

/** A trait for game trees, which represents all possible game states in a game. */
trait GameTree[State, Move]:
  /** A node in the game tree. */
  case class Node(state: State, lastMove: Option[Move], children: LazyList[Node])

  /** The game that the tree obeys. */
  def game: Game[State, Move]

  /** Find the best move from the given node. */
  def bestMove(node: Node): Option[Move]

  /** Create a game tree from the given state. */
  final def createTree(state: State): Node =
    def rec(state: State, lastMove: Option[Move]): Node =
      val children = 
        if game.gameOver(state) then
          LazyList.empty[Node]
        else
          game.possibleMoves(state).map: move =>
            rec(game.makeMove(state, move), Some(move))
      Node(state, lastMove, children)
    rec(state, None)
end GameTree
