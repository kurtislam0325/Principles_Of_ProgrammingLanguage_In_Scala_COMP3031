#!/usr/bin/env -S scala-cli -S 3

import ass3.Lisp

Lisp.evaluate("(def fact (lambda (x) (if x (* x (fact (- x 1))) 1)) (fact 4))")

Lisp.evaluate("(car (cons 1 (cons 2 nil)))")
Lisp.evaluate("(cdr (cons 1 (cons 2 nil)))")
Lisp.evaluate("(null? (cons 1 (cons 2 nil)))")
Lisp.evaluate("(null? nil)")

Lisp.evaluate("(quote (+ x 1))")
