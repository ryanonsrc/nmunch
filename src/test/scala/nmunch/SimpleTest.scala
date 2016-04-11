package nmunch

import org.scalatest.{FlatSpec, Matchers}

class SimpleTest extends FlatSpec with Matchers {
  it should "process a simple expression" in {
    val expression = "(+ (- 5 3) (* 4 2))"
    println(expression)
  }
}
