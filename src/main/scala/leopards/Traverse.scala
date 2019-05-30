package leopards

trait Traverse[F[_]] extends Foldable[F] {
  def (fa: F[A]) traverse[G[_], A, B](f: A => G[B]) given Applicative[G]: G[F[B]]
  def (fa: F[G[A]]) sequence[G[_], A, B] given Applicative[G]: G[F[A]] =
    fa.traverse(identity)
}