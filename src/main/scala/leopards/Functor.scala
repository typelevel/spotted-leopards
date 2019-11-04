package leopards

trait Functor[F[_]] {
  def [A, B] (fa: F[A]) map(f: A => B): F[B]
  def [A, B] (fa: F[A]) as(b: B): F[B] =
    fa.map(_ => b)
  def [A] (fa: F[A]) void: F[Unit] =
    fa.as(())
}
