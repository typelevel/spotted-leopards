package leopards

trait Applicative[F[_]] extends Apply[F] {
  def [A] (a: A) pure: F[A]
}
