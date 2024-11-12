package ass2
package tictactoe

enum Mark:
  case X, O

  /* Get the opposite mark. */
  def unary_~ : Mark = this match
    case X => O
    case O => X

  def player: Game.Player = this match
    case X => Game.Player.Max
    case O => Game.Player.Min
