package ass2

import Game.Player.*

class Minimax[State, Move](override val game: Game[State, Move]) extends GameTree[State, Move]:
    
  override def bestMove(node: Node): Option[Move] = ???
