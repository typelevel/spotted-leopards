package leopards

trait Traverse[F[_]] extends Functor[F], Foldable[F]:
  extension [A](fa: F[A])
    def traverse[G[_], B](f: A => G[B])(using G: Applicative[G]): G[F[B]]
  extension [G[_], A](fga: F[G[A]])
    def sequence(using Applicative[G]): G[F[A]] = fga.traverse(identity)
