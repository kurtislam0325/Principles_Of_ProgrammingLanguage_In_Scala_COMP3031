package ass3

enum LazyValue:
  case Unevaluated(expr: () => Value)
  case Evaluated(value: Value)

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

export Data.{Sym, DataList}
export Value.{Quoted, ValueList, Lambda}
