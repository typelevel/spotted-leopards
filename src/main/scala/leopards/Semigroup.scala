package leopards

import scala.annotation.alpha

trait Semigroup[A] {
  @alpha("combine") def (x: A) |+| (y: A): A
}