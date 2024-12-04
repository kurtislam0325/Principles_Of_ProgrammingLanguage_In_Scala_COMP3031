 package ass2

import scala.annotation.tailrec

class AlphaBeta[State, Move](override val game: Game[State, Move]) extends GameTree[State, Move]:
  override def bestMove(node: Node): Option[Move] = {
    // var num = 0

    // alpha: best value that the maximizing player can guarantee
    // beta: best value that the minimizing player can guarantee
    def miniMaxAlphaBeta(node: Node, alpha: Int, beta: Int): Int = {
      if game.gameOver(node.state) then
        return game.evaluate(node.state)     

      var newAlpha = alpha
      var newBeta = beta         

      if game.nextPlayer(node.state) == Game.Player.Max then
        var maxEval = Int.MinValue
        for child <- node.children do
          val eval = miniMaxAlphaBeta(child, newAlpha, newBeta)
          maxEval = Math.max(maxEval, eval)
          newAlpha = Math.max(newAlpha, eval) // Update alpha for the maximizing player

          if maxEval <= newAlpha then return maxEval
        maxEval        
      else
        var minEval = Int.MaxValue
        for child <- node.children do
          val eval = miniMaxAlphaBeta(child, newAlpha, newBeta)
          minEval = Math.min(minEval, eval)
          newBeta = Math.min(newBeta, eval) // Update beta for the minimizing player

          if minEval >= newBeta then return minEval
        minEval   
    }    

    // Evaluate the miniMaxAlphaBeta score for each child node and pick the best move
    val movesWithScores = node.children.map(child => child.lastMove -> miniMaxAlphaBeta(child, Int.MinValue, Int.MaxValue))
    movesWithScores.minByOption(_._2).map(_._1).flatten    
  }
