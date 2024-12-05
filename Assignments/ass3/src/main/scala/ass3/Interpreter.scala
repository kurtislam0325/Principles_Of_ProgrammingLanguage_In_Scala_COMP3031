package ass3

import scala.Tuple.Head
import ass3.Value.ThunkEvaluated

trait Interpreter:
  self: LispImpl =>

  def eval(x: Data)(using env: Environment[Value])(using tydefs: Environment[List[String]]): Value = {
    x match      
      case Data.IntLit(n) => Value.IntLit(n)
      case Data.StrLit(s) => Value.StrLit(s)
      case Sym(name) => {
        val classDef = tydefs.contains(name)

        classDef match
          case None => {
            val x = env.lookup(name)
            x match
              case Value.ThunkEvaluated(symbol, value) => value
              case _ => x            
          }
          case Some(value) => Value.ValueList(value.map(Value.StrLit(_)))
      }
      // Pattern Matching (case)
      case DataList(Sym("case") :: Sym(scrut) :: branches) => {
        val v = eval(Sym(scrut))
        val cases = branches.map {
          case DataList(List(data_1: Data, data_2: DataList)) => {
            data_1 match
              case Sym(sym) => (Sym(sym), data_2.xs)
              case DataList(xs) => (xs, data_2.xs)
              case x => throw SyntaxError(s"invalid case branch: ${x}")                    
          }
          case x => throw SyntaxError(s"invalid case branch: ${x}")          
        }        

        v match
          case Value.Object(name, fields) => { 
            // Match class definition
            val result = cases.find {
              case (param: List[Data], expr: List[Data]) => {
                val isSameType = param.head == name
            
                if isSameType == false then false 
                else if param.tail.length == fields.xs.length then true else throw ClassArityMismatch("wrong arity for class <name>")
              }
              case (param: Data.Sym, expr: List[Data]) => true
              case _ => false
            }
            result match
              case None => throw MatchError(s"match error on: ${scrut}")
              case Some(value) => {                
                val (symbols, values) = fields.xs.map {
                  case Value.Tuple(s, v) => {
                    val sym_string = s match 
                      case Value.StrLit(str) => str
                      case _ => throw new RuntimeException("Input does not match the expected structure line 45")      

                    val v_forced = v match 
                      case Value.ThunkUnevaluated(_, _) => {
                        v.force match
                          case Value.ThunkEvaluated(symbol, value) => value
                          case _ => throw new RuntimeException("Unexpected value type in Thunk evaluation.")
                      }
                      case Value.ThunkEvaluated(symbol, value) => value
                      case _ => throw new RuntimeException("Unexpected value type in Thunk evaluation.")
                    
                    (sym_string, v_forced)
                  }
                   case _ => throw new RuntimeException("Input does not match the expected structure line 48")
                }.unzip

                value.head match
                  case head :: tail => eval(DataList(value._2))(using env.extendMulti(symbols, values))  
                  case Data.Sym(str) => eval(DataList(value._2))(using env.extend(str, v))  
                  case _ => throw MatchError(s"match error on: ${scrut}")
              }            
          }
          case _ => throw MatchError(s"match error on: ${scrut}")
      }      
      case DataList(Sym("val") :: param :: expr :: rest :: Nil) => {
        eval(rest)(using env.extend(paramName(param), eval(expr)))
      }
      case DataList(Sym("def") :: param :: expr :: rest :: Nil) => {
        eval(rest)(using env.extendRec(paramName(param), env1 => eval(expr)(using env1)))
      }
      case DataList(Sym("if") :: cond :: thenpart :: elsepart :: Nil) =>
        eval(cond) match
          case Value.IntLit(0) => eval(elsepart)
          case _ => eval(thenpart)
      case DataList(Sym("quote") :: y :: Nil) => Quoted(y)
      case DataList(Sym("lambda") :: params :: body :: Nil) => mkLambda(asList(params).map(paramName), body)
      // Class Declaration (class)
      case DataList(Sym("class") :: DataList(Sym(name) :: fields) :: rest :: Nil) => {
        val class_name = className(Sym(name))
        val field_names = fields.map {
          case Sym(field) => fieldName(Sym(field))
          case _ => throw SyntaxError("Invalid field name")
        }

        eval(rest)(using env)(using tydefs.extend(class_name, field_names))
      }
      // Field selection (sel)      
      case DataList(Sym("sel") :: obj :: Sym(fieldName) :: Nil) => {
        eval(obj) match {
          case Value.Object(classType, fields) => {            
            val value = fields.xs.find { 
              case Value.Tuple(sym, value) => sym == Value.StrLit(fieldName)
              case _ => false         
            }

            value match                        
              case Some(Value.Tuple(sym, value)) => {
                value match {
                  case Value.ThunkUnevaluated(_, _) => {
                    value.force match
                      case Value.ThunkEvaluated(symbol, value) => value
                      case _ => throw new RuntimeException("Unexpected value type in Thunk evaluation.")
                  }
                  case Value.ThunkEvaluated(symbol, value) => value
                  case _ => throw new RuntimeException("Unexpected value type in Thunk evaluation.")
                }
              }
              case Some(_) => throw FieldError(s"class C has no field ${fieldName}")         
              case None => throw FieldError(s"class C has no field ${fieldName}")    
          }     
          case _ => throw SelError(s"selection from a non-object: ${obj}")                      
        }
      }            
      case DataList(operator :: operands) => {
        eval(operator) match {
          case Lambda(f) => {
            val isBuiltInFunc = try
              eval(operator)(using globalEnv)
              true
            catch
              case _: UndefinedSymbol => false

            val x = isBuiltInFunc match
              case true => operands.map(o => Value.ThunkEvaluated(o, eval(o)).force)
              case false => operands.map(o => Value.ThunkUnevaluated(o, () => eval(o)))

            f(x)           
          }
          case Value.ValueList(xs) => {
            val field_names = xs
            val values = operands.map(o => Value.ThunkUnevaluated(o, () => eval(o)))

            if (field_names.length != values.length) then throw ClassArityMismatch(s"wrong arity for class ${className}")                
            Value.Object(operator, Value.ValueList(field_names.zip(values).map {
              case (field_name, value) => Value.Tuple(field_name, value)
            }))
          }
          case x => throw AppError("application of a non-function: " + x + " to " + operands)
        }
      }
      case _ => ??? // impossible
  }

  def evaluate(x: Data): Value = eval(x)(using globalEnv)(using emptyEnvironment)

  def evaluate(s: String): String = lisp2string(evaluate(string2lisp(s)))