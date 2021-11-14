package leopards

trait Applicative[F[_]] extends Apply[F]:
  def pure[A](a: A): F[A]
  def unit: F[Unit] = pure(())
