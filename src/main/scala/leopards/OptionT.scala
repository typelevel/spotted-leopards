package leopards

opaque type OptionT[F[_], A] = F[Option[A]]

object OptionT {
  def apply[F[_], A](o: F[Option[A]]): OptionT[F, A] = o

  def fromOption[F[_], A](oa: Option[A]) (given F: Applicative[F]): OptionT[F, A] =
    apply(F.pure(oa))

  def liftF[F[_], A](fa: F[A]) (given Functor[F]): OptionT[F, A] =
    apply(fa.map(Some(_)))

  implicit class OptionTOps[F[_], A](private val `this`: OptionT[F, A]) extends AnyVal {
    def value: F[Option[A]] = `this`
  }

  given [F[_]](given F: Functor[F]): Functor[[X] =>> OptionT[F, X]] =
    new OptionTFunctor[F] {}

  private trait OptionTFunctor[F[_]] (given F: Functor[F]) extends Functor[[X] =>> OptionT[F, X]] {
    def [A, B] (ota: OptionT[F, A]) map (f: A => B): OptionT[F, B] =
      F.map(ota)(_.map(f))
  }

  given [F[_]] (given F: Monad[F]): Monad[[X] =>> OptionT[F, X]] =
    new OptionTFunctor[F] with Monad[[X] =>> OptionT[F, X]] {
      def [A] (a: A) pure: OptionT[F, A] = F.pure(Some(a))
      def [A, B] (ota: OptionT[F, A]) flatMap (f: A => OptionT[F, B]): OptionT[F, B] =
        F.flatMap(ota)(oa => oa.fold(F.pure(None))(f))
    }
}
