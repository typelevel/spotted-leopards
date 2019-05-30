package leopards

trait Foldable[F[_]] {
  def (fa: F[A]) foldLeft[A, B](b: B)(f: (B, A) => B): B
}
