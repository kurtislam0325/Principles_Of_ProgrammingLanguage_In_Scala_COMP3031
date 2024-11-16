package ass2

import Game.Player.*

class Minimax[State, Move](override val game: Game[State, Move]) extends GameTree[State, Move]:
    
  override def bestMove(node: Node): Option[Move] = {
    // println("Minimax")
    // var num = 0
    def minimax(node: Node): Int = { 
      if game.gameOver(node.state) then
        return game.evaluate(node.state)      

      // num = num + 1
      // println(node.lastMove)
      // println(node.state)             
      // println(node.children)

      if game.nextPlayer(node.state) == Game.Player.Max then
        node.children.map(child => minimax(child)).max
      else
        node.children.map(child => minimax(child)).min
    }    

    // Evaluate the minimax score for each child node and pick the best move
    val movesWithScores = node.children.map(child => child.lastMove -> minimax(child))
    movesWithScores.minByOption(_._2).map(_._1).flatten
  }
