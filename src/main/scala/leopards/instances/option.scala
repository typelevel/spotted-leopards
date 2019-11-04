package leopards

given Monad[Option], Traverse[Option] {
  def [A] (a: A) pure: Option[A] = Some(a)
  def [A, B] (fa: Option[A]) flatMap(f: A => Option[B]): Option[B] =
    fa.flatMap(f)
  def [A, B] (fa: Option[A]) foldLeft(b: B)(f: (B, A) => B): B =
    fa.foldLeft(b)(f)
  def [G[_], A, B] (fa: Option[A]) traverse(f: A => G[B]) (given G: Applicative[G]): G[Option[B]] =
    fa match {
      case None => G.pure(None)
      case Some(a) => f(a).map(Some(_))
    }
}

