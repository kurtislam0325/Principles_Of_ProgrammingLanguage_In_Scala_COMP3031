package ass2
package tictactoe

import Mark.*

case class Board(cells: Vector[Option[Mark]]):
  /** Check if every cell is occupied by a mark. */
  def isFull: Boolean = {
    cells.forall(_ != None)
  }

  /** Get the mark at the given index. */
  def apply(index: Int): Option[Mark] = {
    cells.apply(index)
  }

  /** Update the mark at the given index. */
  def updated(index: Int, next: Mark): Board =  {
    Board(cells.updated(index, Some(next)))
  }

  override def toString: String =
    cells.grouped(3).map:
      _.map:
        case Some(X) => "X"
        case Some(O) => "O"
        case None => " "
      .mkString("│ ", " │ ", " │")
    .mkString(
      start = "\n┌───┬───┬───┐\n",
      sep = "\n├───┼───┼───┤\n",
      end = "\n└───┴───┴───┘\n")

object Board:
  /** Initialize an empty board that contains no marks. */
  def empty: Board = {
    Board(Vector.fill(9)(None))
  }
