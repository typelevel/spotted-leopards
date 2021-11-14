package leopards

import scala.annotation.alpha

trait FlatMap[F[_]] extends Apply[F]:
  extension [A](fa: F[A])
    def flatMap[B](f: A => F[B]): F[B]

  extension [A, B](ff: F[A => B])
    override def <*>(fa: F[A]): F[B] =
      ff.flatMap(f => fa.map(f))

  extension [A](ffa: F[F[A]])
    def flatten: F[A] = ffa.flatMap(identity)
