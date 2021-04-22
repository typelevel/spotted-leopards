package leopards

opaque type OptionT[F[_], A] = F[Option[A]]

object OptionT:
  def apply[F[_], A](o: F[Option[A]]): OptionT[F, A] = o

  def fromOption[F[_], A](oa: Option[A])(using F: Applicative[F]): OptionT[F, A] =
    apply(F.pure(oa))

  def liftF[F[_], A](fa: F[A])(using Functor[F]): OptionT[F, A] =
    apply(fa.map(Some(_)))

  extension [F[_], A](fa: OptionT[F, A])
    def value: F[Option[A]] = fa

  given [F[_]](using F: Functor[F]): Functor[[X] =>> OptionT[F, X]] =
    new OptionTFunctor[F] {}

  private trait OptionTFunctor[F[_]](using F: Functor[F]) extends Functor[[X] =>> OptionT[F, X]]:
    extension [A](ota: OptionT[F, A])
      def map[B](f: A => B) = F.map(ota)(_.map(f))

  given [F[_]](using F: Monad[F]): Monad[[X] =>> OptionT[F, X]] =
    new OptionTFunctor[F] with Monad[[X] =>> OptionT[F, X]]:
      def pure[A](a: A) = F.pure(Some(a))
      extension [A](ota: OptionT[F, A])
        def flatMap[B](f: A => OptionT[F, B]): OptionT[F, B] =
          F.flatMap(ota)(oa => oa.fold(F.pure(None))(f))
