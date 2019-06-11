package leopards

import scala.annotation.alpha

trait FlatMap[F[_]] extends Apply[F] {
  def (fa: F[A]) flatMap[A, B](f: A => F[B]): F[B]
  def (ffa: F[F[A]]) flatten[A]: F[A] = ffa.flatMap(identity)
  @alpha("ap") override def (ff: F[A => B]) <*>[A, B](fa: F[A]): F[B] =
    ff.flatMap(f => fa.map(f))
}
