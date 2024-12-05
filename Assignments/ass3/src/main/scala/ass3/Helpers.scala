package ass3

trait Helpers:
  self: LispImpl =>

  def mkLambda(ps: List[String], body: Data)(using env: Environment[Value])(using tydefs: Environment[List[String]]) =
    Lambda {       
      args => {
        // println(s"mkLambda args: ${args}")
        val vars = body match
          case DataList(xs) => Some(xs)
          case _ => None
                
        vars match {
          case None => eval(body)(using env.extendMulti(ps, args))
          case Some(xs) => {
            val argVarNames = ps.map(Data.Sym(_))
            val bodyVarNames = xs.filter(x => x match 
              case Sym(name) => true
              case _ => false
            )

            val common = argVarNames intersect bodyVarNames
            val commonIndicesSet = argVarNames.zipWithIndex.collect {
              case (item, index) if common.contains(item) => index
            }.toSet

            val forcedArgs = args.zipWithIndex.map {
              case (arg, index) if commonIndicesSet.contains(index) =>
                arg match {
                  case Value.ThunkUnevaluated(_, _) => arg.force
                  case Value.ThunkEvaluated(_, value) => value
                  case _ => throw new RuntimeException("Unexpected value type in Thunk evaluation.")
                }
              case (arg, _) => arg // If the arg is not in `common`, leave it unchanged
            }

            // println(s"line 38: $common")
            // println(s"line 39: \n $ps \n $forcedArgs")
            
            eval(body)(using env.extendMulti(ps, forcedArgs))
          } 
        }            
      }
    }

  def asList(x: Data): List[Data] = x match
    case DataList(xs) => xs
    case _ => throw SyntaxError("malformed list: " + x)

  def toName(s: String, upper: Boolean)(x: Data): String = x match
    case Sym(name) if name.head.isUpper == upper => name
    case _ => throw SyntaxError("malformed " + s + ": " + x)                         

  val paramName = toName("parameter", false)
  val fieldName = toName("field", false)
  val className = toName("class", true)
