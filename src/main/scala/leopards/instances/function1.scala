package leopards

given [X]: Monad[[A] =>> X => A] with
  def pure[A](a: A): X => A = _ => a
  extension [A](fa: X => A)
    def flatMap[B](f: A => X => B) =
      x => f(fa(x))(x)