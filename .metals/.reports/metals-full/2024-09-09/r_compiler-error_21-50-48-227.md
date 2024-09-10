file://<WORKSPACE>/Labs/lab0/src/test/scala/lab0/ListsSuite.scala
### file%3A%2F%2F%2FUsers%2Fkurtislam%2FGitHub%2FPrinciples_Of_ProgrammingLanguage_COMP3031%2FLabs%2Flab0%2Fsrc%2Ftest%2Fscala%2Flab0%2FListsSuite.scala:11: error: `;` expected but `:` found
class ListsSuite extends munit.FunSuite:
                                       ^

occurred in the presentation compiler.

presentation compiler configuration:
Scala version: 2.12.19
Classpath:
<HOME>/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/org/scala-lang/scala-library/2.12.19/scala-library-2.12.19.jar [exists ]
Options:



action parameters:
uri: file://<WORKSPACE>/Labs/lab0/src/test/scala/lab0/ListsSuite.scala
text:
```scala
package lab0

/**
 * This class implements a munit test suite for the methods in object
 * `Lists` that need to be implemented as part of this assignment. A test
 * suite is simply a collection of individual tests for some specific
 * component of a program.
 *
 * To run this test suite, start "sbt" then run the "test" command.
 */
class ListsSuite extends munit.FunSuite:

  /**
   * Tests are written using the `test("description") { ... }` syntax
   * The most common way to implement a test body is using the method `assert`
   * which tests that its argument evaluates to `true`. So one of the simplest
   * successful tests is the following:
   */
  test("one plus one is two") {
    assert(1 + 1 == 2)
  }

  test("one plus one is three?") {
    assert(1 + 1 == 2) // This assertion fails! Go ahead and fix it.
  }

  /**
   * One problem with the previous (failing) test is that munit will
   * only tell you that a test failed, but it will not tell you what was
   * the reason for the failure. The output looks like this:
   *
   * {{{
   * ==> X lab1.ListSuite.one plus one is three?  0.007s munit.FailException: /tmp/lab0/src/test/scala/lab0/ListSuite.scala:26 assertion failed
   * 25:  test("one plus one is two") {
   * 26:      assert(1 + 1 == 3)
   * 27:  }
   * }}}
   *
   * This situation can be improved by using a assertEquals
   * (this is only possible in munit). So if you
   * run the next test, munit will show the following output:
   *
   * {{{
   * ==> X lab1.ListSuite.details why one plus one is not three  0.006s munit.FailException: /tmp/lab0/src/test/scala/lab0/ListSuite.scala:72
   * 71:  test("details why one plus one is not three") {
   * 72:      assertEquals(1 + 1, 3) // Fix me, please!
   * 73:  }
   * values are not the same
   * => Obtained
   * 3
   * => Diff (- obtained, + expected)
   * -3
   * +2
   * }}}
   *
   * We recommend to always use the assertEquals equality operator
   * when writing tests.
   */
  test("details why one plus one is not three") {
    assertEquals(1 + 1, 2) // Fix me, please!
  }

  /**
   * Exceptional behavior of a methods can be tested using a try/catch
   * and a failed assertion.
   *
   * In the following example, we test the fact that the method `intNotZero`
   * throws an `IllegalArgumentException` if its argument is `0`.
   */
   test("intNotZero throws an exception if its argument is 0") {
     try
       intNotZero(0)
       fail("No exception has been thrown")
     catch
       case e: IllegalArgumentException => ()
   }

   def intNotZero(x: Int): Int =
     if x == 0 then throw IllegalArgumentException("zero is not allowed")
     else x

  /**
   * Now we finally write some tests for the list functions that have to be
   * implemented for this assignment. We fist import all members of the
   * `List` object.
   */
  import Lists.*


  /**
   * We only provide two very basic tests for you. Write more tests to make
   * sure your `sum` and `max` methods work as expected.
   *
   * In particular, write tests for corner cases: negative numbers, zeros,
   * empty lists, lists with repeated elements, etc.
   *
   * It is allowed to have multiple `assert` statements inside one test,
   * however it is recommended to write an individual `test` statement for
   * every tested aspect of a method.
   */
  test("sum of a few numbers") {
    assert(sum(List(1, 2, 0)) == 3)
  }

  test("max of a few numbers") {
    assert(max(List(3, 7, 2)) == 7)
  }
  test("max of a few numbers") {
    assert(max(List(-2, -4, -)) == 7)
  }

  import scala.concurrent.duration.*
  override val munitTimeout = 1.seconds

```



