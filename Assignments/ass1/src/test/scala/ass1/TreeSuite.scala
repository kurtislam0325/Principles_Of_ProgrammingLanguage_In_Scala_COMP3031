package ass1

/**
 * This class is a test suite for the methods in object Tree.
 *
 * To run this test suite, start "sbt" then run the "test" command.
 */
class TreeSuite extends munit.FunSuite:
  import Tree.Path

  extension (s: String)
    def toPath: Path[String] =
      if s.isEmpty then Path("" :: Nil)
      else Path(s.split('/').toList)

  trait TestTree:
    val t_1 = Tree[String, Int]("")      
      .updated("foo/a".toPath, 1)
      .updated("foo/b".toPath, 2)
      .updated("bar/a".toPath, 11)
      .updated("bar/b".toPath, 12)    

    val t_2 = Tree[String, Int]("")      
      .updated("foo/a".toPath)
      .updated("foo/b".toPath)
      .updated("bar/a".toPath)
      .updated("bar/b".toPath)         

    val t_3 = t_2.updated("foo/b/c/d".toPath)
    val t_4 = t_1.updated("foo/b/c/d".toPath, 42)

    // println(s"t_1\n ${t_1.display}")
    println(s"t_2\n${t_2.display}")    
    // println(s"t_3\n ${t_3.display}")
    println(s"t_4\n${t_4.display}")


  test("Sample tree") {
    new TestTree:
      assertEquals(t_1.toString, "Node(,List(Node(foo,List(Leaf(a,1), Leaf(b,2))), Node(bar,List(Leaf(a,11), Leaf(b,12)))))")
  }

  // test("Tree.get") {
  //   new TestTree:
  //     assertEquals(t_4.get(Path.empty).toString, "Some(Left(List(foo, bar)))")
  //     assertEquals(t_4.get(Path.empty).toString, "Some(Left(List(foo, bar)))")
  //     assert(t_4.get(Path(List("foo", "b", "c", "d"))) == Some(Right(42)))
  //     assert(t_4.get(Path(List("bar", "a"))) == Some(Right(11)))
  //     assert(t_4.get(Path(List("bar"))) == Some(Left(List("a", "b"))))
  //     assert(t_4.get(Path(List("baz"))) == None)      
  // }

  // test("Tree.updated") {
  //   new TestTree:
  //     assertEquals(t_1.updated("foo/b/c/d".toPath, 42).toString, "Node(,List(Node(foo,List(Leaf(a,1), Node(b,List(Node(c,List(Leaf(d,42))))))), Node(bar,List(Leaf(a,11), Leaf(b,12)))))")
  // }

  // test("Tree.contains") {
  //   new TestTree:
  //     assertEquals(t_4.contains("foo/b/c".toPath), true)
  //     assertEquals(t_4.contains("foo/b/c/e".toPath), false)
  // }

  // val expectedFlatten = List(
  //   (Path(List("", "foo", "a")), 1),
  //   (Path(List("", "foo", "b", "c", "d")), 42),
  //   (Path(List("", "bar", "a")), 11),
  //   (Path(List("", "bar", "b")), 12)
  // )

  // test("Tree.flatten") {
  //   new TestTree:
  //     assert(t_4.flatten == expectedFlatten)
  // }


  

  import scala.concurrent.duration.*
  override val munitTimeout = 10.seconds
