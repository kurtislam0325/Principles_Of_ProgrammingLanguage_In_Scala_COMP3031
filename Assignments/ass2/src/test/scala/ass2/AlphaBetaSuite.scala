package ass2

import tictactoe.*

class AlphaBetaTest extends munit.FunSuite:
  import TicTacToe.*, Mark.*

  val tree = new AlphaBeta(TicTacToe)
  import tree.*

  test("AlphaBeta should choose the winning move"):
    // ┌───┬───┬───┐
    // │ O │ X │ X │
    // ├───┼───┼───┤
    // │ X │ O │   │
    // ├───┼───┼───┤
    // │ X │ O │   │
    // └───┴───┴───┘
    val state = Board(Vector(
      Some(O), Some(X), Some(X),
      Some(X), Some(O), None,
      Some(X), Some(O), None))
    val tree = createTree(state)
    val move = bestMove(tree)
    assertEquals(move, Some(8))

  test("AlphaBeta should block the opponent's winning move"):
    // ┌───┬───┬───┐
    // │ O │   │   │
    // ├───┼───┼───┤
    // │ X │ X │ O │
    // ├───┼───┼───┤
    // │   │ X │   │
    // └───┴───┴───┘
    val state = Board(Vector(
      Some(O), None, None,
      Some(X), Some(X), Some(O),
      None, Some(X), None))
    val tree = createTree(state)
    val move = bestMove(tree)
    assertEquals(move, Some(1))
end AlphaBetaTest
