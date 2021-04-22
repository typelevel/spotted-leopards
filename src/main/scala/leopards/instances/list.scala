package leopards

given Monad[List] with Traverse[List] with
  def pure[A](a: A) = List(a)
  extension[A](fa: List[A])
    def flatMap[B](f: A => List[B]) = fa.flatMap(f)
    def traverse[G[_], B](f: A => G[B])(using G: Applicative[G]): G[List[B]] =
      fa.foldRight(G.pure(Nil: List[B]))((a, acc) => f(a).map2(acc, _ :: _))
    def foldLeft[B](b: B)(f: (B, A) => B): B = fa.foldLeft(b)(f)
    def foldRight[B](b: B)(f: (A, B) => B): B = fa.foldRight(b)(f)
