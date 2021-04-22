package leopards

trait Foldable[F[_]]:
  extension [A](fa: F[A])
    def foldLeft[B](b: B)(f: (B, A) => B): B
    def foldRight[B](b: B)(f: (A, B) => B): B
    def foldMap[B](f: A => B)(using B: Monoid[B]): B =
      foldRight(B.empty)((a, b) => f(a) |+| b)
