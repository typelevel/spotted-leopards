package leopards

trait Foldable[F[_]] {
  def [A, B] (fa: F[A]) foldLeft(b: B)(f: (B, A) => B): B
}
