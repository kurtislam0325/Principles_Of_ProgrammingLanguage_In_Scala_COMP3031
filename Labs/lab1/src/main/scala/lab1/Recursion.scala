package lab1

import scala.annotation.tailrec

object Recursion:

  def main(args: Array[String]): Unit =
    println("Pascal's Triangle")
    for row <- 0 to 10 do
      for col <- 0 to row do
        print(s"${pascal(col, row)} ")
      println()

  /**
   * Exercise 1
   */
  def pascal(c: Int, r: Int): Int = {
    if ((c == r) || (c == 0)) then 1
    else pascal(c - 1, r - 1) + pascal(c, r - 1)
  }

  /**
   * Exercise 2
   */
  def balance(chars: List[Char]): Boolean = {
    def balanceRecur(chars: List[Char], stack: List[Char]): Boolean = {
      if (chars.isEmpty) stack.isEmpty
      else if (chars.head == '(') balanceRecur(chars.tail, '(' :: stack)
      else if (chars.head == ')' && stack.nonEmpty && stack.head == '(') balanceRecur(chars.tail, stack.tail)
      else if (chars.head == ')') false
      else balanceRecur(chars.tail, stack)
    }

    balanceRecur(chars, List())
  }


  /**
   * Exercise 3
   */
  def countChange(money: Int, coins: List[Int]): Int = {
    def countChangeRecur(coins: List[Int], acc: Int): Int = {
      if (acc == 0) then 1
      else if (acc < 0 || coins.isEmpty) then 0
      else countChangeRecur(coins, acc - coins.head) + countChangeRecur(coins.tail, acc)
    }

    countChangeRecur(coins, money)
  }
