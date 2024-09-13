file://<WORKSPACE>/Labs/lab1/src/test/scala/lab1/RecursionSuite.scala
### file%3A%2F%2F%2FUsers%2Fkurtislam%2FGitHub%2FPrinciples_Of_ProgrammingLanguage_COMP3031%2FLabs%2Flab1%2Fsrc%2Ftest%2Fscala%2Flab1%2FRecursionSuite.scala:3: error: `;` expected but `:` found
class RecursionSuite extends munit.FunSuite:
                                           ^

occurred in the presentation compiler.

presentation compiler configuration:
Scala version: 2.12.19
Classpath:
<HOME>/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/org/scala-lang/scala-library/2.12.19/scala-library-2.12.19.jar [exists ]
Options:



action parameters:
uri: file://<WORKSPACE>/Labs/lab1/src/test/scala/lab1/RecursionSuite.scala
text:
```scala
package lab1

class RecursionSuite extends munit.FunSuite:
  import Recursion.*

  // ------ balance tests -----------------------------------------------------

  test("balance: '(if (zero? x) max (/ 1 x))' is balanced") {
    assert(balance("(if (zero? x) max (/ 1 x))".toList))
  }

  test("balance: 'I told him ...' is balanced") {
    assert(balance("I told him (that it's not (yet) done).\n(But he wasn't listening)".toList))
  }

  test("balance: ':-)' is unbalanced") {
    assert(!balance(":-)".toList))
  }

  test("balance: counting is not enough") {
    assert(!balance("())(".toList))
  }

  // ------ countChange tests -------------------------------------------------

  test("countChange: example given in the guide") {
    assertEquals(countChange(4,List(1,2)), 3)
  }

  test("countChange: sorted HKD") {
    assertEquals(countChange(300,List(5,10,20,50,100,200,500)), 1022)
  }

  test("countChange: no pennies") {
    assertEquals(countChange(301,List(5,10,20,50,100,200,500)), 0)
  }

  test("countChange: unsorted HKD") {
    assertEquals(countChange(300,List(500,5,50,100,20,200,10)), 1022)
  }

  // ------ pascal tests ------------------------------------------------------

  test("")

  test("pascal: col=0,row=2") {
    assertEquals(pascal(0, 2), 1)
  }

  test("pascal: col=1,row=2") {
    assertEquals(pascal(1, 2), 2)
  }

  test("pascal: col=1,row=3") {
    assertEquals(pascal(1, 3), 3)
  }

  test("pascal: col=3,row=8") {
    assertEquals(pascal(3, 8), 56)
  }

  import scala.concurrent.duration.*
  override val munitTimeout = 10.seconds

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

file%3A%2F%2F%2FUsers%2Fkurtislam%2FGitHub%2FPrinciples_Of_ProgrammingLanguage_COMP3031%2FLabs%2Flab1%2Fsrc%2Ftest%2Fscala%2Flab1%2FRecursionSuite.scala:3: error: `;` expected but `:` found
class RecursionSuite extends munit.FunSuite:
                                           ^