package leopards

trait Functor[F[_]] {
  def (fa: F[A]) map[A, B](f: A => B): F[B]
  def (fa: F[A]) as[A, B](b: B): F[B] =
    fa.map(_ => b)
  def (fa: F[A]) void[A]: F[Unit] =
    fa.as(())
}