package leopards

trait Functor[F[_]]:
  extension [A](fa: F[A])
    def map[B](f: A => B): F[B]
    def as[B](b: B): F[B] = map(_ => b)
    def void: F[Unit] = as(())
  def lift[A, B](f: A => B): F[A] => F[B] =
    _.map(f)