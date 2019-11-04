package leopards

trait Monad[F[_]] extends FlatMap[F] with Applicative[F] {
  override def [A, B] (fa: F[A]) map(f: A => B): F[B] = fa.flatMap(f andThen pure)
}
