package leopards

opaque type Kleisli[F[_], A, B] = A => F[B]

extension [F[_], A, B](k: Kleisli[F, A, B])
  def apply(a: A): F[B] = k(a)

object Kleisli:
  def apply[F[_], A, B](f: A => F[B]): Kleisli[F, A, B] = f

given [F[_], A](using F: Monad[F]): Monad[[B] =>> Kleisli[F, A, B]] with
  def pure[B](b: B) = Kleisli(_ => F.pure(b))
  extension[B](k: Kleisli[F, A, B])
    def flatMap[C](f: B => Kleisli[F, A, C]) =
      a => k(a).flatMap(b => f(b)(a))
