package ass3

trait Helpers:
  self: LispImpl =>

  def mkLambda(ps: List[String], body: Data)(using env: Environment[Value])(using tydefs: Environment[List[String]]) =
    Lambda { args => eval(body)(using env.extendMulti(ps, args)) }

  def asList(x: Data): List[Data] = x match
    case DataList(xs) => xs
    case _ => throw SyntaxError("malformed list: " + x)

  def toName(s: String, upper: Boolean)(x: Data): String = x match
    case Sym(name) if name.head.isUpper == upper => name
    case _ => throw SyntaxError("malformed " + s + ": " + x)

  def force(value: LazyValue): LazyValue = value match
    case LazyValue.Evaluated(value) => LazyValue.Evaluated(value)
    case LazyValue.Unevaluated(expr) =>
      val result = expr()             
      LazyValue.Evaluated(result)                              

  val paramName = toName("parameter", false)
  val fieldName = toName("field", false)
  val className = toName("class", true)
