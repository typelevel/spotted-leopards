package leopards

implied for Monad[List], Traverse[List] {
  def (a: A) pure[A]: List[A] = List(a)
  def (fa: List[A]) flatMap[A, B](f: A => List[B]): List[B] =
    fa.flatMap(f)
  def (fa: List[A]) foldLeft[A, B](b: B)(f: (B, A) => B): B =
    fa.foldLeft(b)(f)
  def (fa: List[A]) traverse[G[_], A, B](f: A => G[B]) given (G: Applicative[G]): G[List[B]] =
    fa.foldRight(G.pure(List.empty[B]))((a, acc) => f(a).map2(acc)(_ :: _))
}