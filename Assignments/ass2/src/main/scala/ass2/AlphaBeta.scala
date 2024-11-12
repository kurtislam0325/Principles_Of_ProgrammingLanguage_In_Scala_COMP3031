 package ass2

import scala.annotation.tailrec

class AlphaBeta[State, Move](override val game: Game[State, Move]) extends GameTree[State, Move]:
  override def bestMove(node: Node): Option[Move] = ???
