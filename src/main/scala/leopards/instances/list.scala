package leopards

given Monad[List], Traverse[List] {
  def [A] (a: A) pure: List[A] = List(a)
  def [A, B] (fa: List[A]) flatMap(f: A => List[B]): List[B] =
    fa.flatMap(f)
  def [A, B] (fa: List[A]) foldLeft(b: B)(f: (B, A) => B): B =
    fa.foldLeft(b)(f)
  def [G[_], A, B] (fa: List[A]) traverse(f: A => G[B]) (given G: Applicative[G]): G[List[B]] =
    fa.foldRight(G.pure(List.empty[B]))((a, acc) => f(a).map2(acc)(_ :: _))
}
