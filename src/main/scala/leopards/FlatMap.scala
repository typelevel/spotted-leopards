package leopards

import scala.annotation.alpha

trait FlatMap[F[_]] extends Apply[F] {
  def [A, B] (fa: F[A]) flatMap(f: A => F[B]): F[B]
  def [A] (ffa: F[F[A]]) flatten: F[A] = ffa.flatMap(identity)
  @alpha("ap") override def [A, B] (ff: F[A => B]) <*> (fa: F[A]): F[B] =
    ff.flatMap(f => fa.map(f))
}
