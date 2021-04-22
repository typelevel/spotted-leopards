package leopards

import scala.annotation.alpha

trait Apply[F[_]] extends Functor[F], Semigroupal[F]:
  extension [A, B](ff: F[A => B])
    @alpha("ap")
    def <*> (fa: F[A]): F[B]

  extension [A](fa: F[A])
    def map2[B, Z](fb: F[B], f: (A, B) => Z): F[Z] =
      fa.product(fb).map(f.tupled)

    override def product[B](fb: F[B]): F[(A, B)] =
      fa.map(a => (b: B) => (a, b)) <*> fb

    @alpha("productL") def <*[B](fb: F[B]): F[A] =
      fa.map2(fb, (a, _) => a)

    @alpha("productR") def *>[B](fb: F[B]): F[B] =
      fa.map2(fb, (_, b) => b)
