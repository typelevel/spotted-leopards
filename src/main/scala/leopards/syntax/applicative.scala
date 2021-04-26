package leopards.syntax

import leopards.Applicative

extension [A](a: A)
  def pure[F[_]](using F: Applicative[F]): F[A] = F.pure(a)