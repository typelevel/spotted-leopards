package leopards

implied ListMonad for Monad[List] {
  def (a: A) pure[A]: List[A] = List(a)
  def (fa: List[A]) flatMap[A, B](f: A => List[B]): List[B] =
    fa.flatMap(f)
}