package ass3

enum Data:
  case IntLit(n: Int)
  case StrLit(str: String)
  case Sym(sym: String)
  case DataList(xs: List[Data])

enum Value:
  case IntLit(n: Int)
  case StrLit(str: String)
  case Tuple(sym: Value, value: Value)
  case Object(name: Data, fields: Value.ValueList)
  case Quoted(e: Data)
  case ValueList(xs: List[Value])
  case Lambda(f: PartialFunction[List[Value], Value])
  case ThunkUnevaluated(symbol: Data, expr: () => Value)
  case ThunkEvaluated(symbol: Data, value: Value)

  def force: Value = this match {
    case ThunkEvaluated(symbol, value) => value
    case ThunkUnevaluated(symbol, expr) => {
      val result = expr()
      ThunkEvaluated(symbol, result)
    }
    case _ => throw new RuntimeException("force on wrong thing")
  }  

export Data.{Sym, DataList}
export Value.{Quoted, ValueList, Lambda}
