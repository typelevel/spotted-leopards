package leopards

extension [A](a: A)
  def pure[F[_]](using F: Applicative[F]): F[A] = F.pure(a)
  def raiseError[F[_], B](using F: ApplicativeError[F, A]): F[B] = F.raiseError[B](a)