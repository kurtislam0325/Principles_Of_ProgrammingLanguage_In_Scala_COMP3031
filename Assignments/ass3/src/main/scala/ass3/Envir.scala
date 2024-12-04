package ass3

trait Envir:
  self: LispImpl =>

  abstract class Environment[T]:
    def lookup(n: String): T

    def extendRec(name: String, expr: Environment[T] => T): Environment[T] =
      new Environment[T]:
        def lookup(n: String): T =
          if n == name then expr(this) else Environment.this.lookup(n)

    def extend(name: String, v: T): Environment[T] = extendRec(name, _ => v)

    def extendMulti(ps: List[String], vs: List[T]): Environment[T] = (ps, vs) match
      case (List(), List()) => this
      case (p :: ps1, arg :: args1) => extend(p, arg).extendMulti(ps1, args1)
      case _ => throw FunArityMismatch("wrong number of arguments")

    def contains(n: String): Option[T] =
      try
        Some(lookup(n))   
      catch
        case _: UndefinedSymbol => None   

  def emptyEnvironment[T] =
    new Environment[T]:
      def lookup(n: String): T = throw UndefinedSymbol("undefined: " + n)

  val globalEnv =
    emptyEnvironment[Value]
    .extend("=", Lambda {
      case List(arg1, arg2) => Value.IntLit(if arg1 == arg2 then 1 else 0)})
    .extend("+", Lambda {
      case List(arg1: Value.IntLit, arg2: Value.IntLit) => Value.IntLit(arg1.n + arg2.n)
      case List(arg1: Value.StrLit, arg2: Value.StrLit) => Value.StrLit(arg1.str + arg2.str)})
    .extend("-", Lambda {
      case List(arg1: Value.IntLit, arg2: Value.IntLit) => Value.IntLit(arg1.n - arg2.n)})
    .extend("*", Lambda {
      case List(arg1: Value.IntLit, arg2: Value.IntLit) => Value.IntLit(arg1.n * arg2.n)})
    .extend("/", Lambda {
      case List(arg1: Value.IntLit, arg2: Value.IntLit) => Value.IntLit(arg1.n / arg2.n)})
    .extend("nil", ValueList(Nil))
    .extend("cons", Lambda {
      case List(arg1, arg2: ValueList) => ValueList(arg1 :: arg2.xs)})
    .extend("car", Lambda {
      case List(ValueList(x :: xs)) => x})
    .extend("cdr", Lambda {
      case List(ValueList(x :: xs)) => ValueList(xs)})
    .extend("null?", Lambda {
      case List(ValueList(Nil)) => Value.IntLit(1)
      case _ => Value.IntLit(0)})
