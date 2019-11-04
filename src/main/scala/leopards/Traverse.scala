package leopards

trait Traverse[F[_]] extends Foldable[F] {
  def [G[_], A, B] (fa: F[A]) traverse(f: A => G[B]) (given Applicative[G]): G[F[B]]
  def [G[_], A, B] (fa: F[G[A]]) sequence (given Applicative[G]): G[F[A]] =
    fa.traverse(identity)
}