#### Error stacktrace:

```
scala.meta.internal.parsers.Reporter.syntaxError(Reporter.scala:16)
	scala.meta.internal.parsers.Reporter.syntaxError$(Reporter.scala:16)
	scala.meta.internal.parsers.Reporter$$anon$1.syntaxError(Reporter.scala:22)
	scala.meta.internal.parsers.Reporter.syntaxError(Reporter.scala:17)
	scala.meta.internal.parsers.Reporter.syntaxError$(Reporter.scala:17)
	scala.meta.internal.parsers.Reporter$$anon$1.syntaxError(Reporter.scala:22)
	scala.meta.internal.parsers.ScalametaParser.syntaxErrorExpected(ScalametaParser.scala:394)
	scala.meta.internal.parsers.ScalametaParser.acceptStatSep(ScalametaParser.scala:450)
	scala.meta.internal.parsers.ScalametaParser.acceptStatSepOpt(ScalametaParser.scala:452)
	scala.meta.internal.parsers.ScalametaParser.statSeqBuf(ScalametaParser.scala:4107)
	scala.meta.internal.parsers.ScalametaParser.$anonfun$statSeq$1(ScalametaParser.scala:4096)
	scala.meta.internal.parsers.ScalametaParser.$anonfun$statSeq$1$adapted(ScalametaParser.scala:4096)
	scala.meta.internal.parsers.ScalametaParser.scala$meta$internal$parsers$ScalametaParser$$listBy(ScalametaParser.scala:562)
	scala.meta.internal.parsers.ScalametaParser.statSeq(ScalametaParser.scala:4096)
	scala.meta.internal.parsers.ScalametaParser.bracelessPackageStats$1(ScalametaParser.scala:4285)
	scala.meta.internal.parsers.ScalametaParser.$anonfun$source$1(ScalametaParser.scala:4288)
	scala.meta.internal.parsers.ScalametaParser.atPos(ScalametaParser.scala:325)
	scala.meta.internal.parsers.ScalametaParser.autoPos(ScalametaParser.scala:369)
	scala.meta.internal.parsers.ScalametaParser.source(ScalametaParser.scala:4264)
	scala.meta.internal.parsers.ScalametaParser.entrypointSource(ScalametaParser.scala:4291)
	scala.meta.internal.parsers.ScalametaParser.parseSourceImpl(ScalametaParser.scala:119)
	scala.meta.internal.parsers.ScalametaParser.$anonfun$parseSource$1(ScalametaParser.scala:116)
	scala.meta.internal.parsers.ScalametaParser.parseRuleAfterBOF(ScalametaParser.scala:58)
	scala.meta.internal.parsers.ScalametaParser.parseRule(ScalametaParser.scala:53)
	scala.meta.internal.parsers.ScalametaParser.parseSource(ScalametaParser.scala:116)
	scala.meta.parsers.Parse$.$anonfun$parseSource$1(Parse.scala:30)
	scala.meta.parsers.Parse$$anon$1.apply(Parse.scala:37)
	scala.meta.parsers.Api$XtensionParseDialectInput.parse(Api.scala:22)
	scala.meta.internal.semanticdb.scalac.ParseOps$XtensionCompilationUnitSource.toSource(ParseOps.scala:15)
	scala.meta.internal.semanticdb.scalac.TextDocumentOps$XtensionCompilationUnitDocument.toTextDocument(TextDocumentOps.scala:161)
	scala.meta.internal.pc.SemanticdbTextDocumentProvider.textDocument(SemanticdbTextDocumentProvider.scala:54)
	scala.meta.internal.pc.ScalaPresentationCompiler.$anonfun$semanticdbTextDocument$1(ScalaPresentationCompiler.scala:469)
```
#### Short summary: 

file%3A%2F%2F%2FUsers%2Fkurtislam%2FGitHub%2FPrinciples_Of_ProgrammingLanguage_COMP3031%2FLabs%2Flab0%2Fsrc%2Ftest%2Fscala%2Flab0%2FListsSuite.scala:11: error: `;` expected but `:` found
class ListsSuite extends munit.FunSuite:
                                       ^