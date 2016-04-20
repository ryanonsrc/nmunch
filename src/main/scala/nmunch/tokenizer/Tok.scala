package nmunch.tokenizer

import scalaz._
import scalaz.stream._
import scalaz.concurrent._

object Tok {

  def in(src: Process[Task, Char]) : Process[Task, Token] =
    src.chunkBy(_.isDigit).map(_.foldLeft("")(_ + _.toString)).map(t => Token.unapply(t)).collect {
      case Some(t) => t
    }

}

object Token {
  type Matcher = String => Option[Token]
  val matchers = List[Matcher](`(`, `)`, PLUS, MINUS, `*`, `/`, int, junk)

  def unapply(str: String) : Option[Token] = matchers.map(_(str))
}

sealed abstract class Token(val str: String) extends Token.Matcher {
  def apply(s: String) : Option[Token] = Some(this).filter(str == s)
}

sealed trait Operator
sealed trait Operand
sealed trait Grouping

case object `(` extends Token("(") with Grouping
case object `)` extends Token(")") with Grouping
case object PLUS  extends Token("+") with Operator
case object MINUS  extends Token("-") with Operator
case object `*`  extends Token("*") with Operator
case object `/`  extends Token("*") with Operator
case class int(i: Int) extends Token(i.toString) with Operand
object int extends Token.Matcher {
  def apply(s: String) : Option[Token] =
    Some(s.forall(_.isDigit)).filter(identity).map(_ => int(s.toInt))
}
case class junk(str: String) extends Token(str)
object junk extends Token.Matcher {
  def apply(s: String) : Option[Token] = Some(junk(s)).filterNot(_ => s.exists(_.isWhitespace))
}
