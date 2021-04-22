package leopards

import scala.annotation.alpha

trait Semigroup[A]:
  extension (x: A)
    @alpha("combine")
    def |+| (y: A): A

object Semigroup:
  given Semigroup[Int] with
    extension (x: Int)
      def |+| (y: Int) = x + y