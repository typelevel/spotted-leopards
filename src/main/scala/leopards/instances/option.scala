package leopards

given Monad[Option] with Traverse[Option] with
  def pure[A](a: A) = Some(a)
  extension[A](fa: Option[A])
    def flatMap[B](f: A => Option[B]) = fa.flatMap(f)
    def traverse[G[_], B](f: A => G[B])(using G: Applicative[G]): G[Option[B]] =
      fa.fold(G.pure(None: Option[B]))(a => f(a).map(Some(_)))
    def foldLeft[B](b: B)(f: (B, A) => B): B = fa.fold(b)(a => f(b, a))
    def foldRight[B](b: B)(f: (A, B) => B): B = fa.fold(b)(a => f(a, b))