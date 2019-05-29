package leopards

trait Monad[F[_]] extends FlatMap[F] with Applicative[F] {
  override def (fa: F[A]) map[A, B](f: A => B): F[B] = fa.flatMap(f andThen pure)
}