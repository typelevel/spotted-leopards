package leopards

trait Applicative[F[_]] extends Apply[F] {
  def (a: A) pure[A]: F[A]
}