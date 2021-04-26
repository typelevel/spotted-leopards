package leopards.syntax

import leopards.ApplicativeError

extension [E](e: E)
  def raiseError[F[_], A](using F: ApplicativeError[F, E]): F[A] = F.raiseError[A](e)