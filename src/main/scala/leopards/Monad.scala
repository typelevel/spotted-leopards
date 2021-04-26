package leopards

trait Monad[F[_]] extends FlatMap[F], Applicative[F]:
  extension [A](fa: F[A])
    override def map[B](f: A => B): F[B] = fa.flatMap(f andThen pure)

object Monad:
  def apply[F[_]](using m: Monad[F]) = m
