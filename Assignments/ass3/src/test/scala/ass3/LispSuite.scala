package ass3

/**
 * This class is a test suite for the methods in object Lisp.
 *
 * To run this test suite, start "sbt" then run the "test" command.
 */
class LispSuite extends munit.FunSuite:
  import Lisp.evaluate

  // test("Normal Functions"):
    // DataList(List(Sym(def), Sym(x), DataList(List(Sym(lambda), DataList(List(Sym(arg))), DataList(List(Sym(*), Sym(arg), IntLit(2))))), DataList(List(Sym(x), IntLit(3)))))
    // assertEquals(evaluate("(def x (lambda (arg) (* arg 2)) (x 3))"), "6")
    // DataList(List(Sym(class), DataList(List(Sym(Pair), Sym(x), Sym(y))), DataList(List(Sym(val), Sym(p), DataList(List(Sym(Pair), StrLit(zero), IntLit(0))), DataList(List(Sym(sel), Sym(p), Sym(x)))))))
    // assertEquals(evaluate("(class (Pair x y) (val p (Pair \"zero\" 0) (sel p x))))))"), "zero")
    // assertEquals(evaluate("(def p 2 (sel p y))"), "0")

  test("Field selection 1"):
    assertEquals(evaluate("(class (Pair x y) (val p (Pair \"zero\" 0) (sel p x))))))"), "zero")
    assertEquals(evaluate("(class (Pair x y) (val p (Pair \"zero\" 0) (sel p y))))))"), "0")

  test("Field selection 2"):
    assertEquals(evaluate("(class (Pair x y) (class (Riap x y) (def f (lambda (p) (sel p x)) (val p (Pair 2 3) (val r (Riap 3 2) (* (f p) (f r)))))))"), "6")

  test("Pattern matching 1"):
    // line 49: (List(Sym(Triple), Sym(x), Sym(y), Sym(z)),List(Sym(*), DataList(List(Sym(*), Sym(x), Sym(y))), Sym(z)))
    // line 60: List(x, y, z) List(IntLit(2), IntLit(3), IntLit(7))
    // line 61: IntLit(42)    
    assertEquals(evaluate("(class (Pair x y) (class (Triple x y z) (def prod (lambda (x) (case x ((Pair x y) (* x y)) ((Triple x y z) (* (* x y) z)))) (val x (Triple 2 3 7) (prod x)))))"), "42")

  test("Pattern matching 2"):
    // line 49: (Sym(x),List(Sym(cons), Sym(x), Sym(nil)))
    // line 60: List(x, y, z) List(IntLit(2), IntLit(3), IntLit(7))
    // line 61: ValueList(List(IntLit(2)))
    assertEquals(evaluate("(class (Pair x y) (class (Triple x y z) (def f (lambda (x) (case x ((Pair x y) (* x y)) (x (cons x nil)))) (val x (Triple 2 3 7) (sel (car (f x)) z)))))"), "7")

  test("Call-by-need: no need 1"):
    assertEquals(evaluate("(def zero (lambda (x) 0) (zero nani))"), "0")

  test("Call-by-need: no need 2"):
    assertEquals(evaluate("(class (Just x) (val x (Just nani) 0))"), "0")
