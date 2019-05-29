package leopards

import scala.annotation.alpha

trait Apply[F[_]] extends Functor[F] with Semigroupal[F] {
  @alpha("ap") def (ff: F[A => B]) <*>[A, B] (fa: F[A]): F[B]

  def (fa: F[A]) map2[A, B, Z](fb: F[B])(f: (A, B) => Z): F[Z] =
    fa.product(fb).map(f.tupled)

  override def (fa: F[A]) product[A, B](fb: F[B]): F[(A, B)] =
    fa.map(a => (b: B) => (a, b)) <*> fb

  @alpha("productL") def (fa: F[A]) <*[A, B] (fb: F[B]): F[A] =
    fa.map2(fb)((a, _) => a)

  @alpha("productR") def (fa: F[A]) *>[A, B] (fb: F[B]): F[B] =
    fa.map2(fb)((_, b) => b)
}