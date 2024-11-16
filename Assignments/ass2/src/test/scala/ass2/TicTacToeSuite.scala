// package ass2

// import Game.Player.*
// import tictactoe.*, Mark.*, TicTacToe.*

// class TicTacToeTest extends munit.FunSuite:
//   import TicTacToe._

//   test("Board should be initialized with empty cells"):
//     val board = Board.empty
//     assert(board.cells.forall(_.isEmpty))

//   test("Board should update correctly"):
//     val board = Board.empty.updated(0, X)
//     assertEquals(board(0), Some(X))

//   test("Board should be full when all cells are filled"):
//     val board = Board(Vector.fill(9)(Some(X)))
//     assert(board.isFull)

//   test("Next mark should be X if counts are equal"):
//     val state = Board(Vector(Some(X), Some(O), None, None, None, None, None, None, None))
//     assertEquals(nextPlayer(state), Max)

//   test("Next mark should be O if X count is greater than O count"):
//     val state = Board(Vector(Some(X), Some(X), None, None, None, None, None, None, None))
//     assertEquals(nextPlayer(state), Min)

//   test("Possible moves should return all empty cell indices"):
//     val state = Board(Vector(Some(X), Some(O), None, None, None, None, None, None, None))
//     assertEquals(possibleMoves(state), LazyList(2, 3, 4, 5, 6, 7, 8))

//   test("Make move should update the board correctly"):
//     val state = Board.empty
//     val newState = makeMove(state, 0)
//     assertEquals(newState(0), Some(X))

//   test("Game should be over when there is a winner"):
//     val state = Board(Vector(Some(X), Some(X), Some(X), None, None, None, None, None, None))
//     assert(gameOver(state))

//   test("Game should be over when the board is full"):
//     val state = Board(Vector.fill(9)(Some(X)))
//     assert(gameOver(state))

//   test("Evaluate should return positive value if three Xs are in a row"):
//     // ┌───┬───┬───┐
//     // │ X │ X │ X │
//     // ├───┼───┼───┤
//     // │   │   │   │
//     // ├───┼───┼───┤
//     // │   │   │   │
//     // └───┴───┴───┘
//     val state = Board(Vector(
//       Some(X), Some(X), Some(X),
//       None,    None,    None,
//       None,    None,    None))
//     assert(evaluate(state) > 0)

//   test("Evaluate should return positive value if three Xs are in a column"):
//     // ┌───┬───┬───┐
//     // │ X │   │   │
//     // ├───┼───┼───┤
//     // │ X │   │   │
//     // ├───┼───┼───┤
//     // │ X │   │   │
//     // └───┴───┴───┘
//     val state = Board(Vector(
//       Some(X), None, None,
//       Some(X), None, None,
//       Some(X), None, None))
//     assert(evaluate(state) > 0)

//   test("Evaluate should return negative value if three Os are in a row"):
//     // ┌───┬───┬───┐
//     // │ X │ O │ X │
//     // ├───┼───┼───┤
//     // │ O │ O │ O │
//     // ├───┼───┼───┤
//     // │   │ X │ X │
//     // └───┴───┴───┘
//     val state = Board(Vector(Some(O), Some(O), Some(O), None, None, None, None, None, None))
//     assert(evaluate(state) < 0)

//   test("Evaluate should return negative value if three Os are in a column"):
//     // ┌───┬───┬───┐
//     // │ X │ O │ X │
//     // ├───┼───┼───┤
//     // │   │ O │   │
//     // ├───┼───┼───┤
//     // │   │ O │ X │
//     // └───┴───┴───┘
//     val state = Board(Vector(
//       Some(X), Some(O), Some(X),
//       None,    Some(O), None,
//       None,    Some(O), Some(X)))
//     assert(evaluate(state) < 0)

//   test("Evaluate should return 0 if it is a tie"):
//     val board = Board(Vector(Some(X), Some(O), Some(X), Some(O), Some(X), Some(O), Some(O), Some(X), Some(O)))
//     val state = board
//     assertEquals(evaluate(state), 0)
