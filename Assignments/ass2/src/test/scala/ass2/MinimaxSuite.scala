package ass2

import tictactoe.*, Mark.*

class MinimaxTest extends munit.FunSuite:
  val tree = new Minimax(TicTacToe)
  import tree.*

//   test("Minimax should choose the winning move"):
//     // ┌───┬───┬───┐
//     // │ O │ X │ X │
//     // ├───┼───┼───┤
//     // │ X │ O │   │
//     // ├───┼───┼───┤
//     // │   │   │   │
//     // └───┴───┴───┘
//     val state = Board(Vector(
//       Some(O), Some(X), Some(X),
//       Some(X), Some(O), None,
//       None, None, None))
//     val tree = createTree(state)
//     val move = bestMove(tree)
//     assertEquals(move, Some(8))    

//   test("Minimax should block the opponent's winning move"):
//     // ┌───┬───┬───┐
//     // │ O │   │   │
//     // ├───┼───┼───┤
//     // │ X │ X │   │
//     // ├───┼───┼───┤
//     // │   │   │   │
//     // └───┴───┴───┘
//     val state = Board(Vector(
//       Some(O), None, None,
//       Some(X), Some(X), None,
//       None, None, None))
//     val tree = createTree(state)
//     val move = bestMove(tree)
//     assertEquals(move, Some(5))

  test("Minimax should choose the optimal move"):
    // ┌───┬───┬───┐
    // │ X │ O │ X │
    // ├───┼───┼───┤
    // │ O │   │   │
    // ├───┼───┼───┤
    // │   │ X │   │
    // └───┴───┴───┘
    val state = Board(Vector(
      Some(X), Some(O), Some(X),
      Some(O), None, None,
      None, Some(X), None))
    val tree = createTree(state)
    println(tree.children)
    val move = bestMove(tree)
    assertEquals(move, Some(4))
end MinimaxTest
