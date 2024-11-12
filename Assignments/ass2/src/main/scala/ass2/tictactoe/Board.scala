package ass2
package tictactoe

import Mark.*

case class Board(cells: Vector[Option[Mark]]):
  /** Check if every cell is occupied by a mark. */
  def isFull: Boolean = ???

  /** Get the mark at the given index. */
  def apply(index: Int): Option[Mark] = ???

  /** Update the mark at the given index. */
  def updated(index: Int, next: Mark): Board =  ???

  override def toString: String =
    cells.grouped(3).map:
      _.map:
        case Some(X) => "X"
        case Some(O) => "O"
        case None => " "
      .mkString("│ ", " │ ", " │")
    .mkString(
      start = "┌───┬───┬───┐\n",
      sep = "\n├───┼───┼───┤\n",
      end = "\n└───┴───┴───┘")

object Board:
  /** Initialize an empty board that contains no marks. */
  def empty: Board = ???
