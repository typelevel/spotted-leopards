package leopards

given Monad[Option], Traverse[Option] {
  def (a: A) pure[A]: Option[A] = Some(a)
  def (fa: Option[A]) flatMap[A, B](f: A => Option[B]): Option[B] =
    fa.flatMap(f)
  def (fa: Option[A]) foldLeft[A, B](b: B)(f: (B, A) => B): B =
    fa.foldLeft(b)(f)
  def (fa: Option[A]) traverse[G[_], A, B](f: A => G[B]) (given G: Applicative[G]): G[Option[B]] =
    fa match {
      case None => G.pure(None)
      case Some(a) => f(a).map(Some(_))
    }

}
  
