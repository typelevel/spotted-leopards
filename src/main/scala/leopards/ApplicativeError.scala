package leopards

trait ApplicativeError[F[_], E] extends Applicative[F]:
  def raiseError[A](e: E): F[A]
  extension [A](fa: F[A])
    def handleError(f: Throwable => A): F[A]