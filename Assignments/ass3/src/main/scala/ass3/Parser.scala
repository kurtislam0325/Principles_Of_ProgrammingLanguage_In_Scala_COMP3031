package ass3

trait Parser:
  self: LispImpl =>
  def string2lisp(s: String): Data =
    val it = new LispTokenizer(s)

    def parseExpr(token: String): Data =
      if token == "(" then parseList
      else if token == ")" then throw ParseError("unmatched parenthesis")
      else if token.charAt(0).isDigit then Data.IntLit(token.toInt)
      else if token.charAt(0) == '\"' && token.charAt(token.length()-1) == '\"' then Data.StrLit(token.substring(1, token.length - 1))
      else Sym(token)

    def parseList: DataList =
      val token = it.next
      if token == ")" then DataList(Nil) else DataList(parseExpr(token) :: parseList.xs)

    parseExpr(it.next)

  def lisp2string(x: Value): String = x match
    case Value.IntLit(n) => n.toString
    case Value.StrLit(s) => s
    case ValueList(xs) => xs.map(lisp2string).mkString("(", " ", ")")
    case _ => x.toString
