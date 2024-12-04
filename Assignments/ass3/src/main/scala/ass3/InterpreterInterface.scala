package ass3

trait InterpreterInterface:
  self: LispImpl =>

  def eval(x: Data)(using env: Environment[Value])(using tydefs: Environment[List[String]]): Value
  def evaluate(x: Data): Value
  def evaluate(s: String): String
