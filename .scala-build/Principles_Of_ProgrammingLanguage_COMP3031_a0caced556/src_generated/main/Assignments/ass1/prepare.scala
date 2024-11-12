package Assignments.ass1


final class prepare$_ {
def args = prepare_sc.args$
def scriptPath = """Assignments/ass1/prepare.sc"""
/*<script>*/
import scala.annotation.tailrec
import scala.util.Success
import scala.util.Failure
import java.io.{File, FilenameFilter, PrintWriter}
import scala.io.Source
import scala.util.Using

object Preparer:
  val I = """/\* BEGIN \*/(.|\n)*?/\* END \*/""".r
  val R = """/\* BEGIN REMOVAL \*/(.|\n)*?/\* END REMOVAL \*/""".r
  val isScalaFile: FilenameFilter = (_, name) => name.endsWith(".scala")

  def main(args: Array[String]): Unit =
    new File("./src/main/scala/ass1").listFiles(isScalaFile).foreach(prepare)
    new File("./src/test/scala/ass1").listFiles(isScalaFile).foreach(prepare)
  
  def prepare(file: File): Unit =
    Using(Source.fromFile(file)): source =>
      val content = remove(I.replaceAllIn(source.getLines().mkString("\n"), "???"), 0, "")
      Using(new PrintWriter(file)): writer =>
        writer.write(content)
        if !content.endsWith("\n") then writer.write("\n")
      match
        case Failure(error) => 
          println(s"Error writing to file ${file.getName}: $error")
        case Success(_) =>
          println(s"Successfully processed file: ${file.getName}")
    .getOrElse:
      println(s"Error reading file: ${file.getName}")

  val BEGIN_MARK = "/* BEGIN REMOVAL */"
  val END_MARK = "/* END REMOVAL */"

  @tailrec def remove(source: String, at: Int, acc: String): String =
    if at >= source.length then acc else
      val begin = source.indexOf(BEGIN_MARK, at)
      if begin == -1 then acc + source.substring(at)
      else
        val end = source.indexOf(END_MARK, begin + BEGIN_MARK.length)
        if end == -1 then acc + source.substring(at)
        else remove(source, end + END_MARK.length, acc + source.substring(at, begin))
end Preparer

/*</script>*/ /*<generated>*//*</generated>*/
}

object prepare_sc {
  private var args$opt0 = Option.empty[Array[String]]
  def args$set(args: Array[String]): Unit = {
    args$opt0 = Some(args)
  }
  def args$opt: Option[Array[String]] = args$opt0
  def args$: Array[String] = args$opt.getOrElse {
    sys.error("No arguments passed to this script")
  }

  lazy val script = new prepare$_

  def main(args: Array[String]): Unit = {
    args$set(args)
    val _ = script.hashCode() // hashCode to clear scalac warning about pure expression in statement position
  }
}

export prepare_sc.script as `prepare`

