package leopards

import scala.annotation.alpha

trait Apply[F[_]] extends Functor[F] with Semigroupal[F] {
  @alpha("ap") def [A, B] (ff: F[A => B]) <*> (fa: F[A]): F[B]

  def [A, B, Z] (fa: F[A]) map2(fb: F[B])(f: (A, B) => Z): F[Z] =
    fa.product(fb).map(f.tupled)

  override def [A, B] (fa: F[A]) product(fb: F[B]): F[(A, B)] =
    fa.map(a => (b: B) => (a, b)) <*> fb

  @alpha("productL") def [A, B] (fa: F[A]) <* (fb: F[B]): F[A] =
    fa.map2(fb)((a, _) => a)

  @alpha("productR") def [A, B] (fa: F[A]) *> (fb: F[B]): F[B] =
    fa.map2(fb)((_, b) => b)
}
