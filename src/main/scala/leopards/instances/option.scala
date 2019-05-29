package leopards

implied OptionMonad for Monad[Option] {
  def (a: A) pure[A]: Option[A] = Some(a)
  def (fa: Option[A]) flatMap[A, B](f: A => Option[B]): Option[B] =
    fa.flatMap(f)
}
  