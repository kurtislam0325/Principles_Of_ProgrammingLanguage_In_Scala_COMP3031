package ass2

import tictactoe.*, Mark.*

class GameTreeTest extends munit.FunSuite:
  object TestGameTree extends GameTree[Board, Int]:
    val game = TicTacToe

    // Not needed for this test.
    def bestMove(node: Node): Option[Int] = ???

    /** Calculate the size of the tree. This is very inefficient.
     *  It is only used for testing purposes. DO NOT use in production code.
     */
    def size(node: Node): Int =
      1 + node.children.iterator.map(size).sum
  
  import TestGameTree.*
  
  test("createTree should create a tree with one node for a full board"):
    // ┌───┬───┬───┐
    // │ X │ X │ X │
    // ├───┼───┼───┤
    // │ X │ X │ X │
    // ├───┼───┼───┤
    // │ X │ X │ X │
    // └───┴───┴───┘
    // Because the board is full. Note that it is not possible in a real game.
    val state = Board(Vector.fill(9)(Some(X)))
    assert(size(createTree(state)) == 1)

  test("createTree should create a tree with one node for the given board"):
    // ┌───┬───┬───┐
    // │ X │ O │ X │
    // ├───┼───┼───┤
    // │ O │ X │ O │
    // ├───┼───┼───┤
    // │ X │ O │   │
    // └───┴───┴───┘
    // Note that X has already won.
    val state = Board(Vector(
      Some(X), Some(O), Some(X),
      Some(O), Some(X), Some(O),
      Some(X), Some(O), None))
    assert(size(createTree(state)) == 1)

  test("createTree should create a tree with 8 nodes for the given board"):
    // ┌───┬───┬───┐
    // │ X │ O │ X │
    // ├───┼───┼───┤
    // │ O │ X │ O │
    // ├───┼───┼───┤
    // │   │   │   │
    // └───┴───┴───┘
    // The given state makes up one node.
    // If X plays at 6, then X wins immediately. --> one node
    // If X plays at 8, then X wins immediately. --> one node
    // If X plays at 7 (one node), then O can either:
    // - play at 6 (one node), then X can only play at 8 (one node);
    // - play at 8 (one node), then X can only play at 6 (one node).
    // Therefore, there are 8 nodes in total.
    val state = Board(Vector(
      Some(X), Some(O), Some(X),
      Some(O), Some(X), Some(O),
      None, None, None))
    val tree = createTree(state)
    println(size(tree))
    assert(size(tree) == 8)
end GameTreeTest
