package nmunch

import scalaz._
import scalaz.stream._
import scalaz.concurrent._

object Main extends TaskApp {
  override def run(args: ImmutableArray[String]): Task[Unit] = Task.delay {
    args foreach println
  }
}
